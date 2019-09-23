//package local.skylerwebdev.businesscardorganizer.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "phonecontacttypes")
//public class PhoneContactType
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long phonetypeid;
//
//    @Column(nullable = false,
//            unique = true)
//    private String phonecontacttype;
//
//    @OneToMany(mappedBy = "phonecontacttype", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("phonecontacttype")
//    private List<UserPhone> userphones = new ArrayList<>();
//
//    public PhoneContactType()
//    {
//    }
//
//    public PhoneContactType(String phonecontacttype)
//    {
//        this.phonecontacttype = phonecontacttype;
//    }
//
//    public long getPhonetypeid()
//    {
//        return phonetypeid;
//    }
//
//    public void setPhonetypeid(long phonetypeid)
//    {
//        this.phonetypeid = phonetypeid;
//    }
//
//    public String getPhonecontacttype()
//    {
//        return phonecontacttype;
//    }
//
//    public void setPhonecontacttype(String phonecontacttype)
//    {
//        this.phonecontacttype = phonecontacttype;
//    }
//
//    public List<UserPhone> getUserphones()
//    {
//        return userphones;
//    }
//
//    public void setUserphones(List<UserPhone> userphones)
//    {
//        this.userphones = userphones;
//    }
//}
//
//
