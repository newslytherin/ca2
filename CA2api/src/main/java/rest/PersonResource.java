package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.PersonFacade;
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
    public Response getJson(@QueryParam("street") String street, @QueryParam("hobby") String hobby, @QueryParam("zipcode") int zip) {
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonById(@PathParam("id") int id) {
        return Response.ok(gson.toJson(pf.getPersonDTOById(id))).build();
    }

    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonByPhone(@PathParam("phone") String phone) {
        return Response.ok(gson.toJson(pf.getPersonDTOByPhone(phone))).build();
    }

    @GET
    @Path("email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonByEmail(@PathParam("email") String email) {
        return Response.ok(gson.toJson(pf.getPersonDTOByEmail(email))).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
