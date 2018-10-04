package facade;

import entity.Person;
import entity.PersonDTO;
import entity.Phone;
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
        String jpql = "SELECT new entity.PersonDTO(p) FROM InfoEntity p WHERE TYPE(p) = Person";
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
                query = query.setParameter("zipCode", Integer.parseInt(parameters.get("zipCode")));
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
            return em.createQuery("SELECT new entity.PersonDTO(p) FROM InfoEntity p WHERE TYPE(p) = Person AND p.id = :id", PersonDTO.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOByPhone(String phone) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p FROM Phone p WHERE p.number = :phone";
        try {
            Phone p = em.createQuery(jpql, Phone.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
            return new PersonDTO((Person) p.getInfoEntity());
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT new entity.PersonDTO(p) FROM InfoEntity p WHERE TYPE(p) = Person AND p.email = :email", PersonDTO.class)
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

    public void updatePerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Person deletePerson(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, person.getId());
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
}
