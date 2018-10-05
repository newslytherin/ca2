/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Company;
import dto.CompanyDTO;
import entity.Person;
import exception.CompanyNotFoundException;
import exception.InvalidDataException;
import facade.CompanyFacade;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Hupra Laptop
 */
@Path("company")
public class CompanyResource
{

    @Context
    private UriInfo context;

    Gson gson;
    CompanyFacade f;

    public CompanyResource()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
        f = new CompanyFacade();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWithFilters(
            @QueryParam("empmin") String empmin,
            @QueryParam("empmax") String empmax,
            @QueryParam("valuemin") String valuemin,
            @QueryParam("valuemax") String valuemax,
            @QueryParam("street") String street,
            @QueryParam("zipcode") String zipCode
    ) throws InvalidDataException
    {
        
        Map<String, String> map = new HashMap();

        if (empmin != null)
        {
            map.put("empmin", empmin);
        }
        if (empmax != null)
        {
            map.put("empmax", empmax);
        }
        if (valuemin != null)
        {
            map.put("valuemin", valuemin);
        }
        if (valuemax != null)
        {
            map.put("valuemax", valuemax);
        }
        if (street != null)
        {
            map.put("street", street);
        }
        if (zipCode != null)
        {
            map.put("zipCode", zipCode);
        }
        
        System.out.println(map);

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOWithFilters(map)))
                .build();

    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name) throws CompanyNotFoundException
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOByName(name)))
                .build();

    }

    @GET
    @Path("cvr/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCvr(@PathParam("cvr") String cvr) throws CompanyNotFoundException
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOByCvr(cvr)))
                .build();

    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) throws CompanyNotFoundException
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOById(id)))
                .build();

    }

    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPhone(@PathParam("phone") String phone) throws CompanyNotFoundException
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOByPhone(phone)))
                .build();

    }

    @GET
    @Path("email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEmail(@PathParam("email") String email) throws CompanyNotFoundException
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOByEmail(email)))
                .build();

    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCompany(String json) {
        Company c = gson.fromJson(json, Company.class);
        CompanyDTO cDTO = f.addCompany(c);
        return Response.ok(gson.toJson(cDTO)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putCompany(String json) {
        Company c = gson.fromJson(json, Company.class);
        CompanyDTO cDTO = f.updateCompany(c);
        return Response.ok(gson.toJson(cDTO)).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCompany(String json) {
        Company c = gson.fromJson(json, Company.class);
        if (f.deleteCompany(c) != null) {
            return Response.ok(json).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{}").build();
        }
    }
    
}


