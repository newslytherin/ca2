/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.CompanyDTO;
import entity.InfoEntity;
import entity.Phone;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        String query = "SELECT new entity.CompanyDTO(c) FROM Company c";
        
        if(parameters.containsKey("street") || parameters.containsKey("zipCode")){
            query += " JOIN Address a";
        }
        
        query += " WHERE TYPE(c) = Company";
        
        
        List<CompanyDTO> list;

        //build query string
        if (parameters.containsKey("empmin"))
        {
            query += " AND c.numEmployees >= :empmin";
        }
        if (parameters.containsKey("empmax"))
        {
            query += " AND c.numEmployees <= :empmax";
        }
        if (parameters.containsKey("valuemin"))
        {
            query += " AND c.marketValue >= :valuemin";
        }
        if (parameters.containsKey("valuemax"))
        {
            query += " AND c.marketValue <= :valuemax";
        }
        if (parameters.containsKey("street"))
        {
            query += " AND a.street = :street AND c MEMBER OF a.infoEntities";
        }
        if (parameters.containsKey("zipCode"))
        {
            query += " AND a.cityInfo.zipCode = :zipCode AND c MEMBER OF a.infoEntities";
        }

        System.out.println(query);

        try
        {

            TypedQuery tq = em.createQuery(query, CompanyDTO.class);

            if (parameters.containsKey("empmin"))
            {
                tq = tq.setParameter("empmin", Integer.parseInt(parameters.get("empmin")));
            }
            if (parameters.containsKey("empmax"))
            {
                tq = tq.setParameter("empmax", Integer.parseInt(parameters.get("empmax")));
            }
            if (parameters.containsKey("valuemin"))
            {
                tq = tq.setParameter("valuemin", Double.parseDouble(parameters.get("valuemin")));
            }
            if (parameters.containsKey("valuemax"))
            {
                tq = tq.setParameter("valuemax", Double.parseDouble(parameters.get("valuemax")));
            }
            if (parameters.containsKey("street"))
            {
                tq = tq.setParameter("street", parameters.get("street"));
            }
            if (parameters.containsKey("zipCode"))
            {
                tq = tq.setParameter("zipCode", Integer.parseInt(parameters.get("zipCode")));
            }

            list = tq.getResultList();

        } finally
        {
            em.close();
        }
        return list;
    }

    public static void main(String[] args)
    {
        Map map = new HashMap();
//        map.put("cvr", "hejsa");
//        map.put("empmin", "400");
//        map.put("empmax", "600");
//        map.put("valuemin", "1500");
//        map.put("valuemax", "2500");
//        map.put("zipCode", "2680");
//        map.put("street", "pedersvej");

        CompanyFacade cf = new CompanyFacade();
        List<CompanyDTO> dto = cf.getCompanyDTOWithFilters(map);
        dto.forEach(System.out::println);

        //CompanyDTO dto = cf.getCompanyDTOByEmail("test@test.dk");
        //CompanyDTO dto = cf.getCompanyDTOByPhone("1234562278");
        //System.out.println(dto);
    }

    public CompanyDTO getCompanyDTOById(int id)
    {
        String query = "SELECT new entity.CompanyDTO(c) FROM Company c WHERE c.id = :id";
        return simpleFacadeBuilder(query, "id", id);
    }

    public CompanyDTO getCompanyDTOByCvr(String cvr)
    {
        String query = "SELECT new entity.CompanyDTO(c) FROM Company c WHERE c.cvr = :cvr";
        return simpleFacadeBuilder(query, "cvr", cvr);
    }

    public CompanyDTO getCompanyDTOByName(String name)
    {
        String query = "SELECT new entity.CompanyDTO(c) FROM Company c WHERE c.name = :name";
        return simpleFacadeBuilder(query, "name", name);
    }

    public CompanyDTO getCompanyDTOByPhone(String phone)
    {
        String query = "SELECT new entity.CompanyDTO(c) FROM Company c JOIN c.phones p WHERE p.number = :phone";
        return simpleFacadeBuilder(query, "phone", phone);
    }

    public CompanyDTO getCompanyDTOByEmail(String email)
    {
        String query = "SELECT new entity.CompanyDTO(c) FROM Company c WHERE c.email = :email";
        return simpleFacadeBuilder(query, "email", email);
    }

    private CompanyDTO simpleFacadeBuilder(String query, String setValue, String here)
    {
        EntityManager em = emf.createEntityManager();
        CompanyDTO dto = null;
        try
        {
            dto = em
                    .createQuery(query, CompanyDTO.class)
                    .setParameter(setValue, here)
                    .getSingleResult();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        } finally
        {
            em.close();
        }
        return dto;
    }

    private CompanyDTO simpleFacadeBuilder(String query, String setValue, int here)
    {
        EntityManager em = emf.createEntityManager();
        CompanyDTO dto = null;
        try
        {
            dto = em
                    .createQuery(query, CompanyDTO.class)
                    .setParameter(setValue, here)
                    .getSingleResult();
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        } finally
        {
            em.close();
        }
        return dto;
    }

}
