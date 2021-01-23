package edu.upc.dsa.services;


import edu.upc.dsa.TracksManager;
import edu.upc.dsa.TracksManagerImpl;
import edu.upc.dsa.UsuarioManager;
import edu.upc.dsa.UsuarioManagerImpl;
import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Api(value = "/usuario", description = "Endpoint to usuario Service")
@Path("/usuario")
public class UsuarioService {

    private UsuarioManager tm;
    final static Logger logger = Logger.getLogger(UsuarioManagerImpl.class);

    public UsuarioService() {
        this.tm = UsuarioManagerImpl.getInstance();

        //testRun();
    }

    /*private void testRun() {
        if (tm.sizeUser()==0) {
            Usuario jessi = new Usuario("jessi", "hola");
            this.tm.addUser(jessi);
        }
    }*/


    @POST
    @ApiOperation(value = "create a new Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 409, message = "User not found Or duplicated")
    })

    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(Usuario u) {
        try {
            if (u.getUsername() == null || u.getPassword() == null) return Response.status(500).entity(u).build();
            if(this.tm.addUser(u) == null) return Response.status(409).build();
            else return Response.status(201).entity(u).build();
        }
        catch(Exception e) {
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "loggin", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "User Not found")
    })
    // si el loggin es correcto se devuelve 201 y usuario,
    // si el usuario o password es incorrecto se devuelve un 404 not found
    // si hay un fallo de conexion o los datos introducidos estan en blanco se devuelve un 500.

    @Path("/logginuser/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loggin(Usuario u) {
        try {
            Usuario usuario = this.tm.loggin(u);
            if (u.getUsername() == null || u.getPassword() == null) return Response.status(500).entity(u).build();
            if( usuario == null) return Response.status(404).build();
            else return Response.status(201).entity(usuario).build();
        }
        catch(Exception e) {
            logger.info("In Exception");
            return Response.status(500).build();
        }
    }


    @GET
    @ApiOperation(value = "get Usuario Actualizado ", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/gettuser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioActualizado(@PathParam("id") String id) {

        Usuario u = this.tm.getUsuarioActualizado(id);

        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }

    @PUT
    @ApiOperation(value = "update User", notes = "updates User and returns code result")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/updateuser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(Usuario usuario) {
        usuario = this.tm.updateUser(usuario);
        if(usuario == null) return Response.status(404).build();
        return Response.status(201).entity(usuario).build();
    }






}
