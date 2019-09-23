package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.models.ContactType;

import java.util.List;

public interface ContactTypeService
{
    List <ContactType> findAll();

    ContactType findContactTypeById(long id);

    void delete(long id);

    ContactType save(ContactType contactType);

    ContactType findByName(String name);
}
