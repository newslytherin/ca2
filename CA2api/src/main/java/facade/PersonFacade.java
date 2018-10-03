package facade;

import entity.Person;
import entity.PersonDTO;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade() {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    public void addEntityManageractory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<PersonDTO> getPersonDTOWithFilters(Map<String, String> parameters) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT new entity.PersonDTO(p) FROM InfoEntity p WHERE TYPE(p) <> Person";
        //build query string
        if (parameters.containsKey("street")) {
            jpql += " AND (SELECT a.street FROM Address a) = :street";
        }
        if (parameters.containsKey("zipCode")) {
            jpql += " AND (SELECT a.cityInfo.zipCode FROM Address a) = :zipCode";
        }
        if (parameters.containsKey("hobby")) {
            jpql += " AND (SELECT h.name FROM Hobby h) = :hobby";
        }
        System.out.println(jpql);
        try {
            TypedQuery<PersonDTO> query = em.createQuery(jpql, PersonDTO.class);
            if (parameters.containsKey("street")) {
                query = query.setParameter("street", parameters.get("street"));
            }
            if (parameters.containsKey("zipCode")) {
                query = query.setParameter("zipCode", parameters.get("zipCode"));
            }
            if (parameters.containsKey("hobby")) {
                query = query.setParameter("hobby", parameters.get("hobby"));
            }
            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOById(int id) {
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
        String jpql = "SELECT new entity.PersonDTO(e) FROM InfoEntity e WHERE (SELECT p.number FROM e.phones p) = :phone";
        try {
            return em.createQuery(jpql, PersonDTO.class)
                    .setParameter("number", phone)
                    .getSingleResult();
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

    public void addPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
