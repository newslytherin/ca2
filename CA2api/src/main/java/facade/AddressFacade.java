package facade;

import entity.Address;
import dto.AddressDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddressFacade
{

    private EntityManagerFactory emf;

    public AddressFacade()
    {
        this.emf = Persistence.createEntityManagerFactory("pu");
    }

    private EntityManager getEm()
    {
        return emf.createEntityManager();
    }

    public List<AddressDTO>  getAddressByZip(int zipCode)
    {
        EntityManager em = getEm();
        try
        {
            return em.createNamedQuery("Address.findbyzip", AddressDTO.class)
                    .setParameter("zipCode", zipCode)
                    .getResultList();
        } finally
        {
            em.close();
        }
    }

    public List<AddressDTO> getAllAddressDTO()
    {
        EntityManager em = getEm();
        try
        {
            return em.createNamedQuery("Address.findall", AddressDTO.class)
                    .getResultList();
        } finally
        {
            em.close();
        }
    }
    
    public void addAddress(Address a)
    {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public AddressDTO updateAddress(Address a)
    {
        EntityManager em = getEm();
        Address tmp = null;
        try {
            em.getTransaction().begin();
            tmp = em.find(Address.class, a.getId());
            tmp.setCityInfo(a.getCityInfo());
            tmp.setInfoEntities(a.getInfoEntities());
            tmp.setStreet(a.getStreet());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AddressDTO(tmp);
    }
}
