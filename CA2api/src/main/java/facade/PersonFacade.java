package facade;

import entity.Hobby;
import entity.Person;
import entity.PersonDTO;
import exception.InvalidDataException;
import exception.PersonNotFoundException;
import java.util.ArrayList;
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

    public List<PersonDTO> getPersonDTOWithFilters(Map<String, String> parameters) throws InvalidDataException {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT new entity.PersonDTO(p) FROM Person p";
        //build query string
        if (!parameters.isEmpty()) {
            jpql += " JOIN Address a WHERE TYPE(p) = Person";
            if (parameters.containsKey("street")) {
                jpql += " AND a.street = :street AND p MEMBER OF a.infoEntities";
            }
            if (parameters.containsKey("zipCode")) {
                jpql += " AND a.cityInfo.zipCode = :zipCode AND p MEMBER OF a.infoEntities";
            }
        }
        try {
            TypedQuery<PersonDTO> query = em.createQuery(jpql, PersonDTO.class);
            if (parameters.containsKey("street")) {
                query = query.setParameter("street", parameters.get("street"));
            }
            if (parameters.containsKey("zipCode")) {
                query = query.setParameter("zipCode", Integer.parseInt(parameters.get("zipCode")));
            }
            return query.getResultList();
        } catch (Exception ex) {
            throw new InvalidDataException("Inserted data is not valid");
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOById(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT new entity.PersonDTO(p) FROM Person p WHERE p.id = :id", PersonDTO.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new PersonNotFoundException("Could not find person with id: " + id);
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOByPhone(String phone) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT new entity.PersonDTO(p) FROM Person p JOIN p.phones phone WHERE phone.number = :phone";
        try {
            return em.createQuery(jpql, PersonDTO.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
        } catch (Exception e) {
            throw new PersonNotFoundException("Could not find person with phone number: " + phone);
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonDTOByEmail(String email) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT new entity.PersonDTO(p) FROM Person p WHERE p.email = :email", PersonDTO.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            throw new PersonNotFoundException("Could not find person with email: " + email);
        } finally {
            em.close();
        }
    }

    public List<PersonDTO> getPersonDTOByHobby(String hobby) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Hobby h = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :hobby", Hobby.class)
                    .setParameter("hobby", hobby)
                    .getSingleResult();
            System.out.println("Person size: " + h.getPeople().size());
            List<PersonDTO> people = new ArrayList<>();
            for (Person p : h.getPeople()) {
                people.add(new PersonDTO(p));
            }
            System.out.println("PersonDTO size: " + people.size());
            return people;
        } catch (Exception e) {
            throw new PersonNotFoundException("Could not find any persons with hobby: " + hobby);
        } finally {
            em.close();
        }
    }

    public PersonDTO addPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        PersonDTO pdto = new PersonDTO(p);
        return pdto;
    }

    public PersonDTO updatePerson(Person p) {
        EntityManager em = emf.createEntityManager();
        Person tmp = null;
        try {
            em.getTransaction().begin();
            tmp = em.find(Person.class, p.getId()).updateValues(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(tmp);
    }

    public Person deletePerson(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, person.getId());
            if (p != null) {
                for (Hobby h : p.getHobbies()) {
                    h.getPeople().remove(p);
                }
                p.getAddress().getInfoEntities().remove(p);
                em.remove(p);
            }
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
}
