package ada.tech.albumify.repositories;

import ada.tech.albumify.domain.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository extends JpaRepository<Album, Integer> {

}
