//package local.skylerwebdev.businesscardorganizer.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@IdClass(UserAddress.class)
//@Table(name = "useraddresses")
//public class UserAddress extends Auditable implements Serializable
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long addressid;
//
//    private String address1;
//    @Column(nullable = true)
//    private String address2;
//
//    private String city;
//
//    private String state;
//
//    private long zip;
//
//    @ManyToOne
//    @JoinColumn(name = "userid",
//                nullable = false)
//    @JsonIgnoreProperties("useraddresses")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "addresstypeid")
//    @JsonIgnoreProperties("useraddresses")
//    private AddressContactType addresscontacttype;
//
//    public UserAddress()
//    {
//    }
//
//    public UserAddress(String address1, String address2, String city, String state, long zip, User user, AddressContactType addresscontacttype)
//    {
//        this.address1 = address1;
//        this.address2 = address2;
//        this.city = city;
//        this.state = state;
//        this.zip = zip;
//        this.user = user;
//        this.addresscontacttype = addresscontacttype;
//    }
//
//    public long getAddressid()
//    {
//        return addressid;
//    }
//
//    public void setAddressid(long addressid)
//    {
//        this.addressid = addressid;
//    }
//
//    public String getAddress1()
//    {
//        return address1;
//    }
//
//    public void setAddress1(String address1)
//    {
//        this.address1 = address1;
//    }
//
//    public String getAddress2()
//    {
//        return address2;
//    }
//
//    public void setAddress2(String address2)
//    {
//        this.address2 = address2;
//    }
//
//    public String getCity()
//    {
//        return city;
//    }
//
//    public void setCity(String city)
//    {
//        this.city = city;
//    }
//
//    public String getState()
//    {
//        return state;
//    }
//
//    public void setState(String state)
//    {
//        this.state = state;
//    }
//
//    public long getZip()
//    {
//        return zip;
//    }
//
//    public void setZip(long zip)
//    {
//        this.zip = zip;
//    }
//
//    public User getUser()
//    {
//        return user;
//    }
//
//    public void setUser(User user)
//    {
//        this.user = user;
//    }
//
//    public AddressContactType getAddresscontacttype()
//    {
//        return addresscontacttype;
//    }
//
//    public void setAddresscontacttype(AddressContactType addresscontacttype)
//    {
//        this.addresscontacttype = addresscontacttype;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserAddress that = (UserAddress) o;
//        return addressid == that.addressid &&
//                zip == that.zip &&
//                Objects.equals(address1, that.address1) &&
//                Objects.equals(address2, that.address2) &&
//                Objects.equals(city, that.city) &&
//                Objects.equals(state, that.state) &&
//                Objects.equals(user, that.user) &&
//                Objects.equals(addresscontacttype, that.addresscontacttype);
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(addressid, address1, address2, city, state, zip, user, addresscontacttype);
//    }
//
//    @Override
//    public String toString()
//    {
//        return "UserAddress{" +
//                "addressid=" + addressid +
//                ", address1='" + address1 + '\'' +
//                ", address2='" + address2 + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", zip=" + zip +
//                ", user=" + user +
//                ", addresscontacttype=" + addresscontacttype +
//                '}';
//    }
//}
