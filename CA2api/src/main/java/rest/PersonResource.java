package rest;

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
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonByPhone(@PathParam("phone") String phone) {
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonByEmail(@PathParam("email") String email) {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
