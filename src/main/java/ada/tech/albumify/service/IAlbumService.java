package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.exceptions.AlbumNotFoundException;
import ada.tech.albumify.domain.dto.exceptions.AlreadyExistsException;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;

import java.util.List;

public interface IAlbumService {
    Album createAlbum(AlbumDto request, int userId) throws AlbumNotFoundException, AlreadyExistsException;
    List<Album> readAllAlbums();
    Album readAlbum(int id) throws AlbumNotFoundException;
    Album updateAlbum(int id, AlbumDto request) throws AlbumNotFoundException;
    void deleteAlbum(int id) throws AlbumNotFoundException;
}
