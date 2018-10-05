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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author super
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Hobby.findall", query = "SELECT new entity.HobbyDTO(h) FROM Hobby h"),
    @NamedQuery(name = "Hobby.findbyname", query = "SELECT new entity.HobbyDTO(h) FROM Hobby h where h.name = :name")
})
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    //@ElementCollection()
    private List<Person> people;

    public Hobby()
    {
    }

    public Hobby(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    
    public Integer getId() {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public List<Person> getPeople()
    {
        return people;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setPeople(Person person)
    {
        people.add(person);
        person.getHobbies().add(this);
    }
    
}
