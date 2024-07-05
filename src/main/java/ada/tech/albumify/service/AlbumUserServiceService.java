package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.domain.entities.AlbumUser;
import ada.tech.albumify.repositories.IAlbumUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlbumUserServiceService implements IAlbumUserService {

    private final IAlbumUserRepository albumUserRepository;

    @Override
    public List<Album> readAlbumsByUserId(int userId) {
        return albumUserRepository.findAlbumsByUserId(userId);
    }

    @Override
    public void deleteAlbumFromUser(int userId, int albumId) throws NotFoundException {
        AlbumUser albumUser = albumUserRepository.findByUserIdAndAlbumId(userId, albumId);
        if (albumUser != null) {
            albumUserRepository.delete(albumUser);
        } else {
            throw new NotFoundException(AlbumUser.class, String.valueOf(albumUser.getId()));
        }
    }
}
