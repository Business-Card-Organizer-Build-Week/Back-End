package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.User;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.UserContactType;
import local.skylerwebdev.businesscardorganizer.repository.UserContactRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userContactService")
public class UserContactServiceImpl implements UserContactService
{
    @Autowired
    private UserContactRepository usercontactrepos;

    @Autowired
    UserRepository userRepository;

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

    @Transactional
    @Override
    public UserContact update(UserContact userContact, long contactid)
    {

        UserContact currentContact = usercontactrepos.findById(contactid).orElseThrow(() -> new ResourceNotFoundException("Not Found"));

        if (usercontactrepos.findById(contactid)
                .isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext()
                    .getAuthentication();
            if (usercontactrepos.findById(contactid)
                    .get()
                    .getUser()
                    .getUsername()
                    .equalsIgnoreCase(authentication.getName()))
            {
                long contactTypeId = currentContact.getUsercontacttype().getContacttypeid();

//                if (contactid == currentContact.getContactid())
//                {
                    if (userContact.getUseremail() != null)
                    {
                        currentContact.setUseremail(userContact.getUseremail());
                    }
                    if (userContact.getUserphone() != null)
                    {
                        currentContact.setUserphone(userContact.getUserphone().replaceAll("[()\\s-]+", "").replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"));
                    }
                    if (userContact.getUseraddress() != null)
                    {
                        currentContact.setUseraddress(userContact.getUseraddress());
                    }
                    if (userContact.getUsercity() != null)
                    {
                        currentContact.setUsercity(userContact.getUsercity());
                    }
                    if (userContact.getUserState() != null)
                    {
                        currentContact.setUserState(userContact.getUserState());
                    }
                    if (userContact.getUserzip() != null)
                    {
                        currentContact.setUserzip(userContact.getUserzip());
                    }
                    if (userContact.getUsercontacttype().getContacttypeid() != contactTypeId)
                    {
                        currentContact.setUsercontacttype(userContact.getUsercontacttype());
                    }
                    return usercontactrepos.save(currentContact);

                } else
                {
                    throw new ResourceNotFoundException(authentication.getName() + " not authorized to make change");
                }
            }
//        else
//            {
//                throw new ResourceNotFoundException("User Contact with id " + contactid + " Not Found!");
//            }
         else
        {
            throw new ResourceNotFoundException("User Contact with id " + contactid + " Not Found!");
        }

    }
}
