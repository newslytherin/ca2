package entity;

import static java.util.Arrays.stream;
import java.util.List;
import java.util.stream.Collectors;

public class HobbyDTO 
{
    private int id;
    private String name;
    private String description;
    private List<String> people;

    public HobbyDTO(Hobby h)
    {
        this.id = h.getId();
        this.name = h.getName();
        this.description = h.getDescription();
        this.people = h.getPeople().stream().map(Person::getName).collect(Collectors.toList());
    }

    public int getId()
    {
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

    public List<String> getPeople()
    {
        return people;
    }
}
