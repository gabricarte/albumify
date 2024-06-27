package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.ArtistInfoResponse;
import ada.tech.albumify.domain.dto.SummaryDto;
import ada.tech.albumify.domain.dto.exceptions.AlreadyExistsException;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.domain.entities.AlbumUser;
import ada.tech.albumify.domain.entities.User;
import ada.tech.albumify.domain.mappers.AlbumMapper;
import ada.tech.albumify.external.LastFmApi;
import ada.tech.albumify.repositories.IAlbumRepository;
import ada.tech.albumify.repositories.IAlbumUserRepository;
import ada.tech.albumify.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class AlbumService implements IAlbumService {

    private final IAlbumRepository albumRepository;
    private final IUserRepository userRepository;
    private final IAlbumUserRepository albumUserRepository;
    private final String lastFmApiKey = "fbb6863a69641c4bfef033a9002e3276";
    private final LastFmApi lastFmApi;

    @Override
    public Album createAlbum(AlbumDto request, int userId) throws NotFoundException, AlreadyExistsException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, String.valueOf(userId)));
        Album album;

        if (albumRepository.existsByNameAndArtist(request.getName(), request.getArtist())) {
            System.out.println("Album already exists in the database, relating it with the user...");
            album = albumRepository.findByNameAndArtist(request.getName(), request.getArtist());
        } else {
            album = AlbumMapper.toEntity(request);
            albumRepository.save(album);
        }

        if (albumUserRepository.existsByUserAndAlbum(user, album)) {
            throw new AlreadyExistsException(album, user);
        }

        AlbumUser albumUser = new AlbumUser();
        albumUser.setAlbum(album);
        albumUser.setUser(user);
        albumUserRepository.save(albumUser);

        return album;
    }



    @Override
    public List<Album> readAllAlbums() {
        return albumRepository
                .findAll()
                .stream()
                .toList();
    }

    @Override
    public Album readAlbum(int id) throws NotFoundException {
        return albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class, String.valueOf(id)));
    }

    @Override
    public Album updateAlbum(int id, AlbumDto request) throws NotFoundException {
        final Album a = albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class, String.valueOf(id)));
        a.setName(request.getName());
        a.setArtist(request.getArtist());
        a.setSummary(request.getSummary());
        return albumRepository.save(a);
    }

    @Override
    public void deleteAlbum(int id) throws NotFoundException {
        albumRepository.deleteById(id);
    }

    private Album searchAlbumById(int id) throws NotFoundException {
        return albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class, String.valueOf(id)));
    }

    public SummaryDto getArtistSummary(String artistName) {
        ArtistInfoResponse response = lastFmApi.getArtistInfo("artist.getinfo", artistName, lastFmApiKey, "json");
        String summary = response.getArtist().getBio().getSummary();
        return new SummaryDto(summary);
    }

}
