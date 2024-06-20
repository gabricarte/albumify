package ada.tech.albumify.repositories;

import ada.tech.albumify.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository  extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
