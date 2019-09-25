package local.skylerwebdev.businesscardorganizer.repository;

import local.skylerwebdev.businesscardorganizer.models.SavedContacts;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SavedContactsRepository extends CrudRepository<SavedContacts, Long>

{
    @Query(value = "SELECT COUNT(*) as count FROM savedcontacts WHERE userid = :userid AND contactid = :contactid",
            nativeQuery = true)
    JustTheCount checkSavedContactCombo(long userid, long contactid);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO savedcontacts(userid, contactid) VALUES (:userid, :contactid)",
            nativeQuery = true)
    void insertSavedContact(long userid, long contactid);

    @Query(value = "SELECT ALL FROM usercontacts where contactid = :contactid", nativeQuery = true)
    SavedContacts getSavedContactsBy(long contactid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM SavedContacts WHERE userid = :userid AND contactid = :contactid")
    void deleteSavedContacts(long userid, long contactid);
}
