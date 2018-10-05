package dto;

import entity.Address;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;

public class AddressDTO 
{
    private Integer id;
    private String street;
    private String address;
    private List<String> persons;
    private String city;
    private int zipCode;

    public AddressDTO(Address a)
    {
        this.id = a.getId();
        this.address = a.getStreet() + " " + a.getAddressInfo();
        this.persons = a.getInfoEntities().stream().map(InfoEntity->InfoEntity.getName()).collect(Collectors.toList());
        this.city = a.getCityInfo().getCity();
        this.zipCode = a.getCityInfo().getZipCode();
    }
        
}
