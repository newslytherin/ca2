package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author super
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Address.findall", query = "SELECT new entity.AddressDTO(a) FROM Address a"),
    @NamedQuery(name = "Address.findbyzip", query = "SELECT new entity.AddressDTO(a) FROM Address a where a.cityInfo.zipCode = :zipCode")
})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String addressInfo;
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    //@ElementCollection()
    private List<InfoEntity> infoEntities;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private CityInfo cityInfo;

    public Address()
    {
    }

    public Address(String street, String addressInfo)
    {
        this.street = street;
        this.addressInfo = addressInfo;
    }

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

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setInfoEntities(List<InfoEntity> infoEntities)
    {
        this.infoEntities = infoEntities;
    }

    public void setCityInfo(CityInfo cityInfo)
    {
        this.cityInfo = cityInfo;
        this.cityInfo.addAddress(this);
    }
    
    public void addInfoEntity(InfoEntity infoEntity){
        
        infoEntity.setAddress(this);
    }
    
    @Override
    public String toString() {
        return street + " " + addressInfo;
    }
}
