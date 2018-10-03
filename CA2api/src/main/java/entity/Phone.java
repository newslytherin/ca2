package entity;

import java.io.Serializable;
import javax.persistence.Entity;
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
    @ManyToOne
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
    
    
    

}
