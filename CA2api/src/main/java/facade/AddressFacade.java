package facade;

import entity.AddressDTO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;

public class AddressFacade 
{
    private EntityManagerFactory emf;

    public AddressFacade(EntityManagerFactory emf)
    {
        this.emf = Persistence.createEntityManagerFactory("pu");
    }
    
    private EntityManager getEm()
    {
        return emf.createEntityManager();
    }
    
    public AddressDTO getAddressByZip(int zipCode)
    {
        EntityManager em = getEm();
        try {
            return em.createNamedQuery("Address.findbyzip", AddressDTO.class)
                    .setParameter("zipCode", zipCode)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
