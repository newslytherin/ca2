/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.CompanyFacade;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
            @QueryParam("zipCode") String zipCode
    )
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
        if (empmin != null)
        {
            map.put("street", street);
        }
        if (empmin != null)
        {
            map.put("zipCode", zipCode);
        }

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOWithFilters(map)))
                .build();

    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name)
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOByName(name)))
                .build();

    }

    @GET
    @Path("cvr/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCvr(@PathParam("cvr") String cvr)
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOByCvr(cvr)))
                .build();

    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id)
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOById(id)))
                .build();

    }

    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPhone(@PathParam("phone") String phone)
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOByPhone(phone)))
                .build();

    }

    @GET
    @Path("email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEmail(@PathParam("email") String email)
    {

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(f.getCompanyDTOByEmail(email)))
                .build();

    }
    
}


