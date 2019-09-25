package local.skylerwebdev.businesscardorganizer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import local.skylerwebdev.businesscardorganizer.exceptions.ResourceFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest
{
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }
    @WithUserDetails("barnbarn")
    @Test
    public void A_whenMeasuredResponseTime()
    {
        given().when()
                .get("/users/users")
                .then()
                .time(lessThan(5000L));
    }
    @WithUserDetails("admin")
    @Test
    public void BA_getAllUsers() throws Exception
    {
        this.mockMvc.perform(get("/users/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("admin")));
    }
    @WithUserDetails("barnbarn")
    @Test(expected = AssertionError.class)
    public void BB_getAllUsersNotLoggedIn() throws Exception
    {
        this.mockMvc.perform(get("/users/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithUserDetails("admin")
    @Test
    public void C_getUserById() throws Exception
    {
        this.mockMvc.perform(get("/users/user/{userid}", 14))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("barnbarn")));
    }
    @WithUserDetails("barnbarn")
    @Test
    public void C_getUserByIdWhenNotAdmin() throws Exception
    {
        this.mockMvc.perform(get("/users/user/{userid}", 14))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("barnbarn")));
    }
    @WithUserDetails("cinnamon")
    @Test(expected = ResourceFoundException.class)
    public void C_getUserByIdWhenWrongUser() throws Exception
    {
        this.mockMvc.perform(get("/users/user/{userid}", 14))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
