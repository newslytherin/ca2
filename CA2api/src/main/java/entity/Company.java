package entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("C")
public class Company extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String cvr;
    private int numEmployees;
    private double marketValue;

    public Company()
    {
    }

    public Company(String name, String description, String cvr, int numEmployees, double marketValue)
    {
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.numEmployees = numEmployees;
        this.marketValue = marketValue;
    }
    
    
    @Override
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }

    public String getCvr()
    {
        return cvr;
    }

    public int getNumEmployees()
    {
        return numEmployees;
    }

    public double getMarketValue()
    {
        return marketValue;
    }
    

}
