/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.CityInfoDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Stephan
 */
public class CityInfoFacada {
    private EntityManagerFactory emf;

    public CityInfoFacada() {
        emf = Persistence.createEntityManagerFactory("pu");
    }
    
    public void addEntityManageractory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public CityInfoDTO getCityInfoDTOByZip(int zipCode) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createNamedQuery("CityInfo.findbyzipcode", CityInfoDTO.class)
                    .setParameter("zipCode", zipCode)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<CityInfoDTO> getAllCityDTO() {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createNamedQuery("CityInfo.findall", CityInfoDTO.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
