package local.skylerwebdev.businesscardorganizer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usercontact")
//@IdClass(UserContact.class)
public class UserContact extends Auditable implements Serializable
{
    @ApiModelProperty(name = "contactid", value = "Primary Key for UserContact Auto Generated", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contactid;

    @ApiModelProperty(name = "useremail", value = "Contact Email Address", required = true, example = "test@test.com")
    @Column(nullable = false)
    @Email
    private String useremail;

    @ApiModelProperty(name = "userphone", value = "Contact Phone Number", required = false, example = "555-555-5555")
    private String userphone;

    @ApiModelProperty(name = "useraddress", value = "Contact Address", required = false, example = "123 Main Street")
    private String useraddress;

    @ApiModelProperty(name = "usercity", value = "Contact City", required = false, example = "City")
    private String usercity;

    @ApiModelProperty(name = "userstate", value = "Contact State", required = false, example = "ST")
    private String userState;

    @ApiModelProperty(name = "userzip", value = "Contact Zip", required = false, example = "55555")
    private String userzip;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("userContacts")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "contacttypeid")
    @JsonIgnoreProperties("usercontacts")
    private UserContactType usercontacttype;



//    @OneToMany(mappedBy = "userContact", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("userContact")
//    private List<SavedContacts> savedcontacts = new ArrayList<>();


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

    public UserContact(@Email String useremail, String userphone, String useraddress, String usercity, String userState, String userzip, UserContactType usercontacttype)
    {
        this.useremail = useremail;
        this.userphone = userphone;
        this.useraddress = useraddress;
        this.usercity = usercity;
        this.userState = userState;
        this.userzip = userzip;
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