package local.skylerwebdev.businesscardorganizer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacttypes")
public class ContactType
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long typeid;

    @Column(nullable = false,
            unique = true)
    private String contacttype;

    @OneToMany(mappedBy = "contacttype", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("contacttype")
    private List<UserContact> userContacts = new ArrayList<>();

    public ContactType()
    {
    }


    public ContactType(String contacttype)
    {
        this.contacttype = contacttype;

    }

    public long getTypeid()
    {
        return typeid;
    }

    public void setTypeid(long typeid)
    {
        this.typeid = typeid;
    }

    public String getContacttype()
    {
        return contacttype;
    }

    public void setContacttype(String contacttype)
    {
        this.contacttype = contacttype;
    }

    public List<UserContact> getUserContacts()
    {
        return userContacts;
    }

    public void setUserContacts(List<UserContact> userContacts)
    {
        this.userContacts = userContacts;
    }
}