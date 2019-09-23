package local.skylerwebdev.businesscardorganizer;

import local.skylerwebdev.businesscardorganizer.models.*;
import local.skylerwebdev.businesscardorganizer.services.*;
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
    ContactTypeService contactTypeService;

//    @Autowired
//    PhoneContactTypeService phoneContactTypeService;
//
//    @Autowired
//    AddressContactTypeService addressContactTypeService;

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);


        ContactType c1 = new ContactType("Home");
        ContactType c2 = new ContactType("Business");
        ContactType c3 = new ContactType("Other");

        contactTypeService.save(c1);
        contactTypeService.save(c2);
        contactTypeService.save(c3);
        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "John", "Smith", "Business Name", "password", admins);
        u1.getUsercontacts(new UserContact("test@test.test", "123-456-7891", "Test Address1", "TestAddress2", "City", "ST","3213333", c1));
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "John", "Smith", "Business Name",   "1234567", datas);
//        u2.getUseremails()
//          .add(new Useremail(u2, "cinnamon@mymail.local",c1));
//        u2.getUseremails()
//          .add(new Useremail(u2, "hops@mymail.local",c2));
//        u2.getUseremails()
//          .add(new Useremail(u2, "bunny@email.local",c3));
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "John", "Smith", "Business Name",   "ILuvM4th!", users);
//        u3.getUseremails()
//          .add(new Useremail(u3, "barnbarn@email.local",c1));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "John", "Smith", null,  "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "John", "Smith", null,  "password", users);
        userService.save(u5);
    }
}