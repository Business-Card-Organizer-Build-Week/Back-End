package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.models.SavedContacts;

import java.util.List;

public interface SavedContactsService
{
    List<SavedContacts> findAll();

    SavedContacts findSavedContactsById(long contactid);

    void delete(long id);

    void save( long userid, long contactid);

    SavedContacts findByName(String name);
}
