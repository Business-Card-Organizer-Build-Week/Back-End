//package local.skylerwebdev.businesscardorganizer.controllers;
//
//import local.skylerwebdev.businesscardorganizer.models.SavedContacts;
//import local.skylerwebdev.businesscardorganizer.services.SavedContactService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.sql.Statement;
//import java.util.List;
//
//@RestController
//@RequestMapping("/saved")
//public class SavedController
//{
//    private static final Logger logger = LoggerFactory.getLogger(SavedController.class);
//
//    @Autowired
//    SavedContactService savedContactService;
//
//    @GetMapping(value = "/all", produces = {"application/json"})
//    public ResponseEntity<?> listAllSavedContacts(HttpServletRequest request)
//    {
//        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");
//        List<SavedContacts> savedContacts = savedContactService.findAll();
//        return new ResponseEntity<>(savedContacts, HttpStatus.OK);
//    }
//}
