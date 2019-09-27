package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.models.UserContactType;

import java.util.List;

public interface ContactTypeService
{

    UserContactType findContactTypeById(long id);

    UserContactType save(UserContactType userContactType);


}
