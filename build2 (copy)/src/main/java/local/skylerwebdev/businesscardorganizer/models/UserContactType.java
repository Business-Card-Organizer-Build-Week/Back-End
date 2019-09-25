package local.skylerwebdev.businesscardorganizer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacttypes")
public class UserContactType
{

    @ApiModelProperty(name = "contacttypeid", value = "Primary Key for Contact Type Auto Generated", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contacttypeid;

    @ApiModelProperty(name = "contacttype", value = "Contact Type", required = true, example = "Business")
    @Column(nullable = false, unique = true)
    private String contacttype;

    @OneToMany(mappedBy = "usercontacttype", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usercontacttype")
    private List<UserContact> usercontacts = new ArrayList<>();

    public UserContactType()
    {
    }

    public UserContactType(String contacttype)
    {
        this.contacttype = contacttype;
    }

    public long getContacttypeid()
    {
        return contacttypeid;
    }

    public void setContacttypeid(long contacttypeid)
    {
        this.contacttypeid = contacttypeid;
    }

    public String getContacttype()
    {
        return contacttype;
    }

    public void setContacttype(String contacttype)
    {
        this.contacttype = contacttype;
    }

    public List<UserContact> getUsercontacts()
    {
        return usercontacts;
    }

    public void setUsercontacts(List<UserContact> usercontacts)
    {
        this.usercontacts = usercontacts;
    }

    @Override
    public String toString()
    {
        return "UserContactType{" +
                "contacttypeid=" + contacttypeid +
                ", contacttype='" + contacttype + '\'' +
                ", usercontacts=" + usercontacts +
                '}';
    }



}
