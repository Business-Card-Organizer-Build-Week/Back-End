package local.skylerwebdev.businesscardorganizer.repository;

import local.skylerwebdev.businesscardorganizer.models.ContactType;
import local.skylerwebdev.businesscardorganizer.view.JustTheCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserContactTypeRepository extends CrudRepository<ContactType, Long>
{
    @Query(value = "SELECT COUNT(*) as count FROM usercontacts WHERE userid = :userid AND contacttypeid = :typeid",
            nativeQuery = true)
    JustTheCount checkUserContactCombo(long userid, long typeid);

}
