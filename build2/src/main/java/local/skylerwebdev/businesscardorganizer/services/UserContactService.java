package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.models.UserContact;

import java.util.List;

public interface UserContactService
{
    List<UserContact> findAll();

    UserContact findUserContactById(long contactid);

    void delete(long id, boolean isAdmin);

    UserContact save(UserContact userContact, boolean isAdmin);
}
