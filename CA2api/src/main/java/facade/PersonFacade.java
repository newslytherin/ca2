package facade;

import entity.PersonDTO;
import javax.persistence.EntityManager;
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
    public PersonDTO getPersonDTOByPhone(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Person.findbyid", PersonDTO.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOByPhone(String phone) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT new PersonDTO(e) FROM Person e WHERE (SELECT p.number FROM e.phones p) = :phone";
        try {
            return em.createQuery(jpql, PersonDTO.class).setParameter("number", phone).getSingleResult();
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Person.findbyemail", PersonDTO.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
