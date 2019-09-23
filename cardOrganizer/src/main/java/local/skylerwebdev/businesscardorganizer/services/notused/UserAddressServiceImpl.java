//package local.skylerwebdev.businesscardorganizer.services;
//
//import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
//import local.skylerwebdev.businesscardorganizer.models.UserAddress;
//import local.skylerwebdev.businesscardorganizer.repository.UserAddressRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service(value = "useraddressService")
//public class UserAddressServiceImpl implements UserAddressService
//{
//    @Autowired
//    private UserAddressRepository useraddressrepos;
//
//    @Override
//    public List<UserAddress> findAll()
//    {
//        List<UserAddress> list = new ArrayList<>();
//        useraddressrepos.findAll()
//                      .iterator()
//                      .forEachRemaining(list::add);
//        return list;
//    }
//
//    @Override
//    public UserAddress findUserAddressById(long id)
//    {
//        return useraddressrepos.findById(id)
//                             .orElseThrow(() -> new ResourceNotFoundException("UserAddress with id " + id + " Not Found!"));
//    }
//
//    @Override
//    public List<UserAddress> findByUserName(String username)
//    {
//        return useraddressrepos.findAllByUser_Username(username);
//    }
//
//    @Override
//    public void delete(long id, boolean isAdmin)
//    {
//        if (useraddressrepos.findById(id)
//                          .isPresent())
//        {
//            Authentication authentication = SecurityContextHolder.getContext()
//                                                                 .getAuthentication();
//            if (useraddressrepos.findById(id)
//                              .get()
//                              .getUser()
//                              .getUsername()
//                              .equalsIgnoreCase(authentication.getName()) || isAdmin)
//            {
//                useraddressrepos.deleteById(id);
//            } else
//            {
//                throw new ResourceNotFoundException(authentication.getName() + " not authorized to make change");
//            }
//        } else
//        {
//            throw new ResourceNotFoundException("UserAddress with id " + id + " Not Found!");
//        }
//    }
//
//    @Override
//    public UserAddress save(UserAddress useraddress, boolean isAdmin)
//    {
//        Authentication authentication = SecurityContextHolder.getContext()
//                                                             .getAuthentication();
//
//        if (useraddress.getUser()
//                     .getUsername()
//                     .equalsIgnoreCase(authentication.getName()) || isAdmin)
//        {
//            return useraddressrepos.save(useraddress);
//        } else
//        {
//            throw new ResourceNotFoundException((authentication.getName() + "not authorized to make change"));
//        }
//    }
//}
