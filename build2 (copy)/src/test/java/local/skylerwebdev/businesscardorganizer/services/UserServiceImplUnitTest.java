package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.BusinesscardorganizerApplication;
import local.skylerwebdev.businesscardorganizer.exceptions.ResourceFoundException;
import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
import local.skylerwebdev.businesscardorganizer.models.User;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.UserContactType;
import local.skylerwebdev.businesscardorganizer.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinesscardorganizerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplUnitTest
{

    @Autowired
    UserService userService;
    @Autowired
    ContactTypeService contactTypeService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void A_loadUserByUsername()
    {
        assertEquals("admin", userService.loadUserByUsername("admin").getUsername());
    }

    @Test
    @WithUserDetails("admin")
    public void B_findUserById()
    {
        assertEquals("admin", userService.findUserById(7, true).getUsername());
    }

    @Test
    public void C_findAll()
    {
        assertEquals(5, userService.findAll().size());
    }

    @Test
    public void D_delete()
    {
        userService.delete(17);
        assertEquals(4, userService.findAll().size());
    }

    @Test
    @WithUserDetails("admin")
    public void E_findByName()
    {
        assertEquals("admin", userService.findByName("admin", true).getUsername());
    }

    @Test
    @WithUserDetails("admin")
    public void F_save()
    {
        //converts to lowercase and strips whitespace in username
        UserContactType ct1 = contactTypeService.findContactTypeById(4);
        ArrayList<UserRoles> datas = new ArrayList<>();

        User u2 = new User("Jane Tester",  "John", "Smith", "TestBusName", "Title","password", datas);
        u2.getUserContacts()
                .add(new UserContact("test@test.com",u2.getFname(),u2.getLname(),u2.getBusname(), "5555555555", "TestAddress", "Test City", "ST", "55555", u2, ct1));
        User adduser = userService.save(u2);
        System.out.println(u2.getUsername());
        System.out.println(adduser.getUserid());
        assertNotNull(adduser);
        User foundUser = userService.findByName(u2.getUsername().toLowerCase().replaceAll("\\s+",""), true);
        assertEquals("janetester", foundUser.getUsername().toLowerCase().replaceAll("\\s+",""));

    }

    @Test
    @WithUserDetails("cinnamon")
    public void G_update()
    {
        UserContactType ct2 = contactTypeService.findContactTypeById(5);
        ArrayList<UserRoles> datas = new ArrayList<>();
        User u2 = new User("cinnamon",  "Jane", "Smith", "TestBusName", "Title","password", datas);
        u2.getUserContacts()
                .add(new UserContact("test@test.com",u2.getFname(),u2.getLname(),u2.getBusname(), "5555555555", "TestAddress", "Test City", "ST", "55555", u2, ct2));
        User updateU2 = userService.update(u2, 10, false);
        assertNotNull(updateU2);
        assertEquals("Jane", updateU2.getFname());
    }
    @Test (expected = ResourceNotFoundException.class)
    public void H_deleteUserRoleComboNotFound()
    {
        // testing cinnamon and user roles
        userService.deleteUserRole(11, 2);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void HA_deleteUserRoleRoleNotFound()
    {
        userService.deleteUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void HB_deleteUserRoleUserNotFound()
    {
        userService.deleteUserRole(50, 2);
    }
    @Test(expected = ResourceFoundException.class)
    public void IA_addUserRoleUserRoleFound()
    {
        userService.addUserRole(7, 1);
    }
    @Test
    public void IB_deleteUserRole()
    {
        userService.deleteUserRole(7, 1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void IC_addUserRoleRoleNotFound()
    {
        userService.addUserRole(7, 50);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void ID_addUserRoleUserNotFound()
    {
        userService.addUserRole(50, 2);
    }

    @Test
    public void IE_addUserRole()
    {
        userService.addUserRole(14, 1);
    }

    @Test
    @WithUserDetails("admin")
    public void JA_addSavedContact()
    {
        userService.addSavedContact(7,13,false);
    }

    @Test
    @WithUserDetails("admin")
    public void K_deleteSavedContact()
    {
        userService.deleteSavedContact(7,13);

    }
}