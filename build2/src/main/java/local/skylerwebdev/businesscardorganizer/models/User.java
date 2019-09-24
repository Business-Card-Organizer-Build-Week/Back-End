package local.skylerwebdev.businesscardorganizer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// User is considered the parent entity

@Entity
@Table(name = "users")
public class User extends Auditable
{
    @ApiModelProperty(name = "userid", value = "Primary Key for Users Auto Generated", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @ApiModelProperty(name = "username", value = "Username for login", required = true, example = "username")
    @Column(nullable = false,
            unique = true)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String username;

    @ApiModelProperty(name = "fname", value = "User First Name", required = false, example = "John")
    @Column(nullable =true)
    private String fname;

    @ApiModelProperty(name = "lname", value = "User Last Name", required = false, example = "Smith")
    @Column(nullable =true)
    private String lname;

    @ApiModelProperty(name = "busname", value = "User Business Name", required = false, example = "Business Name")
    @Column(nullable =true)
    private String busname;

    @ApiModelProperty(name = "title", value = "User Title/Role", required = false, example = "Manager")
    @Column(nullable = true)
    private String title;

    @ApiModelProperty(name = "password", value = "User Password", required = true, example = "EntErYoUrPasswrdHere")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<>();

    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<UserContact> userContacts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<SavedContacts> savedContacts = new ArrayList<>();


    public User()
    {
    }

    public User(String username, String fname, String lname, String busname, String title, String password, List<UserRoles> userRoles)
    {
        setUsername(username);
        this.fname = fname;
        this.lname = lname;
        this.busname = busname;
        this.title = title;
        setPassword(password);
        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userroles = userRoles;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public String getBusname()
    {
        return busname;
    }

    public void setBusname(String busname)
    {
        this.busname = busname;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRoles> getUserroles()
    {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles)
    {
        this.userroles = userroles;
    }

    public List<UserContact> getUserContacts()
    {
        return userContacts;
    }

    public List<SavedContacts> getSavedContacts()
    {
        return savedContacts;
    }

    public void setSavedContacts(List<SavedContacts> savedContacts)
    {
        this.savedContacts = savedContacts;
    }

    public void setUserContacts(List<UserContact> userContacts)
    {
        this.userContacts = userContacts;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userroles)
        {
            String myRole = "ROLE_" + r.getRole()
                                       .getName()
                                       .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", busname='" + busname + '\'' +
                ", title='" + title + '\'' +
                ", password='" + password + '\'' +
                ", userroles=" + userroles +
                ", userContacts=" + userContacts +
                ", savedContacts=" + savedContacts +
                '}';
    }
}
