package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "CityInfo.findall", query = "SELECT new entity.CityInfoDTO(c) FROM City c"),
    @NamedQuery(name = "CityInfo.findbyzipcode", query = "SELECT new entity.CityInfoDTO(c) FROM City c WHERE c.zipCode = :zipCode")
})
@Entity
@Table(name="CITY")
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int zipCode;
    private String city;
    @ElementCollection()
    private List<Address> addresses;

    public Integer getId() {
        return id;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public List<Address> getAddresses()
    {
        return addresses;
    }
    
    

}
