//package local.skylerwebdev.businesscardorganizer.controllers;
//
//import local.skylerwebdev.businesscardorganizer.models.UserPhone;
//import local.skylerwebdev.businesscardorganizer.services.UserPhoneService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/userphones")
//public class UserPhoneController
//{
//    private static final Logger logger = LoggerFactory.getLogger(UserPhoneController.class);
//
//    @Autowired
//    UserPhoneService userphoneService;
//
//    @GetMapping(value = "/userphones",
//                produces = {"application/json"})
//    public ResponseEntity<?> listAllUserPhones(HttpServletRequest request)
//    {
//        logger.trace(request.getMethod()
//                            .toUpperCase() + " " + request.getRequestURI() + " accessed");
//
//        List<UserPhone> allEmails = userphoneService.findAll();
//        return new ResponseEntity<>(allEmails, HttpStatus.OK);
//    }
//
//
//    @GetMapping(value = "/userphone/{userphoneId}",
//                produces = {"application/json"})
//    public ResponseEntity<?> getUserEmailById(HttpServletRequest request,
//                                              @PathVariable
//                                              Long userphoneId)
//    {
//        logger.trace(request.getMethod()
//                            .toUpperCase() + " " + request.getRequestURI() + " accessed");
//
//        UserPhone ue = userphoneService.findUserPhoneById(userphoneId);
//        return new ResponseEntity<>(ue, HttpStatus.OK);
//    }
//
//
//    @GetMapping(value = "/username/{userName}",
//                produces = {"application/json"})
//    public ResponseEntity<?> findEmailByUserName(HttpServletRequest request,
//                                                 @PathVariable
//                                                         String userName)
//    {
//        logger.trace(request.getMethod()
//                            .toUpperCase() + " " + request.getRequestURI() + " accessed");
//
//        List<UserPhone> theUserPhones = userphoneService.findByUserName(userName);
//        return new ResponseEntity<>(theUserPhones, HttpStatus.OK);
//    }
//
//
//    @PostMapping(value = "/userphone")
//    public ResponseEntity<?> addNewEmail(HttpServletRequest request, @Valid
//    @RequestBody
//            UserPhone newUserPhone) throws URISyntaxException
//    {
//        logger.trace(request.getMethod()
//                            .toUpperCase() + " " + request.getRequestURI() + " accessed");
//
//        newUserPhone = userphoneService.save(newUserPhone, request.isUserInRole("ADMIN"));
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newUserPhoneURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                                                     .path("/{userphoneid}")
//                                                     .buildAndExpand(newUserPhone.getUserphoneid())
//                                                     .toUri();
//        responseHeaders.setLocation(newUserPhoneURI);
//
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//    }
//
//
//    @DeleteMapping("/userphone/{userphoneid}")
//    public ResponseEntity<?> deleteEmailById(HttpServletRequest request,
//                                             @PathVariable
//                                                     long userphoneid)
//    {
//        logger.trace(request.getMethod()
//                            .toUpperCase() + " " + request.getRequestURI() + " accessed");
//
//        userphoneService.delete(userphoneid, request.isUserInRole("ADMIN"));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
