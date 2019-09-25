package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.User;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.UserContactType;
import local.skylerwebdev.businesscardorganizer.repository.UserContactTypeRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userContactTypeService")
public class ContactTypeServiceImpl implements ContactTypeService

{
    @Autowired
    UserContactTypeRepository userContactTypeRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserContactType findContactTypeById(long id)
    {
        return userContactTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }


    @Override
    public UserContactType save(UserContactType userContactType)
    {
        UserContactType newCtype = new UserContactType();
        newCtype.setContacttype(userContactType.getContacttype());
        ArrayList<UserContact> newCtypes = new ArrayList<>();
        for (UserContact c : userContactType.getUsercontacts())
        {
            long id = c.getUser().getUserid();
            User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User id " + id + " was not found!"));
            newCtypes.add(new UserContact(c.getUseremail(),c.getFname(),c.getLname(),c.getBusname(), c.getUserphone(), c.getUseraddress(), c.getUsercity(), c.getUserState(), c.getUserzip(), c.getUser(), newCtype));
        }
        newCtype.setUsercontacts(newCtypes);
        return userContactTypeRepository.save(userContactType);
    }
}
