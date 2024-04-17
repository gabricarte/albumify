package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;

import java.util.List;

public interface IAlbumService {
    AlbumDto createAlbum(AlbumDto request);
    List<AlbumDto> readAllAlbums();
    AlbumDto readAlbum(int id) throws NotFoundException;
    AlbumDto updateAlbum(int id, AlbumDto request) throws NotFoundException;
    void deleteAlbum(int id) throws NotFoundException;
}
