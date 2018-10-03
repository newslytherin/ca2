package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name = "type")
@Table(name = "INFO")
public class InfoEntity implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @OneToMany(mappedBy = "infoEntity", fetch = FetchType.EAGER)
    //@ElementCollection()
    private List<Phone> phones;
    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

    public InfoEntity()
    {
        phones = new ArrayList();
    }

    
    
    public Integer getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public List<Phone> getPhones()
    {
        return phones;
    }

    public Address getAddress()
    {
        return address;
    }

    public String getName()
    {
        return "";
    }

    void addPhone(Phone phone) {
        phones.add(phone);
    }

}
