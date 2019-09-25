package local.skylerwebdev.businesscardorganizer;

import local.skylerwebdev.businesscardorganizer.models.*;
import local.skylerwebdev.businesscardorganizer.services.ContactTypeService;
import local.skylerwebdev.businesscardorganizer.services.RoleService;
import local.skylerwebdev.businesscardorganizer.services.UserContactService;
import local.skylerwebdev.businesscardorganizer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    ContactTypeService userContactTypeService;

    @Autowired
    UserContactService userContactService;
//    @Autowired
//    SavedContactService savedContactService;

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        UserContactType ct1 = new UserContactType("Business");
        UserContactType ct2 = new UserContactType("Home");
        UserContactType ct3 = new UserContactType("Other");
        userContactTypeService.save(ct1);
        userContactTypeService.save(ct2);
        userContactTypeService.save(ct3);


        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "John", "Smith", "TestBusName", "Title", "password", admins);

        u1.getUserContacts()
          .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555",u1 ,ct1));
        u1.getUserContacts()
          .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u1,ct2));
        u1.getSavedContacts().add(new SavedContacts(u1,12));
        u1.getSavedContacts().add(new SavedContacts(u1,13));
        u1.getSavedContacts().add(new SavedContacts(u1,14));
        userService.save(u1);
        System.out.println(u1.getUserid());
//        userService.addSavedContact(u1, );
        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "John", "Smith", "TestBusName", "Title", "1234567", datas);
        u2.getUserContacts()
          .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u2,ct1));
        u2.getUserContacts()
          .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u2,ct2));
        u2.getUserContacts()
          .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u2,ct3));
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "John", "Smith", "TestBusName", "Title", "password", users);
        u3.getUserContacts()
          .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u1,ct1));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "John", "Smith", "TestBusName", "Title", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane",  "John", "Smith", "TestBusName", "Title","password", users);
        userService.save(u5);

         }
}