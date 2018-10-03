package entity;

import java.util.List;
import java.util.stream.Collectors;

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
        phones = p.getPhones().stream().map(Phone::getNumber).collect(Collectors.toList());
        hobbies = p.getHobbies().stream().map(Hobby::getName).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public List<String> getPhones() {
        return phones;
    }

    public List<String> getHobbies() {
        return hobbies;
    }
}
