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
//        this.email = p.getEmail();
//        this.name = p.getName();
//        this.address = p.getAddress().toString();
//        this.zipCode = p.getAddress().getCityInfo().getZipCode();
//        this.city = p.getAddress().getCityInfo().getCity();
//        phones = p.getPhones().stream().map(Phone::getNumber).collect(Collectors.toList());
//        hobbies = p.getHobbies().stream().map(Hobby::getName).collect(Collectors.toList());

        this.email = p.getEmail();
        //person
        this.name = p.getName();

        //phones -> maybe es works?
//        this.phones = new ArrayList<>();
        this.phones = p.getPhones().stream().map(Phone::getNumber).collect(Collectors.toList());
//        this.hobbies = new ArrayList<>();
        this.hobbies = p.getHobbies().stream().map(Hobby::getName).collect(Collectors.toList());
        p.getHobbies().forEach(System.out::println);
//        hobbies.forEach(System.out::println);

        if (p.getAddress() != null) {
            this.address = p.getAddress().getStreet() + " " + p.getAddress().getAddressInfo();
            if (p.getAddress().getCityInfo() != null) {
                this.zipCode = p.getAddress().getCityInfo().getZipCode();
                this.city = p.getAddress().getCityInfo().getCity();
            }
        }
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

    @Override
    public String toString() {
        return "id: " + id + ", " + name + ", " + email + ", " + address + ", " + zipCode + " " + city;
    }
}
