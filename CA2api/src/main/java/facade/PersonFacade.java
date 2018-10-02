package facade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade() {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    public void addEntityManageractory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    //get all + filter
    //get by id
    //get by phone
    //get by email
//    public PersonDTO 
}
