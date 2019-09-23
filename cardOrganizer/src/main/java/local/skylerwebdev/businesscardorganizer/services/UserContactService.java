package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.UserContact;

import java.util.List;

public interface UserContactService
{
    List <UserContact> findAll();

    UserContact findContactById(long id);

    void delete(long id);

    UserContact save(UserContact userContact);

    UserContact findByName(String name);
}
