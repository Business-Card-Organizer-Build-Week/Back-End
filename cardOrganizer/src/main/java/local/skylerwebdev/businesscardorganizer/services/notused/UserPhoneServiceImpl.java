//package local.skylerwebdev.businesscardorganizer.services;
//
//import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
//import local.skylerwebdev.businesscardorganizer.models.UserPhone;
//import local.skylerwebdev.businesscardorganizer.repository.UserPhoneRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service(value = "userphoneService")
//public class UserPhoneServiceImpl implements UserPhoneService
//{
//    @Autowired
//    private UserPhoneRepository userphonerepos;
//
//    @Override
//    public List<UserPhone> findAll()
//    {
//        List<UserPhone> list = new ArrayList<>();
//        userphonerepos.findAll()
//                      .iterator()
//                      .forEachRemaining(list::add);
//        return list;
//    }
//
//    @Override
//    public UserPhone findUserPhoneById(long id)
//    {
//        return userphonerepos.findById(id)
//                             .orElseThrow(() -> new ResourceNotFoundException("UserPhone with id " + id + " Not Found!"));
//    }
//
//    @Override
//    public List<UserPhone> findByUserName(String username)
//    {
//        return userphonerepos.findAllByUser_Username(username);
//    }
//
//    @Override
//    public void delete(long id, boolean isAdmin)
//    {
//        if (userphonerepos.findById(id)
//                          .isPresent())
//        {
//            Authentication authentication = SecurityContextHolder.getContext()
//                                                                 .getAuthentication();
//            if (userphonerepos.findById(id)
//                              .get()
//                              .getUser()
//                              .getUsername()
//                              .equalsIgnoreCase(authentication.getName()) || isAdmin)
//            {
//                userphonerepos.deleteById(id);
//            } else
//            {
//                throw new ResourceNotFoundException(authentication.getName() + " not authorized to make change");
//            }
//        } else
//        {
//            throw new ResourceNotFoundException("UserPhone with id " + id + " Not Found!");
//        }
//    }
//
//    @Override
//    public UserPhone save(UserPhone userphone, boolean isAdmin)
//    {
//        Authentication authentication = SecurityContextHolder.getContext()
//                                                             .getAuthentication();
//
//        if (userphone.getUser()
//                     .getUsername()
//                     .equalsIgnoreCase(authentication.getName()) || isAdmin)
//        {
//            return userphonerepos.save(userphone);
//        } else
//        {
//            throw new ResourceNotFoundException((authentication.getName() + "not authorized to make change"));
//        }
//    }
//}
