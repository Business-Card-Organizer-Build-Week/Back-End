package local.skylerwebdev.businesscardorganizer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(UserContact.class)
@Table(name = "usercontacts")
public class UserContact extends Auditable implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contactid;

    @Column(nullable = false)
    @Email
    private String useremail;

    @Column(nullable = false)
    private String userphone;

    private String address1;
    @Column(nullable = true)
    private String address2;

    private String city;

    private String state;

    private String zip;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("usercontact")
    private User user;

    @ManyToOne
    @JoinColumn(name = "typeid")
    @JsonIgnoreProperties("contacts")
    private ContactType contacttype;

    public UserContact()
    {
    }



    public UserContact(String useremail, String userphone, String address1, String address2, String city, String state, String zip, User user, ContactType contacttype)
    {
        this.useremail = useremail;
        this.userphone = userphone;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.user = user;
        this.contacttype = contacttype;
    }


    public long getContactid()
    {
        return contactid;
    }

    public void setContactid(long contactid)
    {
        this.contactid = contactid;
    }

    public String getUseremail()
    {
        return useremail;
    }

    public void setUseremail(String useremail)
    {
        this.useremail = useremail;
    }

    public String getUserphone()
    {
        return userphone;
    }

    public void setUserphone(String userphone)
    {
        this.userphone = userphone;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public ContactType getContacttype()
    {
        return contacttype;
    }

    public void setContacttype(ContactType contacttype)
    {
        this.contacttype = contacttype;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserContact userContact = (UserContact) o;
        return contactid == userContact.contactid &&
                zip == userContact.zip &&
                Objects.equals(useremail, userContact.useremail) &&
                Objects.equals(userphone, userContact.userphone) &&
                Objects.equals(address1, userContact.address1) &&
                Objects.equals(address2, userContact.address2) &&
                Objects.equals(city, userContact.city) &&
                Objects.equals(state, userContact.state) &&
                Objects.equals(user, userContact.user) &&
                Objects.equals(contacttype, userContact.contacttype);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(contactid, useremail, userphone, address1, address2, city, state, zip, user, contacttype);
    }

    @Override
    public String toString()
    {
        return "Contact{" +
                "contactid=" + contactid +
                ", useremail='" + useremail + '\'' +
                ", userphone='" + userphone + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", user=" + user +
                ", contacttype=" + contacttype +
                '}';
    }
}