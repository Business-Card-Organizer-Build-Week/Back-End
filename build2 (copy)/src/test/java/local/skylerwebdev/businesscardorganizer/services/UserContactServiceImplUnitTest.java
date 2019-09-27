package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.BusinesscardorganizerApplication;
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
public class UserContactServiceImplUnitTest
{

    @Autowired
    UserContactService userContactService;
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
    public void A_findAll()
    {
        assertEquals(6, userContactService.findAll().size());
    }

    @Test
    @WithUserDetails("admin")
    public void B_findUserContactById()
    {
        assertEquals("test@test.com", userContactService.findUserContactById(15).getUseremail());
    }

   @Test
   @WithUserDetails("admin")
   public void C_delete()
    {
        userContactService.delete(15, true);
        assertEquals(5, userContactService.findAll().size());
    }

    @Test
    @WithUserDetails("admin")
    public void D_update()
    {
        UserContactType ct1 = contactTypeService.findContactTypeById(4);
        User u1 = userService.findUserById(7, true);

        UserContact uc1 = new UserContact("test@checktest.com",u1.getFname(),u1.getLname(),u1.getBusname(), "5555555555", "TestAddress", "Test City", "ST", "55555",u1 ,ct1);

        UserContact updateUser = userContactService.update(uc1, 9);
        assertNotNull(updateUser);
        assertEquals("test@checktest.com", updateUser.getUseremail());
    }
}