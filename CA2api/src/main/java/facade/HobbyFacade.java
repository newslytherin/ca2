package facade;

import entity.Hobby;
import entity.HobbyDTO;
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
    
    public EntityManager getEm()
    {
        return emf.createEntityManager();
    }
    
    public HobbyDTO getHobby(String name)
    {
        EntityManager em = getEm();
        try {
            return em.createNamedQuery("Hobby.findbyname", HobbyDTO.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
