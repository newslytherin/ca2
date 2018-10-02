/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.CompanyDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CompanyFacade
{

    private EntityManagerFactory emf;

    public CompanyFacade()
    {
        this.emf = Persistence.createEntityManagerFactory("pu", null);
    }

    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public CompanyDTO getCompanyDTOById(int id)
    {

        EntityManager em = emf.createEntityManager();
        String query = "SELECT new CompanyDTO(c) FROM Company c WHERE c.id = :id";

        try
        {
            return em
                    .createNamedQuery(query, CompanyDTO.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally
        {
            em.close();
        }
    }

    public CompanyDTO getCompanyDTOByCvr(String cvr)
    {

        EntityManager em = emf.createEntityManager();
        String query = "SELECT new CompanyDTO(c) FROM Company c WHERE c.cvr = :cvr";

        try
        {
            return em
                    .createNamedQuery(query, CompanyDTO.class)
                    .setParameter("cvr", cvr)
                    .getSingleResult();
        } finally
        {
            em.close();
        }
    }
    
    public CompanyDTO getCompanyDTOByName(String name)
    {

        EntityManager em = emf.createEntityManager();
        String query = "SELECT new CompanyDTO(c) FROM Company c WHERE c.name = :name";

        try
        {
            return em
                    .createNamedQuery(query, CompanyDTO.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally
        {
            em.close();
        }
    }
    
    public CompanyDTO getCompanyDTOByPhone(String phone)
    {

        EntityManager em = emf.createEntityManager();
        //String query = "SELECT new CompanyDTO(c) FROM Company c WHERE (SELECT p.number FROM c.phones p = :phone)";
        //String query = "SELECT new CompanyDTO(c) FROM Company c WHERE (c.phones p WHERE p.number = :phone)";
        
        
        String query = "SELECT new CompanyDTO(c) FROM Company c WHERE (SELECT p.number FROM c.phones p) = :phone";
        
        try
        {
            return em
                    .createNamedQuery(query, CompanyDTO.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
        } finally
        {
            em.close();
        }
    }

}
