/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import deploy.DeploymentConfiguration;
import entity.User;
import facade.UserFacade;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.exception.UserAlreadyExistsException;
import rest.exception.UserNotFoundException;

/**
 * REST Web Service
 *
 * @author Alexander
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    private Gson gson;
    private UserFacade userFacade;

    public UserResource() {
        gson = new Gson();
        userFacade = new UserFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok(gson.toJson(userFacade.getUsers())).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getUser(@PathParam("id") int id) throws UserNotFoundException{
        User user = userFacade.getUser(id);
        if(user == null)
            throw new UserNotFoundException("No user with given id found");
        
        return Response.ok(gson.toJson(user)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) throws UserAlreadyExistsException{
        return Response.status(Response.Status.CREATED).entity(gson.toJson(userFacade.addUser(user))).build();
    }
}
