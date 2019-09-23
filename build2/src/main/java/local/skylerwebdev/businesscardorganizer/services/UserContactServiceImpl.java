package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.repository.UserContactRepository;
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
    private UserContactRepository usercontactrepos;

    @Override
    public List<UserContact> findAll()
    {
        List<UserContact> list = new ArrayList<>();
        usercontactrepos.findAll()
                      .iterator()
                      .forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserContact findUseremailById(long id)
    {
        return usercontactrepos.findById(id)
                             .orElseThrow(() -> new ResourceNotFoundException("Useremail with id " + id + " Not Found!"));
    }

    @Override
    public List<UserContact> findByUserName(String username)
    {
        return usercontactrepos.findAllByUser_Username(username);
    }

    @Override
    public void delete(long id, boolean isAdmin)
    {
        if (usercontactrepos.findById(id)
                          .isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext()
                                                                 .getAuthentication();
            if (usercontactrepos.findById(id)
                              .get()
                              .getUser()
                              .getUsername()
                              .equalsIgnoreCase(authentication.getName()) || isAdmin)
            {
                usercontactrepos.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(authentication.getName() + " not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("Useremail with id " + id + " Not Found!");
        }
    }

    @Override
    public UserContact save(UserContact userContact, boolean isAdmin)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();

        if (userContact.getUser()
                     .getUsername()
                     .equalsIgnoreCase(authentication.getName()) || isAdmin)
        {
            return usercontactrepos.save(userContact);
        } else
        {
            throw new ResourceNotFoundException((authentication.getName() + "not authorized to make change"));
        }
    }
}
