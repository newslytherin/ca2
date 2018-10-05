package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("P")
public class Person extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "people", fetch = FetchType.EAGER)
    //@ElementCollection()
    private List<Hobby> hobbies;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + hobbies + '}';
    }

}
