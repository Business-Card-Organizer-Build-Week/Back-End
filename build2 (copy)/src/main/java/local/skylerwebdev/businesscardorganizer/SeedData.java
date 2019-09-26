//package local.skylerwebdev.businesscardorganizer;
//
//import local.skylerwebdev.businesscardorganizer.models.*;
//import local.skylerwebdev.businesscardorganizer.services.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//
//@Transactional
//@Component
//public class SeedData implements CommandLineRunner
//{
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserContactService userContactService;
//    @Autowired
//    SavedContactsService savedContactsService;
//
//    @Autowired
//    ContactTypeService userContactTypeService;
//
//
////    @Autowired
////    SavedContactService savedContactService;
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//        Role r3 = new Role("data");
//
//        roleService.save(r1);
//        roleService.save(r2);
//        roleService.save(r3);
//
//        UserContactType ct1 = new UserContactType("Home");
//        UserContactType ct2 = new UserContactType("Business");
//        UserContactType ct3 = new UserContactType("Other");
//
//        userContactTypeService.save(ct1);
//        userContactTypeService.save(ct2);
//        userContactTypeService.save(ct3);
//
//
//        // admin, data, user
//        ArrayList<UserRoles> admins = new ArrayList<>();
//        ArrayList<SavedContacts> saved = new ArrayList<>();
//        admins.add(new UserRoles(new User(), r1));
//        admins.add(new UserRoles(new User(), r2));
//        admins.add(new UserRoles(new User(), r3));
//        User u1 = new User("admin", "Admin", "Account", "Business Card Organizer", "Admin", "password", admins);
//
//        u1.getUserContacts()
//          .add(new UserContact("admin@businesscardorganizer.com", u1.getFname(), u1.getLname(), u1.getBusname(), "5555551212", "123 Test Address", "Some City", "ST", "55555",u1 ,ct1));
//        u1.getUserContacts()
//          .add(new UserContact("test2@test.com", u1.getFname(), u1.getLname(), u1.getBusname(), "5555551717", "321 Test Address", "Test City", "ST", "50225", u1,ct2));
//        User thisUser1 = userService.save(u1);
//        savedContactsService.save(thisUser1.getUserid(), thisUser1.getUserContacts().get(0).getContactid());
//
//        // data, user
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        datas.add(new UserRoles(new User(), r3));
//        datas.add(new UserRoles(new User(), r2));
//        User u2 = new User("datauser", "Data", "Account", "Business Card Organzier", "Data", "password", datas);
//        u2.getUserContacts()
//          .add(new UserContact("data@test.com", u1.getFname(), u1.getLname(), u1.getBusname(), "5555551234", "TestAddress", "Test City", "ST", "55555", u2,ct1));
//        u2.getUserContacts()
//          .add(new UserContact("test@data.com", u1.getFname(), u1.getLname(), u1.getBusname(), "5555558855", "TestAddress", "Test City", "ST", "55555", u2,ct2));
//        u2.getUserContacts()
//          .add(new UserContact("datatest@test.com", u1.getFname(), u1.getLname(), u1.getBusname(), "5555556632", "TestAddress", "Test City", "ST", "55555", u2,ct3));
//        userService.save(u2);
//
//        // user
//        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
//        User u3 = new User("testuser", "Test", "Account", "Business Card Organizer", "Test Account", "password", users);
//        u3.getUserContacts()
//          .add(new UserContact("testaccount@test.com", u1.getFname(), u1.getLname(), u1.getBusname(), "2223334444", "123 Test Account Address", "Test City", "ST", "55555", u1,ct1));
//        userService.save(u3);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
//        User u4 = new User("Bob", "John", "Smith", "TestBusName", "Title", "password", users);
//        userService.save(u4);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
//        User u5 = new User("Jane",  "John", "Smith", "TestBusName", "Title","password", users);
//        userService.save(u5);
//        System.out.println(userService.findAll());
//        savedContactsService.save(thisUser1.getUserid(), thisUser1.getUserContacts().get(0).getContactid());
//
//    }
//}