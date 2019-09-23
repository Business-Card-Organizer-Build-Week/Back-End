package local.skylerwebdev.businesscardorganizer.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "savedcontacts")
@IdClass(SavedContacts.class)
public class SavedContacts extends Auditable implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long savedcontactid;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("savedContacts")
    private User user;

    private int contactid;

    public SavedContacts()
    {
    }

    public SavedContacts(User user, int contactid)
    {
        this.user = user;
        this.contactid = contactid;
    }


    public long getSavedcontactid()
    {
        return savedcontactid;
    }

    public void setSavedcontactid(long savedcontactid)
    {
        this.savedcontactid = savedcontactid;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public int getContactid()
    {
        return contactid;
    }

    public void setContactid(int contactid)
    {
        this.contactid = contactid;
    }

    @Override
    public String toString()
    {
        return "SavedContacts{" +
                "savedcontactid=" + savedcontactid +
                ", user=" + user +
                ", contactid=" + contactid +
                '}';
    }
}