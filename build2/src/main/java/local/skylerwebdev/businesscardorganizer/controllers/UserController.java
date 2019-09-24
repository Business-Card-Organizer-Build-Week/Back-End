package local.skylerwebdev.businesscardorganizer.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import local.skylerwebdev.businesscardorganizer.models.ErrorDetail;
import local.skylerwebdev.businesscardorganizer.models.User;
import local.skylerwebdev.businesscardorganizer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Return All Users", response = User.class, responseContainer = "List")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/all",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @ApiOperation(value = "Return User Based on Id", response = User.class, responseContainer = "List")
    @GetMapping(value = "/user/{userId}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserById(HttpServletRequest request,
                                         @PathVariable
                                                 Long userId)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findUserById(userId, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @ApiOperation(value = "Return User Based on User Name", response = User.class, responseContainer = "List")
    @GetMapping(value = "/name/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserByName(HttpServletRequest request,
                                           @PathVariable
                                                   String userName)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findByName(userName, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @ApiOperation(value = "Return Current User", response = User.class, responseContainer = "List")
    @GetMapping(value = "/getusername",
                produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(HttpServletRequest request, Authentication authentication)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }


    @ApiOperation(value = "Creates a new User", notes = "Newly Created user id sent in response header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Created Successfully", response = void.class),
            @ApiResponse(code = 404, message = "Not a valid method for endpoint", response = void.class),
            @ApiResponse(code = 500, message = "ERROR Creating User", response = ErrorDetail.class)
    })

    @PostMapping(value = "/newuser",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                                                    .path("/{userid}")
                                                    .buildAndExpand(newuser.getUserid())
                                                    .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates A user Based on ID", response = User.class)
    @ApiResponse(code = 200, message = "User Updated Successfully", response = void.class)
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long id)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.update(updateUser, id, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes User Based on Id",  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Deleted Successfully", response = void.class),
    })
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request,
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "Deletes User Role Based on Id",  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student Deleted Successfully", response = void.class),
    })
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> deleteUserRoleByIds(HttpServletRequest request,
                                                 @PathVariable
                                                         long userid,
                                                 @PathVariable
                                                         long roleid)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.deleteUserRole(userid, roleid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "Adds a Role to a User based on ID", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Role Added Successfully", response = void.class),
            @ApiResponse(code = 404, message = "Role id _ not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "ERROR Adding Role", response = ErrorDetail.class)
    })
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{userid}/role/{roleid}")
    public ResponseEntity<?> postUserRoleByIds(HttpServletRequest request,
                                               @PathVariable
                                                       long userid,
                                               @PathVariable
                                                       long roleid)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.addUserRole(userid, roleid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @ApiOperation(value = "Adds a Contact to Saved Contacts based on contactid", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Saved Contact Added Successfully", response = void.class),
            @ApiResponse(code = 500, message = "ERROR Adding Saved Contact", response = ErrorDetail.class)
    })
    @PostMapping("/{userid}/contact/{contactid}")
    public ResponseEntity<?> postSavedContactsByIds(HttpServletRequest request,
                                               @PathVariable
                                                       long userid,
                                               @PathVariable
                                                       int contactid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.addSavedContact(userid, contactid, request.isUserInRole("ADMIN"));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @ApiOperation(value = "Deletes Saved User Contact based on id on Id",  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Saved Contact Deleted Successfully", response = void.class),
    })
    @DeleteMapping("/{userid}/contact/{contactid}")
    public ResponseEntity<?> deleteUserContactById(HttpServletRequest request,
                                                 @PathVariable
                                                         long userid,
                                                 @PathVariable
                                                         long contactid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.deleteSavedContact(userid, contactid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}