/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Phone;
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

/**
 * REST Web Service
 *
 * @author Stephan
 */
@Path("phone")
public class PhoneResource {

    @Context
    private UriInfo context;
    
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
    public String getJson() {
        return gson.toJson(PhoneFacade.getAllPhonesDTO());
    }
    
    /**
     * Retrieves representation of an instance of rest.PhoneResource
     * @param number
     * @return an instance of java.lang.String
     */
    @Path("number/{number}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("number") int number) {
        return gson.toJson(PhoneFacade.getPhoneDTOByNumber(number));
    }

    /**
     * PUT method for updating or creating an instance of PhoneResource
     * @param content representation for the resource
     * @param infoEntityid
     */
    @Path("infoentityid/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content, @PathParam("id") int infoEntityid) {
        PhoneFacade.addPhone(gson.fromJson(content, Phone.class), infoEntityid);
    }
    
    @Path("number/{number}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void postJson(String content) {
        PhoneFacade.editPhone(gson.fromJson(content, Phone.class));
    }
    
    @Path("number/{number}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteJson(String content) {
        PhoneFacade.deletePhone(gson.fromJson(content, Phone.class));
    }
}
