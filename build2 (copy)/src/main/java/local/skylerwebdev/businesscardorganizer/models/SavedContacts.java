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


    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("savedContacts")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "contactid")
    @JsonIgnoreProperties("savedContacts")
    private UserContact userContact;


    public SavedContacts()
    {
    }



    public SavedContacts(User user, UserContact userContact)
    {
        this.user = user;
        this.userContact = userContact;

    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }




    public UserContact getUserContact()
    {
        return userContact;
    }

    public void setUserContact(UserContact userContact)
    {
        this.userContact = userContact;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedContacts that = (SavedContacts) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(userContact, that.userContact);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(user, userContact);
    }
}