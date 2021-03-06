/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Address;
import facade.AddressFacade;
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
@Path("address")
public class AddressResource
{

    @Context
    private UriInfo context;
    private final Gson gson;
    AddressFacade af;

    /**
     * Creates a new instance of AddressResource
     */
    public AddressResource()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
        af = new AddressFacade();
    }

    /**
     * Retrieves representation of an instance of rest.AddressResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddress()
    {
        return Response.ok(gson.toJson(af.getAllAddressDTO())).build();
    }

    @GET
    @Path("zip/{zip}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressByZip(@PathParam("zip") int zip)
    {
        return Response.ok(gson.toJson(af.getAddressByZip(zip))).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putAddress(String json)
    {
        Address a = gson.fromJson(json, Address.class);
        af.addAddress(a);
        return Response.ok(json).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postAddress(String json)
    {
        Address a = gson.fromJson(json, Address.class);
        af.addAddress(a);
        return Response.ok(json).build();
    }
}
