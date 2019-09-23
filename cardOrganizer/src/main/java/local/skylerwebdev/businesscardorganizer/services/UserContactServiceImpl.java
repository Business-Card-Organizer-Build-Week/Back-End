package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.ContactType;
import local.skylerwebdev.businesscardorganizer.models.User;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.repository.UserContactRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserContactTypeRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service(value = "userContactService")
public class UserContactServiceImpl implements UserContactService
{
    @Autowired
    UserContactRepository userContactRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserContact> findAll()
    {
        return null;
    }

    @Override
    public UserContact findContactById(long id)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {

    }

    @Override
    public UserContact save(UserContact userContact)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(userContact.getUser().getUsername().equalsIgnoreCase(authentication.getName()))
        {
            return userContactRepository.save(userContact);
        }else
        {
            throw new ResourceNotFoundException((authentication.getName() + "notAuthorized"));
        }
    }

    @Override
    public UserContact findByName(String name)
    {
        return null;
    }
}