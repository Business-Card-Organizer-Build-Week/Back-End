package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.ContactType;
import local.skylerwebdev.businesscardorganizer.models.User;
import local.skylerwebdev.businesscardorganizer.repository.UserContactTypeRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service(value = "contactTypeService")
public class ContactTypeServiceImpl  implements ContactTypeService
{
    @Autowired
    UserContactTypeRepository contactTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<ContactType> findAll()
    {
        return null;
    }

    @Override
    public ContactType findContactTypeById(long id)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {

    }

    @Override
    public ContactType save(ContactType contactType)
    {
        ContactType newContactType = new ContactType();
        ArrayList<UserContact> newUserUserContact = new ArrayList<>();
        for (UserContact c : contactType.getUserContacts())
        {
            long id = c.getUser().getUserid();
            User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
            newUserUserContact.add(new UserContact(c.getUseremail(), c.getUserphone(), c.getAddress1(), c.getAddress2(), c.getCity(), c.getState(), c.getZip(), c.getUser(), newContactType));
        }
        newContactType.setUserContacts(newUserUserContact);
        return contactTypeRepository.save(contactType);
    }


    @Override
    public ContactType findByName(String name)
    {
        return null;
    }


}