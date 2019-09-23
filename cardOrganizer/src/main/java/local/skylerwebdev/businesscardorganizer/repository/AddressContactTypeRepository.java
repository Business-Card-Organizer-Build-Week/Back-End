//package local.skylerwebdev.businesscardorganizer.repository;
//
//import local.skylerwebdev.businesscardorganizer.models.AddressContactType;
//import local.skylerwebdev.businesscardorganizer.models.PhoneContactType;
//import local.skylerwebdev.businesscardorganizer.view.JustTheCount;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//public interface AddressContactTypeRepository extends CrudRepository<AddressContactType, Long>
//{
//    @Query(value = "SELECT COUNT(*) as count FROM userphones WHERE userid = :userid AND addresstypeid = :typeid",
//            nativeQuery = true)
//    JustTheCount checkUserAddressCombo(long userid, long typeid);
//}
