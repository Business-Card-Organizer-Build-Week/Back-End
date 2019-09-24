//package local.skylerwebdev.businesscardorganizer.services;
//
//import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
//import local.skylerwebdev.businesscardorganizer.models.SavedContacts;
//import local.skylerwebdev.businesscardorganizer.models.User;
//import local.skylerwebdev.businesscardorganizer.repository.SavedContactsRepository;
//import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service(value = "savedContactService")
//public class SavedContactServiceImpl implements SavedContactService
//{
//    @Autowired
//    SavedContactsRepository savedContactsRepository;
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRepository userRepository;
//    @Override
//    public List<SavedContacts> findAll()
//    {
//        List<SavedContacts> list = new ArrayList<>();
//        savedContactsRepository.findAll().iterator().forEachRemaining(list::add);
//        return list;
//    }
//
//    @Override
//    public SavedContacts findSavedContactsById(long id)
//    {
//        return null;
//    }
//
//    @Override
//    public void delete(long id)
//    {
//
//    }
//
//    @Override
//    public SavedContacts save(SavedContacts savedContacts)
//    {
//        SavedContacts newSavedContact = new SavedContacts();
//        newSavedContact.setContactid(savedContacts.getContactid());
//        long id = savedContacts.getUser().getUserid();
//        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
//        newSavedContact.setUser(user);
//
//        return savedContactsRepository.save(newSavedContact);
//
//    }
//
//    @Override
//    public SavedContacts findByName(String name)
//    {
//        return null;
//    }
//}
