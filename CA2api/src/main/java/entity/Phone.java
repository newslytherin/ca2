package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author super
 */
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
