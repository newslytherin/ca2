/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import entity.AddressDTO;
import entity.CityInfo;
import entity.CityInfoDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Stephan
 */
public class CityInfoFacade {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public CityInfoFacade() {
        emf = Persistence.createEntityManagerFactory("pu");
    }
    
    public static void addEntityManageractory(EntityManagerFactory ef) {
        emf = ef;
    }
    
    public static CityInfoDTO getCityInfoDTOByZip(int zipCode) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createNamedQuery("CityInfo.findbyzipcode", CityInfoDTO.class)
                    .setParameter("zipCode", zipCode)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public static List<CityInfoDTO> getAllCityDTO() {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createNamedQuery("CityInfo.findall", CityInfoDTO.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public void addAddressToCityInfo(int addressid, int cityid){
        
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
           
            Address a = em.find(Address.class, addressid);
            CityInfo city = em.find(CityInfo.class, cityid);

            city.addAddress(a);

            em.getTransaction().commit();
           
        } finally {
            em.close();
        }
        
    }

}
