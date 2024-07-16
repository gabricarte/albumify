package ada.tech.albumify.service;

import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.dto.exceptions.UserNotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.domain.entities.AlbumUser;
import ada.tech.albumify.domain.entities.User;
import ada.tech.albumify.repositories.IAlbumRepository;
import ada.tech.albumify.repositories.IAlbumUserRepository;
import ada.tech.albumify.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlbumUserServiceService implements IAlbumUserService {

    private final IAlbumUserRepository albumUserRepository;

    private final IUserRepository userRepository;

    private  final IAlbumRepository albumRepository;

    @Override
    public List<Album> readAlbumsByUserId(int userId) throws UserNotFoundException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return albumUserRepository.findAlbumsByUserId(userId);
    }

    @Override
    public void deleteAlbumFromUser(int userId, int albumId) throws NotFoundException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        albumRepository.findById(userId).orElseThrow(() -> new NotFoundException(Album.class, String.valueOf(albumId)));

        AlbumUser albumUser = albumUserRepository.findByUserIdAndAlbumId(userId, albumId);
        if (albumUser != null) {
            albumUserRepository.delete(albumUser);
        } else {
            throw new NotFoundException(AlbumUser.class, String.valueOf(albumUser.getId()));
        }
    }
}
