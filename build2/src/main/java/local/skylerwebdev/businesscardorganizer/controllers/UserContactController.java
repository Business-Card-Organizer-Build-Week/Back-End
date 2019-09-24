package local.skylerwebdev.businesscardorganizer.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import local.skylerwebdev.businesscardorganizer.models.User;
import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.services.UserContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class UserContactController
{
    private static final Logger logger = LoggerFactory.getLogger(UserContactController.class);

    @Autowired
    UserContactService userContactService;

    @ApiOperation(value = "LISTS ALL CONTACTS ***LOGGED IN AS ADMIN***", response = UserContact.class, responseContainer = "List")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/all",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUserContacts(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserContact> allQuotes = userContactService.findAll();
        return new ResponseEntity<>(allQuotes, HttpStatus.OK);
    }

    @ApiOperation(value = "LISTS CONTACT BY ID ***LOGGED IN AS A USER***", response = UserContact.class, responseContainer = "List")
    @GetMapping(value = "/{id}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserContactsById(HttpServletRequest request,
                                              @PathVariable
                                              Long id)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        UserContact ue = userContactService.findUserContactById(id);
        return new ResponseEntity<>(ue, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes Contact Based on Id. ***Must be Done by logged in user or Admin***",  response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contact Deleted Successfully", response = void.class),
            @ApiResponse(code = 404, message = "Usercontact with id _ not found or not Authorized to make change", response = void.class)
    })
    @DeleteMapping("/delete/{usercontactid}")
    public ResponseEntity<?> deleteContactById(HttpServletRequest request,
                                             @PathVariable
                                                     long usercontactid)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userContactService.delete(usercontactid, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{contactid}")
    public ResponseEntity<?> updateContact(HttpServletRequest request,
                                        @RequestBody
                                                UserContact userContact,
                                        @PathVariable
                                                long contactid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");
        System.out.println(contactid);
        userContactService.update(userContact, contactid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
