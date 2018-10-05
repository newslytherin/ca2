/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Hobby;
import facade.HobbyFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
 * @author Jacob Borg
 */
@Path("hobby")
public class HobbyResource
{

    @Context
    private UriInfo context;
    private final Gson gson;
    private HobbyFacade hf;

    /**
     * Creates a new instance of HobbyResource
     */
    public HobbyResource()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
        hf = new HobbyFacade();
    }

    /**
     * Retrieves representation of an instance of rest.HobbyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHobby()
    {
        return Response.ok(gson.toJson(hf.getAllHobby())).build();
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHobbyByName(@PathParam("name") String name) {
        return Response.ok(gson.toJson(hf.getHobbyByName(name))).build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putHobby(String json) {
        Hobby h = gson.fromJson(json, Hobby.class);
        hf.addHobby(h);
        return Response.ok(json).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postHobby(String json) {
        Hobby h = gson.fromJson(json, Hobby.class);
        hf.addHobby(h);
        return Response.ok(json).build();
    }
    
    @POST
    @Path("hobbyid/{hobbyid}/personid/{personid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHobby(@PathParam("hobbyid") int hobbyid, @PathParam("personid") int personid){
        
        hf.addHobbyToPerson(personid, hobbyid);
        return Response.ok().build();
        
    }
}
