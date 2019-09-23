//package local.skylerwebdev.businesscardorganizer.repository;
//
//import local.skylerwebdev.businesscardorganizer.models.EmailContactType;
//import local.skylerwebdev.businesscardorganizer.view.JustTheCount;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//public interface EmailContactTypeRepository extends CrudRepository<EmailContactType, Long>
//{
//    @Query(value = "SELECT COUNT(*) as count FROM usercontacts WHERE userid = :userid AND contacttypeid = :typeid",
//            nativeQuery = true)
//    JustTheCount checkUserEmailsCombo(long userid, long typeid);
//
//}
