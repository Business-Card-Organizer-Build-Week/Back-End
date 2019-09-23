//package local.skylerwebdev.businesscardorganizer.repository;
//
//import local.skylerwebdev.businesscardorganizer.models.PhoneContactType;
//import local.skylerwebdev.businesscardorganizer.view.JustTheCount;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//public interface PhoneContactTypeRepository extends CrudRepository<PhoneContactType, Long>
//{
//    @Query(value = "SELECT COUNT(*) as count FROM userphones WHERE userid = :userid AND phonetypeid = :typeid",
//            nativeQuery = true)
//    JustTheCount checkUserPhonesCombo(long userid, long typeid);
//}
