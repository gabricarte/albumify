package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;

import java.util.List;

public interface IAlbumUserService {
    List<Album> readAlbumsByUserId(int userId);

    void deleteAlbumFromUser(int idUser, int idAlbum) throws NotFoundException;

}
