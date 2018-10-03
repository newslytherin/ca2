package entity;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;

public class AddressDTO 
{
    private Integer id;
    private String street;
    private String addressInfo;
    private List<String> infoEntities;
    private String cityInfo;

    public AddressDTO(Address a)
    {
        this.id = a.getId();
        this.street = a.getStreet();
        this.addressInfo = a.getAddressInfo();
        this.infoEntities = a.getInfoEntities().stream().map(e->e.g).collect(Collectors.toList());
        this.cityInfo = a.getCityInfo().getCity();
    }
    
    
}
