package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.models.SavedContacts;

import java.util.List;

public interface SavedContactService
{
    List<SavedContacts> findAll();

    SavedContacts findSavedContactsById(long id);

    void delete(long id);

    SavedContacts save(SavedContacts savedContacts);

    SavedContacts findByName(String name);
}
