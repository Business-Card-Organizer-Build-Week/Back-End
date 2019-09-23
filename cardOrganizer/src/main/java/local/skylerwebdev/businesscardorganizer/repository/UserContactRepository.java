package local.skylerwebdev.businesscardorganizer.repository;

import local.skylerwebdev.businesscardorganizer.models.UserContact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface UserContactRepository extends CrudRepository<UserContact, Long>
{
    List<UserContact> findAllByUser_Username(String name);
}
