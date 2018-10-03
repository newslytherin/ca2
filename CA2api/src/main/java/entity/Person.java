package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("P")
@NamedQueries({
    @NamedQuery(name = "Person.findbyid", query = "SELECT new PersonDTO(p) FROM InfoEntity p where p.id = :id"),
    @NamedQuery(name = "Person.findbyemail", query = "SELECT new PersonDTO(p) FROM InfoEntity p where p.email = :email")
})
public class Person extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "people")
    @ElementCollection()
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
    public String toString()
    {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + hobbies + '}';
    }

}
