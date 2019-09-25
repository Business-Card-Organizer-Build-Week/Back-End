package local.skylerwebdev.businesscardorganizer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import local.skylerwebdev.businesscardorganizer.models.*;
import local.skylerwebdev.businesscardorganizer.repository.SavedContactsRepository;
import local.skylerwebdev.businesscardorganizer.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerUnitTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> userList;

    private List<SavedContacts> savedContactsList;

    @Before
    public void setUp() throws Exception
    {
        userList = new ArrayList<>();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");
        r1.setRoleid(1);
        r2.setRoleid(2);
        r3.setRoleid(3);

        UserContactType ct1 = new UserContactType("Business");
        UserContactType ct2 = new UserContactType("Home");
        UserContactType ct3 = new UserContactType("Other");
        ct1.setContacttypeid(1);
        ct2.setContacttypeid(2);
        ct3.setContacttypeid(3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "John", "Smith", "TestBusName", "Title", "password", admins);
        u1.getUserContacts()
                .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555",u1 ,ct1));
        u1.getUserContacts().get(0).setContactid(10);
        u1.getUserContacts()
                .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u1,ct2));
        u1.getUserContacts().get(1).setContactid(11);
        u1.getSavedContacts().add(new SavedContacts(u1,12));
        u1.getSavedContacts().add(new SavedContacts(u1,13));
        u1.getSavedContacts().add(new SavedContacts(u1,14));
        u1.setUserid(101);
        userList.add(u1);
//        System.out.println(u1.getUserid());
//        userService.addSavedContact(u1, );
        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "John", "Smith", "TestBusName", "Title", "1234567", datas);
        u2.getUserContacts()
                .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u2,ct1));
        u2.getUserContacts().get(0).setContactid(20);
        u2.getUserContacts()
                .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u2,ct2));
        u2.getUserContacts().get(1).setContactid(21);
        u2.getUserContacts()
                .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u2,ct3));
        u2.getUserContacts().get(2).setContactid(22);
        u2.setUserid(102);
        userList.add(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "John", "Smith", "TestBusName", "Title", "password", users);
        u3.getUserContacts()
                .add(new UserContact("test@test.com", "5555555555", "TestAddress", "Test City", "ST", "55555", u1,ct1));
        u3.getUserContacts().get(0).setContactid(30);
        u3.setUserid(103);
        userList.add(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "John", "Smith", "TestBusName", "Title", "password", users);
        u4.setUserid(104);
        userList.add(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane",  "John", "Smith", "TestBusName", "Title","password", users);
        u5.setUserid(105);
        userList.add(u5);
        System.out.println("\n*** Seed Data ***");

    }

    @Test
    public void listAllUsers() throws Exception
    {
        String apiUrl = "/users/all";

        Mockito.when(userService.findAll()).thenReturn(userList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
//    @WithUserDetails("admin")
    public void getUserById() throws Exception
    {
        String apiUrl = "/users/user/102";

        Mockito.when(userService.findUserById(102, false)).thenReturn(userList.get(1));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(1));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }
        @Test
    public void getUserByIdNotFound() throws Exception
    {
        String apiUrl = "/users/user/77";

        Mockito.when(userService.findUserById(77,false)).thenReturn(null);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        String er = "";

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }


    @Test
    public void getUserByName() throws Exception
    {
                String apiUrl = "/users/name/testing";

        Mockito.when(userService.findByName("testing",false)).thenReturn(userList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(0));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getCurrentUserName()
    {
        // requires security which we have turned off for unit test
        // refer to integration testing for test of this method
    }

    @Test
    public void addNewUser() throws Exception
    {
        String apiUrl = "/users/newuser";

        // build a restaurant
        ArrayList<UserRoles> thisRole = new ArrayList<>();
        ArrayList<UserContact> thisEmail = new ArrayList<>();
        User u1 = new User();
        u1.setUserid(100);
        u1.setUsername("tiger");
        u1.setPassword("ILuvM4th!");
        u1.setFname("Test");
        u1.setLname("Test");
        u1.setBusname("Test");
        u1.setUserroles(thisRole);
        u1.setUserContacts(thisEmail);

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(u1);

        Mockito.when(userService.save(any(User.class))).thenReturn(u1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(userString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUser() throws Exception
    {
        String apiUrl = "/users/user/{userid}";

        User u1 = new User();
        u1.setUserid(100);
        u1.setUsername("tigerUpdated");
        u1.setPassword("ILuvM4th!");
        u1.setFname("Test");
        u1.setLname("Test");
        u1.setBusname("Test");

        Mockito.when(userService.update(u1, 100L, true)).thenReturn(u1);

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(u1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 100L)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(userString);

        mockMvc.perform(rb).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserById() throws Exception
    {
        String apiUrl = "/users/delete/{userid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserRoleByIds() throws Exception
    {
        String apiUrl = "/users/user/{userid}/role/{roleid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, 3, 2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postUserRoleByIds() throws Exception
    {
        String apiUrl = "/users/{userid}/role/{roleid}";
        ArrayList<UserRoles> mySaved = new ArrayList<>();
        User u1 = new User();
        SavedContacts s1 = new SavedContacts();
        u1.setUserid(100);
        u1.setUsername("tigerUpdated");
        u1.setPassword("ILuvM4th!");
        u1.setFname("Test");
        u1.setLname("Test");
        u1.setBusname("Test");
        u1.setUserroles(mySaved);

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(u1);

        Mockito.when(userService.save(any(User.class))).thenReturn(u1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl,100,1 )
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(userString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postSavedContactsByIds() throws Exception
    {
        String apiUrl = "/users/{userid}/contact/{contactid}";
        ArrayList<SavedContacts> mySaved = new ArrayList<>();
        User u1 = new User();
        SavedContacts s1 = new SavedContacts();
        u1.setUserid(100);
        u1.setUsername("tigerUpdated");
        u1.setPassword("ILuvM4th!");
        u1.setFname("Test");
        u1.setLname("Test");
        u1.setBusname("Test");
        u1.setSavedContacts(mySaved);

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(u1);

        Mockito.when(userService.save(any(User.class))).thenReturn(u1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl,100,1 )
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(userString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserContactById() throws Exception
    {
        String apiUrl = "/users/{userid}/contact/{contactid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, 101, 11)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}