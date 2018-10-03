package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author super
 */
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String addressInfo;
    @OneToMany(mappedBy = "address")
    @ElementCollection()
    private List<InfoEntity> infoEntities;
    private CityInfo cityInfo;

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public List<InfoEntity> getInfoEntities() {
        return infoEntities;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    @Override
    public String toString() {
        return street + " " + addressInfo;
    }
}
