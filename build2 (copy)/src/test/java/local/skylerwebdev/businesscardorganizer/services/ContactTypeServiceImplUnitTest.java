package local.skylerwebdev.businesscardorganizer.services;

import local.skylerwebdev.businesscardorganizer.BusinesscardorganizerApplication;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.models.UserContactType;
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
public class ContactTypeServiceImplUnitTest
{
    @Autowired
    ContactTypeService contactTypeService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void A_findContactTypeById()
    {
        assertEquals("Business", contactTypeService.findContactTypeById(4).getContacttype());
    }

    @Test
    public void B_save()
    {
        UserContactType newType = new UserContactType();
        newType.setContacttype("TestType");
        assertNotNull(newType);
        contactTypeService.save(newType);
        System.out.println(newType);
        assertEquals("TestType", contactTypeService.findContactTypeById(18).getContacttype());
    }
}