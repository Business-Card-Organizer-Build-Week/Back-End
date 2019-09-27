package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.BusinesscardorganizerApplication;
import local.skylerwebdev.businesscardorganizer.models.Role;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinesscardorganizerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceImplUnitTest
{

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void A_findAll()
    {
        assertEquals(3, roleService.findAll().size());
    }

    @Test
    public void B_findRoleById()
    {
        assertEquals("user", roleService.findRoleById(2).getName());
    }

    @Test
    public void C_findByName()
    {
        assertEquals(2, roleService.findByName("user").getRoleid());
    }

    @Test
    public void D_delete()
    {
        roleService.delete(2);
        assertEquals(2, roleService.findAll().size());
    }

    @Test
    public void E_save()
    {
        Role newRole = new Role();
        newRole.setName("TestRole");
        assertNotNull(newRole);
        roleService.save(newRole);
        assertEquals("TestRole", roleService.findByName("TestRole").getName());
        assertEquals(3, roleService.findAll().size());
    }
}