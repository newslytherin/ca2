package entity;

import java.util.List;

public class PersonDTO {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String addressInfo;
    private int zipCode;
    private String city;
    private List<String> phones;
    private List<String> hobbies;
    public PersonDTO(Person p) {
        this.id = p.getId();
        this.email = p.getEmail();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.street = p.getAddress().getStreet();
        this.addressInfo = p.getAddress().getAddressInfo();
        this.zipCode = p.getAddress().getCityInfo().getZipCode();
        this.city = p.getAddress().getCityInfo().getCity();
        p.getPhones().forEach(phone -> phones.add(phone.toString()));
        
    }
}
