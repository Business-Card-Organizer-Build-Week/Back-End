//package local.skylerwebdev.businesscardorganizer.services;
//
//
//import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
//import local.skylerwebdev.businesscardorganizer.models.PhoneContactType;
//import local.skylerwebdev.businesscardorganizer.models.User;
//import local.skylerwebdev.businesscardorganizer.models.UserPhone;
//import local.skylerwebdev.businesscardorganizer.repository.PhoneContactTypeRepository;
//import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service(value = "phoneContactTypeService")
//public class PhoneContactTypeServiceImpl implements PhoneContactTypeService
//{
//    @Autowired
//    PhoneContactTypeRepository phoneContactTypeRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public List<PhoneContactType> findAll()
//    {
//        return null;
//    }
//
//    @Override
//    public PhoneContactType findContactTypeById(long id)
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
//    public PhoneContactType save(PhoneContactType phoneContactType)
//    {
//        PhoneContactType newPhoneContactType = new PhoneContactType();
//        newPhoneContactType.setPhonecontacttype(phoneContactType.getPhonecontacttype());
//
//        ArrayList<UserPhone> newContactTypes = new ArrayList<>();
//        for (UserPhone up : phoneContactType.getUserphones())
//        {
//            long id = up.getUser().getUserid();
//            User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
//            newContactTypes.add(new UserPhone(up.getUserphone(), up.getUser(), newPhoneContactType));
//        }
//        newPhoneContactType.setUserphones(newContactTypes);
//
//        return phoneContactTypeRepository.save(phoneContactType);
//    }
//
//    @Override
//    public PhoneContactType findByName(String name)
//    {
//        return null;
//    }
//}
