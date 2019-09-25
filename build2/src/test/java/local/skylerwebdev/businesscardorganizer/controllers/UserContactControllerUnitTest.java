package local.skylerwebdev.businesscardorganizer.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import local.skylerwebdev.businesscardorganizer.models.*;
import local.skylerwebdev.businesscardorganizer.services.UserContactService;
import local.skylerwebdev.businesscardorganizer.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import springfox.documentation.service.Contact;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserContactController.class, secure = false)
public class UserContactControllerUnitTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
//    private UserService userService;
    private UserContactService userContactService;
    @MockBean
    private UserService userService;

    private List<User> userList;
    private List<UserContact> contactList;
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
        contactList = new ArrayList<>();
        UserContact ct1test = new UserContact("test@test.com","1234567891","TestAddress","TestCity","TS","5555555",u5,ct1);
        ct1test.setContactid(100);
        contactList.add(ct1test);
//        System.out.println(contactList);
        System.out.println("\n*** Seed Data ***");

    }

    @Test
    public void listAllUserContacts() throws Exception
    {
        String apiUrl = "/contact/all";

        Mockito.when(userContactService.findAll()).thenReturn(contactList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(contactList);
        System.out.println("expect"+er);
        System.out.println("actual"+tr);
        assertEquals("RestApiReturns List", er,tr);

    }

    @Test
    public void getUserContactsById() throws Exception
    {
        String apiUrl = "/contact/100";

        Mockito.when(userContactService.findUserContactById(100)).thenReturn(contactList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(contactList.get(0));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void deleteContactById() throws Exception
    {
        {
            String apiUrl = "/contact/delete/{contactid}";

            RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "100")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON);
            mockMvc.perform(rb)
                    .andExpect(status().is2xxSuccessful())
                    .andDo(MockMvcResultHandlers.print());
        }
    }

    @Test
    public void updateContact() throws Exception
    {
        String apiUrl = "/contact/{contactid}";
        UserContact ct1 = new UserContact();
        ct1.setUserzip("33555");

        Mockito.when(userContactService.update(ct1, 100)).thenReturn(ct1);
        ObjectMapper mapper = new ObjectMapper();
        String contactString = mapper.writeValueAsString(ct1);
        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 100).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(contactString);
        mockMvc.perform(rb).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }
}