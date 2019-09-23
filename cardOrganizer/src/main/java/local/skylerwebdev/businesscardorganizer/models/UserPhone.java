//package local.skylerwebdev.businesscardorganizer.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@IdClass(UserPhone.class)
//@Table(name = "userphones")
//public class UserPhone extends Auditable implements Serializable
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long userphoneid;
//
//    @Column(nullable = false)
//    private String userphone;
//
//    @ManyToOne
//    @JoinColumn(name = "userid",
//                nullable = false)
//    @JsonIgnoreProperties("userphones")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "phonetypeid")
//    @JsonIgnoreProperties("userphones")
//    private PhoneContactType phonecontacttype;
//
//    public UserPhone()
//    {
//    }
//
//    public UserPhone(String userphone, User user, PhoneContactType phonecontacttype)
//    {
//        this.userphone = userphone;
//        this.user = user;
//        this.phonecontacttype = phonecontacttype;
//    }
//
//    public long getUserphoneid()
//    {
//        return userphoneid;
//    }
//
//    public void setUserphoneid(long userphoneid)
//    {
//        this.userphoneid = userphoneid;
//    }
//
//    public String getUserphone()
//    {
//        return userphone;
//    }
//
//    public void setUserphone(String userphone)
//    {
//        this.userphone = userphone;
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
//    public PhoneContactType getPhonecontacttype()
//    {
//        return phonecontacttype;
//    }
//
//    public void setPhonecontacttype(PhoneContactType phonecontacttype)
//    {
//        this.phonecontacttype = phonecontacttype;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserPhone userPhone = (UserPhone) o;
//        return userphoneid == userPhone.userphoneid &&
//                Objects.equals(userphone, userPhone.userphone) &&
//                Objects.equals(user, userPhone.user) &&
//                Objects.equals(phonecontacttype, userPhone.phonecontacttype);
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(userphoneid, userphone, user, phonecontacttype);
//    }
//
//    @Override
//    public String toString()
//    {
//        return "UserPhone{" +
//                "userphoneid=" + userphoneid +
//                ", userphone='" + userphone + '\'' +
//                ", user=" + user +
//                ", phonecontacttype=" + phonecontacttype +
//                '}';
//    }
//}
