/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Phone;
import entity.PhoneDTO;
import facade.PhoneFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Stephan
 */
@Path("phone")
public class PhoneResource {

    @Context
    private UriInfo context;
    
    PhoneFacade pf = new PhoneFacade();
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of PhoneResource
     */
    public PhoneResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PhoneResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        
        String json = gson.toJson(pf.getAllPhonesDTO()); 
        if(json != null) {
            return Response.ok(json).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{}").build();
        }
    }
    
    /**
     * Retrieves representation of an instance of rest.PhoneResource
     * @param number
     * @return an instance of java.lang.String
     */
    @Path("number/{number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("number") String number) {
        
        String json = gson.toJson(pf.getPhoneDTOByNumber(number));  
        if(json != null) {   
            return Response.ok(json).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{}").build();
        }
    }

    /**
     * PUT method for updating or creating an instance of PhoneResource
     * @param content representation for the resource
     * @param infoEntityid
     */
    @Path("infoentityid/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String content, @PathParam("id") int infoEntityid) {
        
        Phone phone = gson.fromJson(content, Phone.class);
        
        PhoneDTO dto = pf.addPhoneToInfoEntity(phone, infoEntityid);
        
        if(dto != null) {
            return Response.ok(gson.toJson(dto)).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{}").build();
        }
    }
    
    @Path("number/{number}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        
        Phone phone = gson.fromJson(content, Phone.class);     
        if(pf.editPhone(phone) != null) {
            return Response.ok(content).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{}").build();
        } 
    }
    
    @Path("number/{number}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteJson(String content) {
        
        Phone phone = gson.fromJson(content, Phone.class);     
        if(pf.deletePhone(phone) != null) {
            return Response.ok(content).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{}").build();
        }     
    }
}
