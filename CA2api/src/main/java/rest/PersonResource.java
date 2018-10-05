package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import exception.InvalidDataException;
import exception.PersonNotFoundException;
import facade.PersonFacade;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private PersonFacade pf = new PersonFacade();
    @Context
    private UriInfo context;

    public PersonResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@QueryParam("street") String street, @QueryParam("zipcode") String zipCode) throws InvalidDataException {
        Map<String, String> params = new HashMap<>();
        if (street != null) {
            params.put("street", street);
        }
        if (zipCode != null) {
            params.put("zipCode", zipCode);
        }
        return Response.ok(gson.toJson(pf.getPersonDTOWithFilters(params))).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("id") int id) throws PersonNotFoundException {
        return Response.ok(gson.toJson(pf.getPersonDTOById(id))).build();
    }

    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByPhone(@PathParam("phone") String phone) throws PersonNotFoundException {
        return Response.ok(gson.toJson(pf.getPersonDTOByPhone(phone))).build();
    }

    @GET
    @Path("email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByEmail(@PathParam("email") String email) throws PersonNotFoundException {
        return Response.ok(gson.toJson(pf.getPersonDTOByEmail(email))).build();
    }

    @GET
    @Path("hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByHobby(@PathParam("hobby") String hobby) throws PersonNotFoundException {
        return Response.ok(gson.toJson(pf.getPersonDTOByHobby(hobby))).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPerson(String json) throws InvalidDataException {
        Person p = gson.fromJson(json, Person.class);
        if (p.getFirstName() == null || p.getLastName() == null || p.getEmail() == null) {
            throw new InvalidDataException("Not enough data");
        }
        return Response.ok(gson.toJson(pf.addPerson(p))).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPerson(String json) {
        Person p = gson.fromJson(json, Person.class);
        return Response.ok(pf.updatePerson(p)).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(String json) {
        Person p = gson.fromJson(json, Person.class);
        if (pf.deletePerson(p) != null) {
            return Response.ok(json).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{}").build();
        }
    }
}
