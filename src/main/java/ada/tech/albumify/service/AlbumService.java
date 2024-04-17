package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.mappers.AlbumMapper;
import ada.tech.albumify.repositories.IAlbumRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class AlbumService implements IAlbumService{
    private final IAlbumRepository repository;

    public AlbumService(IAlbumRepository repository) {
        this.repository = repository;
    }
    @Override
    public AlbumDto createAlbum(AlbumDto request) {
        Album album = AlbumMapper.toEntity(request);
        return AlbumMapper.toDto(repository.save(album));
    }

    @Override
    public List<AlbumDto> readAllAlbums() {
        return repository.findAll().stream().map(AlbumMapper::toDto).toList();
    }

    @Override
    public AlbumDto readAlbum(int id) throws NotFoundException {
        return AlbumMapper.toDto(
                repository.findById(id)
                        .orElseThrow(()-> new NotFoundException(Album.class, String.valueOf(id))));
    }

    @Override
    public AlbumDto updateAlbum(int id, AlbumDto request) throws NotFoundException{
        final Album a = repository.findById(id).orElseThrow(()->new NotFoundException(Album.class, String.valueOf(id)));
        a.setName(request.getName());
        a.setArtist(request.getArtist());
        a.setYear(request.getYear());
        return AlbumMapper.toDto(repository.save(a));
    }

    @Override
    public void deleteAlbum(int id) throws NotFoundException {
        repository.deleteById(id);
    }
}
