package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.exceptions.AlreadyExistsException;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;

import java.util.List;

public interface IAlbumService {
    Album createAlbum(AlbumDto request, int userId) throws NotFoundException, AlreadyExistsException;
    List<Album> readAllAlbums();
    Album readAlbum(int id) throws NotFoundException;
    Album updateAlbum(int id, AlbumDto request) throws NotFoundException;
    void deleteAlbum(int id) throws NotFoundException;
}
