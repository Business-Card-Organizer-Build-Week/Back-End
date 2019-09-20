package local.skylerwebdev.businesscardorganizer.repository;

import local.skylerwebdev.businesscardorganizer.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
