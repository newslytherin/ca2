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
public class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String email;
    @OneToMany(mappedBy = "infoEntity", fetch = FetchType.EAGER)
    //@ElementCollection()
    protected List<Phone> phones;
    @ManyToOne(fetch = FetchType.EAGER)
    protected Address address;

    public InfoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return "";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public InfoEntity addPhone(Phone phone){
        
        phone.setInfoEntity(this);
        return this;
    }

    @Override
    public String toString() {
        return "InfoEntity{" + "id=" + id + ", email=" + email + ", phones=" + phones + ", address=" + address + '}';
    }

}
