/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.CityInfoFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Stephan
 */
@Path("city")
public class CityResource {

    @Context
    private UriInfo context;
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of CityResource
     */
    public CityResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CityResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        String json = gson.toJson(CityInfoFacade.getAllCityDTO());
        return Response.ok(json).build();
    }
    
    /**
     * Retrieves representation of an instance of rest.CityResource
     * @param zip
     * @return an instance of java.lang.String
     */
    @Path("zip/{zip}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("zip") int zip) {
        String json = gson.toJson(CityInfoFacade.getCityInfoDTOByZip(zip));
        return Response.ok(json).build();
    }

}
