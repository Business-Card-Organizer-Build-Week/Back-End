package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.UserContactType;
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
    public UserContact findUserContactById(long contactid) throws ResourceNotFoundException
    {
        return usercontactrepos.findById(contactid)
                             .orElseThrow(() -> new ResourceNotFoundException("User Contact with id " + contactid + " Not Found!"));
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
            UserContact newUserContact = new UserContact();
            newUserContact.setUseremail(userContact.getUseremail());
            newUserContact.setUserphone(userContact.getUserphone());
            newUserContact.setUseraddress(userContact.getUseraddress());
            newUserContact.setUsercity(userContact.getUsercity());
            newUserContact.setUserState(userContact.getUserState());
            newUserContact.setUserzip(userContact.getUserzip());
            newUserContact.setUsercontacttype(userContact.getUsercontacttype());
            return usercontactrepos.save(newUserContact);

        } else
        {
            throw new ResourceNotFoundException((authentication.getName() + "not authorized to make change"));
        }
    }
}
