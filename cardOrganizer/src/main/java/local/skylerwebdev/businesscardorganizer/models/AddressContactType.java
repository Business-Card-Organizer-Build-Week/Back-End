//package local.skylerwebdev.businesscardorganizer.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "addresscontacttypes")
//public class AddressContactType
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long addresstypeid;
//
//    @Column(nullable = false,
//            unique = true)
//    private String addresscontacttype;
//
//    @OneToMany(mappedBy = "addresscontacttype", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("addresscontacttype")
//    private List<UserAddress> useraddresses = new ArrayList<>();
//
//    public AddressContactType()
//    {
//    }
//
//    public AddressContactType(String addresscontacttype)
//    {
//        this.addresscontacttype = addresscontacttype;
//    }
//
//    public long getAddresstypeid()
//    {
//        return addresstypeid;
//    }
//
//    public void setAddresstypeid(long addresstypeid)
//    {
//        this.addresstypeid = addresstypeid;
//    }
//
//    public String getAddresscontacttype()
//    {
//        return addresscontacttype;
//    }
//
//    public void setAddresscontacttype(String addresscontacttype)
//    {
//        this.addresscontacttype = addresscontacttype;
//    }
//
//    public List<UserAddress> getUseraddresses()
//    {
//        return useraddresses;
//    }
//
//    public void setUseraddresses(List<UserAddress> useraddresses)
//    {
//        this.useraddresses = useraddresses;
//    }
//}
//
//
