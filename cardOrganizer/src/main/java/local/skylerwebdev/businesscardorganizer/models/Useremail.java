//package local.skylerwebdev.businesscardorganizer.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@IdClass(Useremail.class)
//@Table(name = "useremails")
//public class Useremail extends Auditable implements Serializable
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long useremailid;
//
//    @Column(nullable = false)
//    @Email
//    private String useremail;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "userid",
//                nullable = false)
//    @JsonIgnoreProperties("useremails")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "emailtypeid")
//    @JsonIgnoreProperties("useremails")
//    private EmailContactType emailcontacttype;
//
//    public Useremail()
//    {
//    }
//
//    public Useremail(User user, String useremail, EmailContactType emailcontacttype)
//    {
//        this.useremail = useremail;
//        this.user = user;
//        this.emailcontacttype = emailcontacttype;
//    }
//
//    public long getUseremailid()
//    {
//        return useremailid;
//    }
//
//    public void setUseremailid(long useremailid)
//    {
//        this.useremailid = useremailid;
//    }
//
//    public String getUseremail()
//    {
//        return useremail;
//    }
//
//    public void setUseremail(String useremail)
//    {
//        this.useremail = useremail;
//    }
//
//    public User getUser()
//    {
//        return user;
//    }
//
//    public void setUser(User user)
//    {
//        this.user = user;
//    }
//
//    public EmailContactType getEmailcontacttype()
//    {
//        return emailcontacttype;
//    }
//
//    public void setEmailcontacttype(EmailContactType emailcontacttype)
//    {
//        this.emailcontacttype = emailcontacttype;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Useremail useremail1 = (Useremail) o;
//        return useremailid == useremail1.useremailid &&
//                Objects.equals(useremail, useremail1.useremail) &&
//                Objects.equals(user, useremail1.user) &&
//                Objects.equals(emailcontacttype, useremail1.emailcontacttype);
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(useremailid, useremail, user, emailcontacttype);
//    }
//
//    @Override
//    public String toString()
//    {
//        return "Useremail{" +
//                "useremailid=" + useremailid +
//                ", useremail='" + useremail + '\'' +
//                ", user=" + user +
//                ", emailcontacttype=" + emailcontacttype +
//                '}';
//    }
//}
