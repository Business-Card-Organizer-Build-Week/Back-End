package local.skylerwebdev.businesscardorganizer.controllers;

import local.skylerwebdev.businesscardorganizer.models.UserContact;
import local.skylerwebdev.businesscardorganizer.services.UserContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/useremails")
public class UserContactController
{
    private static final Logger logger = LoggerFactory.getLogger(UserContactController.class);

    @Autowired
    UserContactService userContactService;

    @GetMapping(value = "/useremails",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUseremails(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserContact> allQuotes = userContactService.findAll();
        return new ResponseEntity<>(allQuotes, HttpStatus.OK);
    }


    @GetMapping(value = "/useremail/{useremailId}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserEmailById(HttpServletRequest request,
                                              @PathVariable
                                              Long useremailId)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        UserContact ue = userContactService.findUseremailById(useremailId);
        return new ResponseEntity<>(ue, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findQuoteByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<UserContact> theUserContacts = userContactService.findByUserName(userName);
        return new ResponseEntity<>(theUserContacts, HttpStatus.OK);
    }


    @PostMapping(value = "/useremail")
    public ResponseEntity<?> addNewQuote(HttpServletRequest request, @Valid
    @RequestBody
            UserContact newUserContact) throws URISyntaxException
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        newUserContact = userContactService.save(newUserContact, request.isUserInRole("ADMIN"));

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUseremailURI = ServletUriComponentsBuilder.fromCurrentRequest()
                                                     .path("/{useremailid}")
                                                     .buildAndExpand(newUserContact.getContactid())
                                                     .toUri();
        responseHeaders.setLocation(newUseremailURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/useremail/{useremailid}")
    public ResponseEntity<?> deleteQuoteById(HttpServletRequest request,
                                             @PathVariable
                                                     long useremailid)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userContactService.delete(useremailid, request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
