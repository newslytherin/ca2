/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.CompanyDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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

    public List<CompanyDTO> getCompanyDTOWithFilters(Map<String, String> parameters)
    {

        EntityManager em = emf.createEntityManager();
        String query = "SELECT new enitity.CompanyDTO(c) FROM InfoEntity c WHERE TYPE(c) <> Company";

        //build query string
        if (parameters.containsKey("empmin"))
        {
            query += " AND numEmployees <= :empmin";
        }
        if (parameters.containsKey("empmax"))
        {
            query += " AND numEmployees >= :empmax";
        }
        if (parameters.containsKey("valuemin"))
        {
            query += " AND marketValue <= :valuemin";
        }
        if (parameters.containsKey("valuemax"))
        {
            query += " AND marketValue >= :valuemax";
        }
        if (parameters.containsKey("street"))
        {
            query += " AND (SELECT a.street FROM Address a) = :street";
        }
        if (parameters.containsKey("zipCode"))
        {
            query += " AND (SELECT a.cityInfo.zipCode FROM Address a) = :zipCode";
        }

        System.out.println(query);

        try
        {

            TypedQuery tq = em.createNamedQuery(query, CompanyDTO.class);

            if (parameters.containsKey("empmin"))
            {
                tq.setParameter("empmin", parameters.get("empmin"));
            }
            if (parameters.containsKey("empmax"))
            {
                tq.setParameter("empmax", parameters.get("empmax"));
            }
            if (parameters.containsKey("valuemin"))
            {
                tq.setParameter("valuemin", parameters.get("valuemin"));
            }
            if (parameters.containsKey("valuemax"))
            {
                tq.setParameter("valuemax", parameters.get("valuemax"));
            }
            if (parameters.containsKey("street"))
            {
                tq.setParameter("street", parameters.get("street"));
            }
            if (parameters.containsKey("zipCode"))
            {
                tq.setParameter("zipCode", parameters.get("zipCode"));
            }

            System.out.println(tq);

            //return tq.getResultList();
            return null;

        } finally
        {
            em.close();
        }
    }

    public static void main(String[] args)
    {
        Map map = new HashMap();

        map.put("empmin", "5");
        map.put("empmax", "20");
        map.put("valuemin", "5");
        map.put("valuemax", "20");
        map.put("zipCode", "2680");
        map.put("street", "minvej");

        CompanyFacade cf = new CompanyFacade();
        List<CompanyDTO> list = cf.getCompanyDTOWithFilters(map);
        System.out.println(list);

    }

    public CompanyDTO getCompanyDTOById(int id)
    {

        EntityManager em = emf.createEntityManager();
        String query = "SELECT new CompanyDTO(c) FROM infoEntity c WHERE c.id = :id";

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
        String query = "SELECT new CompanyDTO(c) FROM infoEntity c WHERE c.cvr = :cvr";

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
        String query = "SELECT new CompanyDTO(c) FROM infoEntity c WHERE c.name = :name";

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

        String query = "SELECT new CompanyDTO(c) FROM infoEntity c WHERE (SELECT p.number FROM c.phones p) = :phone";

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
