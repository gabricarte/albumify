package ada.tech.albumify.repositories;

import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.domain.entities.AlbumUser;
import ada.tech.albumify.domain.entities.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlbumUserRepository  extends JpaRepository<AlbumUser, Integer> {
    boolean existsByUserAndAlbum(User user, Album album);
    @Query("SELECT au.album FROM AlbumUser au WHERE au.user.id = :userId")
    List<Album> findAlbumsByUserId(@Param("userId") int userId);

    @Query("SELECT au FROM AlbumUser au WHERE au.user.id = :userId AND au.album.id = :albumId")
    AlbumUser findByUserIdAndAlbumId(@Param("userId") int userId, @Param("albumId") int albumId);
}
