package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.ArtistInfoResponse;
import ada.tech.albumify.domain.dto.SummaryDto;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.domain.mappers.AlbumMapper;
import ada.tech.albumify.external.LastFmApi;
import ada.tech.albumify.repositories.IAlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class AlbumService implements IAlbumService {

    private final IAlbumRepository repository;
    private final String lastFmApiKey = "fbb6863a69641c4bfef033a9002e3276";
    private final LastFmApi lastFmApi;
    @Override
    public AlbumDto createAlbum(AlbumDto request) {
        Album album = AlbumMapper.toEntity(request);
        return AlbumMapper.toDto(repository.save(album), getArtistSummary(album.getArtist()));
    }

    @Override
    public List<AlbumDto> readAllAlbums() {
        return repository
                .findAll()
                .stream()
                .map(ent -> AlbumMapper.toDto(ent, getArtistSummary(ent.getArtist())))
                .toList();
    }

    @Override
    public AlbumDto readAlbum(int id) throws NotFoundException {
        return AlbumMapper.toDto(searchAlbumById(id), getArtistSummary(searchAlbumById(id).getArtist()));
    }

    @Override
    public AlbumDto updateAlbum(int id, AlbumDto request) throws NotFoundException {
        final Album a = repository.findById(id).orElseThrow(() -> new NotFoundException(Album.class, String.valueOf(id)));
        a.setName(request.getName());
        a.setArtist(request.getArtist());
        a.setYear(request.getYear());
        return AlbumMapper.toDto(repository.save(a), getArtistSummary(a.getArtist()));
    }

    @Override
    public void deleteAlbum(int id) throws NotFoundException {
        repository.deleteById(id);
    }

    private Album searchAlbumById(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(Album.class, String.valueOf(id)));
    }

    public SummaryDto getArtistSummary(String artistName) {
        ArtistInfoResponse response = lastFmApi.getArtistInfo("artist.getinfo", artistName, lastFmApiKey, "json");
        String summary = response.getArtist().getBio().getSummary();
        return new SummaryDto(summary);
    }
}
