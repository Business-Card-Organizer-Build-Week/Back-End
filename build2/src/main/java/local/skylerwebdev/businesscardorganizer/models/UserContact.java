package local.skylerwebdev.businesscardorganizer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usercontact")
@IdClass(UserContact.class)
public class UserContact extends Auditable implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contactid;

    @Column(nullable = false)
    @Email
    private String useremail;

    private String userphone;

    private String useraddress;

    private String usercity;

    private String userState;

    private String userzip;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("useremails")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contacttypeid")
    @JsonIgnoreProperties("usercontacts")
    private UserContactType usercontacttype;

    public UserContact()
    {
    }

    public UserContact(@Email String useremail, String userphone, String useraddress, String usercity, String userState, String userzip, User user, UserContactType usercontacttype)
    {
        this.useremail = useremail;
        this.userphone = userphone;
        this.useraddress = useraddress;
        this.usercity = usercity;
        this.userState = userState;
        this.userzip = userzip;
        this.user = user;
        this.usercontacttype = usercontacttype;
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

    public String getUseraddress()
    {
        return useraddress;
    }

    public void setUseraddress(String useraddress)
    {
        this.useraddress = useraddress;
    }

    public String getUsercity()
    {
        return usercity;
    }

    public void setUsercity(String usercity)
    {
        this.usercity = usercity;
    }

    public String getUserState()
    {
        return userState;
    }

    public void setUserState(String userState)
    {
        this.userState = userState;
    }

    public String getUserzip()
    {
        return userzip;
    }

    public void setUserzip(String userzip)
    {
        this.userzip = userzip;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public UserContactType getUsercontacttype()
    {
        return usercontacttype;
    }

    public void setUsercontacttype(UserContactType usercontacttype)
    {
        this.usercontacttype = usercontacttype;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserContact that = (UserContact) o;
        return contactid == that.contactid &&
                Objects.equals(useremail, that.useremail) &&
                Objects.equals(userphone, that.userphone) &&
                Objects.equals(useraddress, that.useraddress) &&
                Objects.equals(usercity, that.usercity) &&
                Objects.equals(userState, that.userState) &&
                Objects.equals(userzip, that.userzip) &&
                Objects.equals(user, that.user) &&
                Objects.equals(usercontacttype, that.usercontacttype);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(contactid, useremail, userphone, useraddress, usercity, userState, userzip, user, usercontacttype);
    }

    @Override
    public String toString()
    {
        return "UserContact{" +
                "contactid=" + contactid +
                ", useremail='" + useremail + '\'' +
                ", userphone='" + userphone + '\'' +
                ", useraddress='" + useraddress + '\'' +
                ", usercity='" + usercity + '\'' +
                ", userState='" + userState + '\'' +
                ", userzip='" + userzip + '\'' +
                ", user=" + user +
                ", usercontacttype=" + usercontacttype +
                '}';
    }
}