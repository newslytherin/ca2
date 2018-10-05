package facade;

import entity.Hobby;
import entity.HobbyDTO;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HobbyFacade
{

    private EntityManagerFactory emf;

    public HobbyFacade()
    {
        this.emf = Persistence.createEntityManagerFactory("pu");
    }

    private EntityManager getEm()
    {
        return emf.createEntityManager();
    }

    public HobbyDTO getHobbyByName(String name)
    {
        EntityManager em = getEm();
        try
        {
            return em.createNamedQuery("Hobby.findbyname", HobbyDTO.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally
        {
            em.close();
        }
    }

    public List<HobbyDTO> getAllHobby()
    {
        EntityManager em = getEm();
        try
        {
            return em.createNamedQuery("Hobby.findall", HobbyDTO.class)
                    .getResultList();
        } finally
        {
            em.close();
        }
    }
    
    public void addHobby(Hobby h)
    {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.persist(h);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void addHobbyToPerson(int personID, int hobbyID){
        
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
           
            Hobby h = em.find(Hobby.class, hobbyID);
            Person p = em.find(Person.class, personID);

            p.addHobby(h);
            
            em.getTransaction().commit();
           
        } finally {
            em.close();
        }
        
    }
}
