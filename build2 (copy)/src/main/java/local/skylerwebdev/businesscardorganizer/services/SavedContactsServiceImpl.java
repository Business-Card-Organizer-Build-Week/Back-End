package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.SavedContacts;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.repository.SavedContactsRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserContactRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service(value = "savedContactsService")
public class SavedContactsServiceImpl implements SavedContactsService
{
    @Autowired
    SavedContactsRepository savedContactsRepository;
    @Autowired
    private UserRepository userrepos;
    @Autowired
    private UserContactRepository contactRepository;

    @Override
    public List<SavedContacts> findAll()
    {
        return null;
    }

    @Override
    public SavedContacts findSavedContactsById(long contactid)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {

    }
//    @Transactional
//    @Override
    public void save( long userid, long contactid)
    {
//        userrepos.findById(userid)
//                .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
//        contactRepository.findById(contactid)
//               .orElseThrow(() -> new ResourceNotFoundException("Role id " + contactid + " not found!"));
//
//
//        if (savedContactsRepository.checkSavedContactCombo(userid, contactid).getCount() <= 0)
//        {
//            savedContactsRepository.insertSavedContact(userid, contactid);
//        }else
//        {
//            throw new ResourceNotFoundException("SavedContact Combo Found Already");
//        }
//

}
    @Override
    public SavedContacts findByName(String name)
    {
        return null;
    }
}
