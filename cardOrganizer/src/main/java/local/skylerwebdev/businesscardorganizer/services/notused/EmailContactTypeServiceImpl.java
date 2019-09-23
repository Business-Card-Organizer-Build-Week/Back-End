//package local.skylerwebdev.businesscardorganizer.services;
//
//
//import local.skylerwebdev.businesscardorganizer.exceptions.ResourceNotFoundException;
//import local.skylerwebdev.businesscardorganizer.models.EmailContactType;
//import local.skylerwebdev.businesscardorganizer.models.User;
//import local.skylerwebdev.businesscardorganizer.models.UserPhone;
//import local.skylerwebdev.businesscardorganizer.models.Useremail;
//import local.skylerwebdev.businesscardorganizer.repository.EmailContactTypeRepository;
//import local.skylerwebdev.businesscardorganizer.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service(value = "emailContactTypeService")
//public class EmailContactTypeServiceImpl implements EmailContactTypeService
//{
//    @Autowired
//    EmailContactTypeRepository emailContactTypeRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public List<EmailContactType> findAll()
//    {
//        return null;
//    }
//
//    @Override
//    public EmailContactType findContactTypeById(long id)
//    {
//        return null;
//    }
//
//    @Override
//    public void delete(long id)
//    {
//
//    }
//
//    @Override
//    public EmailContactType save(EmailContactType emailContactType)
//    {
//        EmailContactType newEmailContactType = new EmailContactType();
//        newEmailContactType.setEmailcontacttype(emailContactType.getEmailcontacttype());
//
//        ArrayList<Useremail> newContactTypes = new ArrayList<>();
//        for(Useremail ue : emailContactType.getUseremails())
//        {
//            long id = ue.getUser().getUserid();
//            User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
//            newContactTypes.add(new Useremail(ue.getUser(), ue.getUseremail(), newEmailContactType));
//        }
//        newEmailContactType.setUseremails(newContactTypes);
//
//
//
//        return emailContactTypeRepository.save(emailContactType);
//    }
//
//    @Override
//    public EmailContactType findByName(String name)
//    {
//        return null;
//    }
//}
