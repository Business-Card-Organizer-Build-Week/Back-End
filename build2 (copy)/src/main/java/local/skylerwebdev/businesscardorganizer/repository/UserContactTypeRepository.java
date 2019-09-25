package local.skylerwebdev.businesscardorganizer.repository;

import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.UserContactType;
import local.skylerwebdev.businesscardorganizer.view.JustTheCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserContactTypeRepository extends CrudRepository<UserContactType, Long>
{
    @Query(value = "SELECT COUNT(*) as count FROM usercontacts WHERE userid = :userid AND contacttypeid = :typeid",
          nativeQuery = true)
    JustTheCount checkUserPhonesCombo(long userid, long typeid);
}
