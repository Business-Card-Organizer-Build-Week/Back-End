//package local.skylerwebdev.businesscardorganizer.services;
//
//
//import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
//import local.skylerwebdev.businesscardorganizer.models.*;
//import local.skylerwebdev.businesscardorganizer.repository.AddressContactTypeRepository;
//import local.skylerwebdev.businesscardorganizer.repository.PhoneContactTypeRepository;
//import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service(value = "addressContactTypeService")
//public class AddressContactTypeServiceImpl implements AddressContactTypeService
//{
//    @Autowired
//    AddressContactTypeRepository addressContactTypeRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Override
//    public List<AddressContactType> findAll()
//    {
//        return null;
//    }
//
//    @Override
//    public AddressContactType findContactTypeById(long id)
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
//    public AddressContactType save(AddressContactType addressContactType)
//    {
//        AddressContactType newAddressContactType = new AddressContactType();
//        newAddressContactType.setAddresscontacttype(addressContactType.getAddresscontacttype());
//
//        ArrayList<UserAddress> newContactTypes = new ArrayList<>();
//        for (UserAddress ua : addressContactType.getUseraddresses())
//        {
//            long id = ua.getUser().getUserid();
//            User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
//            newContactTypes.add(new UserAddress(ua.getAddress1(), ua.getAddress2(), ua.getCity(), ua.getState(), ua.getZip(), ua.getUser(), newAddressContactType));
//        }
//        newAddressContactType.setUseraddresses(newContactTypes);
//        return addressContactTypeRepository.save(addressContactType);
//    }
//
//    @Override
//    public AddressContactType findByName(String name)
//    {
//        return null;
//    }
//}
