package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.models.UserContactType;

import java.util.List;

public interface ContactTypeService
{
    List <UserContactType> findAll();

    UserContactType findContactTypeById();

    void delete (long id);

    UserContactType save(UserContactType userContactType);


}
