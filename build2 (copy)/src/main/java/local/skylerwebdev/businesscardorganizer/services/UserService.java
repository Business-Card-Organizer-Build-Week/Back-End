package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService
{
    UserDetails loadUserByUsername(String username);

    List<User> findAll();

    User findUserById(long id, boolean isAdmin);

    User findByName(String name, boolean isAdmin);

    void delete(long id);

    User save(User user);

    User update(User user, long id, boolean isAdmin);

    void deleteUserRole(long userid, long roleid);

    void addUserRole(long userid, long roleid);

    void addSavedContact(long userid, long contactid, boolean isAdmin);

    void deleteSavedContact(long userid, long contactid);

}