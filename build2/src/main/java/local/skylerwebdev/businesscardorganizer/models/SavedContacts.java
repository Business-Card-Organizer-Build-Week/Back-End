package local.skylerwebdev.businesscardorganizer.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "savedcontacts")
@IdClass(SavedContacts.class)
public class SavedContacts implements Serializable
{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long savedcontactid;
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("savedContacts")
    private User user;

    @ApiModelProperty(name = "contactid", value = "Contact ID to Save", required = true, example = "1")
    @Id
    private long contactid;

    public SavedContacts()
    {
    }

    public SavedContacts(User user, long contactid)
    {
        this.user = user;
        this.contactid = contactid;
    }

//    public long getSavedcontactid()
//    {
//        return savedcontactid;
//    }
//
//    public void setSavedcontactid(long savedcontactid)
//    {
//        this.savedcontactid = savedcontactid;
//    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public long getContactid()
    {
        return contactid;
    }

    public void setContactid(int contactid)
    {
        this.contactid = contactid;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedContacts that = (SavedContacts) o;
        return contactid == that.contactid &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(user, contactid);
    }

    @Override
    public String toString()
    {
        return "SavedContacts{" +
                "user=" + user +
                ", contactid=" + contactid +
                '}';
    }
}