package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.exceptions.ResourceFoundException;
import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.Role;
import local.skylerwebdev.businesscardorganizer.models.User;
import local.skylerwebdev.businesscardorganizer.models.UserRoles;
import local.skylerwebdev.businesscardorganizer.repository.RoleRepository;
import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

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

    public User findUserById(long id) throws ResourceNotFoundException
    {
        return userrepos.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
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
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name);
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
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
        newUser.setUsername(user.getUsername());
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setBusname(user.getBusname());
        newUser.setPasswordNoEncrypt(user.getPassword());

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
        for (UserContact c : user.getUsercontacts());
        {
            newU().
        }
//        for (Useremail ue : user.getUseremails())
//        {
//            newUser.getUseremails()
//                   .add(new Useremail(newUser, ue.getUseremail(), ue.getEmailcontacttype()));
//        }
//
//        for(UserPhone up : user.getUserphones())
//        {
//            newUser.getUserphones().add(new UserPhone(up.getUserphone(), newUser, up.getPhonecontacttype()));
//        }
//
//        for (UserAddress ua : user.getUseraddresses())
//        {
//            newUser.getUseraddresses().add(new UserAddress(ua.getAddress1(), ua.getAddress2(), ua.getCity(), ua.getState(), ua.getZip(), newUser, ua.getAddresscontacttype()));
//        }

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

            if (user.getPassword() != null)
            {
                currentUser.setPasswordNoEncrypt(user.getPassword());
            }

            if (user.getUserroles()
                    .size() > 0)
            {
                throw new ResourceFoundException("User Roles are not updated through User");
            }

            if (user.getUseremails()
                    .size() > 0)
            {
                for (Useremail ue : user.getUseremails())
                {
                    currentUser.getUseremails()
                               .add(new Useremail(currentUser, ue.getUseremail(), ue.getEmailcontacttype()));
                }
            }

            if(user.getUserphones().size() > 0)
            {
                for(UserPhone up : user.getUserphones())
                {
                    currentUser.getUserphones().add(new UserPhone(up.getUserphone(), currentUser, up.getPhonecontacttype()));
                }
            }

            if (user.getUseraddresses().size() > 0)
            {
                for (UserAddress ua : user.getUseraddresses())
                {
                    currentUser.getUseraddresses().add(new UserAddress(ua.getAddress1(), ua.getAddress2(), ua.getCity(), ua.getState(), ua.getZip(), currentUser, ua.getAddresscontacttype()));
                }
            }

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
}
