/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.PhoneDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Stephan
 */
public class PhoneFacade {
    private EntityManagerFactory emf;

    public PhoneFacade() {
        emf = Persistence.createEntityManagerFactory("pu");
    }
    
    public void addEntityManageractory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
      public PhoneDTO getPhoneDTOByNumber(int number) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createNamedQuery("Phone.findbynumber", PhoneDTO.class)
                    .setParameter("number", number)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<PhoneDTO> getAllCityDTO() {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createNamedQuery("Phone.findall", PhoneDTO.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
