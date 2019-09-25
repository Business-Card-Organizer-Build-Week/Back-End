package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceFoundException;
import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.*;
import local.skylerwebdev.businesscardorganizer.repository.RoleRepository;
import local.skylerwebdev.businesscardorganizer.repository.SavedContactsRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.service.Contact;

import java.util.ArrayList;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Autowired
    SavedContactsRepository savedContactsRepository;


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    public User findUserById(long id, boolean isAdmin) throws ResourceNotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        if (id == currentUser.getUserid() || isAdmin)
        {
            return userrepos.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        } else
        {
            throw new ResourceNotFoundException(id + " Not current user");
        }
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll()
                 .iterator()
                 .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name, boolean isAdmin)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        if (name.equalsIgnoreCase(currentUser.getUsername()) || isAdmin)
        {
            User uu = userrepos.findByUsername(name);
            if (uu == null)
            {
                throw new ResourceNotFoundException("User name " + name + " not found!");
            }
            return uu;
        }else
        {
            throw new ResourceNotFoundException(name + " Not current user");
        }
    }

    @Transactional
    @Override
    public User save(User user)
    {
        if (userrepos.findByUsername(user.getUsername()) != null)
        {
            throw new ResourceFoundException(user.getUsername() + " is already taken!");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername().toLowerCase().replaceAll("\\s+",""));
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setBusname(user.getBusname());
        newUser.setTitle(user.getTitle());
        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserroles())
        {
            long id = ur.getRole()
                        .getRoleid();
            Role role = rolerepos.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setUserroles(newRoles);

        if (user.getUserContacts() != null)
        {
            for (UserContact ue : user.getUserContacts())
            {
                newUser.getUserContacts()
                        .add(new UserContact(ue.getUseremail(), newUser.getFname(), newUser.getLname(), newUser.getBusname(), ue.getUserphone(), ue.getUseraddress(), ue.getUsercity(), ue.getUserState(), ue.getUserzip(), newUser, ue.getUsercontacttype()));
            }
        }
        if (user.getSavedContacts() != null)
        {
//        for (SavedContacts s : user.getSavedContacts())
//        {
//            newUser.getSavedContacts().add(new SavedContacts(newUser, ));
//        }
        }

        return userrepos.save(newUser);
    }


    @Transactional
    @Override
    public User update(User user, long id, boolean isAdmin)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (id == currentUser.getUserid() || isAdmin)
        {
            if (user.getUsername() != null)
            {
                currentUser.setUsername(user.getUsername());
            }
            if (user.getFname() != null)
            {
                currentUser.setFname(user.getFname());
            }
            if (user.getLname() != null)
        {
            currentUser.setLname(user.getLname());
        }
            if (user.getBusname() != null)
        {
            currentUser.setBusname(user.getBusname());
        }
            if (user.getTitle() !=null)
            {
                currentUser.setTitle(user.getTitle());
            }

            if (user.getPassword() != null)
            {
                currentUser.setPasswordNoEncrypt(user.getPassword());
            }

            if (user.getUserroles()
                    .size() > 0)
            {
                throw new ResourceFoundException("User Roles are not updated through User");
            }

            if (user.getUserContacts()
                    .size() > 0)
            {
                for (UserContact ue : user.getUserContacts())
                {
                    currentUser.getUserContacts()
                               .add(new UserContact(ue.getUseremail(), currentUser.getFname(), currentUser.getLname(), currentUser.getBusname(), ue.getUserphone(), ue.getUseraddress(), ue.getUsercity(), ue.getUserState(), ue.getUserzip(), currentUser, ue.getUsercontacttype()));
                }
            }

//            if(user.getSavedContacts().size() > 0)
//            {
//                for (SavedContacts s : user.getSavedContacts())
//                {
//                    currentUser.getSavedContacts().add(new SavedContacts(currentUser()));
//                }
//            }

            return userrepos.save(currentUser);
        } else
        {
            throw new ResourceNotFoundException(id + " Not current user");
        }
    }

    @Transactional
    @Override
    public void deleteUserRole(long userid, long roleid)
    {
        userrepos.findById(userid)
                 .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        rolerepos.findById(roleid)
                 .orElseThrow(() -> new ResourceNotFoundException("Role id " + roleid + " not found!"));

        if (rolerepos.checkUserRolesCombo(userid, roleid)
                     .getCount() > 0)
        {
            rolerepos.deleteUserRoles(userid, roleid);
        } else
        {
            throw new ResourceNotFoundException("Role and User Combination Does Not Exists");
        }
    }

    @Transactional
    @Override
    public void addUserRole(long userid, long roleid)
    {
        userrepos.findById(userid)
                 .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        rolerepos.findById(roleid)
                 .orElseThrow(() -> new ResourceNotFoundException("Role id " + roleid + " not found!"));

        if (rolerepos.checkUserRolesCombo(userid, roleid)
                     .getCount() <= 0)
        {
            rolerepos.insertUserRoles(userid, roleid);
        } else
        {
            throw new ResourceFoundException("Role and User Combination Already Exists");
        }
    }

    @Override
    public void addSavedContact(long userid, long contactid, boolean isAdmin)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        if (userid == currentUser.getUserid() || isAdmin)
        {
        userrepos.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
        if (savedContactsRepository.checkSavedContactCombo(userid,contactid).getCount() <= 0)
        {
            savedContactsRepository.insertSavedContact(userid, contactid);
        }
        }else
        {
            throw new ResourceNotFoundException("Combo Exists");
        }
    }

    @Override
    public void deleteSavedContact(long userid, long contactid)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        if (userid == currentUser.getUserid())
        {
        savedContactsRepository.deleteSavedContacts(userid, contactid);
        }else
        {
            throw new ResourceNotFoundException("Current User Not Logged In");

        }
    }
}
