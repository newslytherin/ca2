package exception;

import dto.ExceptionDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersonNotFoundExceptionMapper implements ExceptionMapper<PersonNotFoundException> {

    @Context
    ServletContext context;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(PersonNotFoundException e) {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        ExceptionDTO ex = new ExceptionDTO(e, 404, isDebug);
        ex.setDescription("Person not found");
        return Response.status(404)
                .entity(gson.toJson(ex))
                .type(MediaType.APPLICATION_JSON).
                build();
    }
}
