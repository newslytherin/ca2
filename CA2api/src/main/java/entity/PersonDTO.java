package entity;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDTO {

    private int id;
    private String email;
    private String name;
    private String address;
    private int zipCode;
    private String city;
    private List<String> phones;
    private List<String> hobbies;

    public PersonDTO(Person p) {
        this.id = p.getId();
        this.email = p.getEmail();
        this.name = p.getFirstName() + " " + p.getLastName();
        this.address = p.getAddress().getStreet() + " " + p.getAddress().getAddressInfo();
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

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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
