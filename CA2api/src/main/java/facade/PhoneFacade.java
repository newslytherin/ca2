/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.InfoEntity;
import entity.Phone;
import entity.PhoneDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Stephan
 */
public class PhoneFacade
{

    private EntityManagerFactory emf;

    public PhoneFacade()
    {
        emf = Persistence.createEntityManagerFactory("pu");
    }

    public void addEntityManageractory(EntityManagerFactory ef)
    {
        emf = ef;
    }

    public PhoneDTO getPhoneDTOByNumber(String number)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            return em.createNamedQuery("Phone.findbynumber", PhoneDTO.class)
                    .setParameter("number", number)
                    .getSingleResult();
        } finally
        {
            em.close();
        }
    }

    public List<PhoneDTO> getAllPhonesDTO()
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            return em.createNamedQuery("Phone.findall", PhoneDTO.class)
                    .getResultList();
        } finally
        {
            em.close();
        }
    }

    public PhoneDTO addPhone(Phone phone, int infoEntityid)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            phone.setInfoEntity(em.find(InfoEntity.class, infoEntityid));
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }

        return new PhoneDTO(phone);
    }

    public PhoneDTO deletePhone(Phone phone)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            em.remove(phone);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }

        return new PhoneDTO(phone);
    }

    public PhoneDTO editPhone(Phone phone)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            em.merge(phone);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }

        return new PhoneDTO(phone);
    }

}
