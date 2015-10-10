package rest.exception; 

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserAlreadyExistsExceptionMapper implements ExceptionMapper<UserAlreadyExistsException> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(UserAlreadyExistsException ex) {
        ErrorMessage em = new ErrorMessage(ex, 409);
        return Response.status(Response.Status.CONFLICT).entity(gson.toJson(em)).type(MediaType.APPLICATION_JSON).build();
    }
}