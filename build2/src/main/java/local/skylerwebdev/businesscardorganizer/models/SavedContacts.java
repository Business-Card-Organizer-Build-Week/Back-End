package local.skylerwebdev.businesscardorganizer.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "savedcontacts")
@IdClass(SavedContacts.class)
public class SavedContacts extends Auditable implements Serializable
{

    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("savedContacts")
    private User user;
    @Id
    private int contactid;

    public SavedContacts()
    {
    }

    public SavedContacts(User user, int contactid)
    {
        this.user = user;
        this.contactid = contactid;
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

                ", user=" + user +
                ", contactid=" + contactid +
                '}';
    }
}