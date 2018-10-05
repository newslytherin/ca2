package entity;

import exception.CompanyNotFoundException;
import facade.CompanyFacade;
import facade.PersonFacade;
import facade.PhoneFacade;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author super
 */
@NamedQueries({
    @NamedQuery(name = "Phone.findall", query = "SELECT new entity.PhoneDTO(p) FROM Phone p"),
    @NamedQuery(name = "Phone.findbynumber", query = "SELECT new entity.PhoneDTO(p) FROM Phone p WHERE p.number = :number")
})
@Entity
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private InfoEntity infoEntity;

    public Integer getId() {
        return id;
    }

    public String getNumber()
    {
        return number;
    }

    public String getDescription()
    {
        return description;
    }

    public InfoEntity getInfoEntity()
    {
        return infoEntity;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Phone setInfoEntity(InfoEntity infoEntity) {
        
        this.infoEntity = infoEntity;
        this.infoEntity.getPhones().add(this);
        
        return this;
    }

    @Override
    public String toString()
    {
        return "Phone{" + "id=" + id + ", number=" + number + ", description=" + description + '}';
    }
    
    
    
    
    
    
//    public static void main(String[] args)
//    {
//        
//        PersonFacade personfacade = new PersonFacade();
//        PhoneFacade phonefacade = new PhoneFacade();
//        CompanyFacade companyfacade = new CompanyFacade();
//        
//        Person p = new Person();
//        p.setFirstName("daniel");
//        p.setLastName("lindholm");
//        
//        Company c = new Company("Daniels Company", null, null, 0, 0d);
//        
//        companyfacade.addCompany(c);
//        personfacade.addPerson(p);
//        
//        
//        Phone phone = new Phone();
//        phone.setNumber("555345345");
//        phone.setDescription("Min iPhone");
//        
//     
//        phonefacade.addPhone(phone);
//        
//        
//        System.out.println("-----");
//        System.out.println(c);
//        //System.out.println(p);
//        System.out.println(phone);
//        System.out.println("-----");
//        
//        
//        System.out.println(phonefacade.addPhoneToInfoEntity(c, phone));
//          
//        
//    }
    
}
