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

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Api(value = "/usuario", description = "Endpoint to usuario Service")
@Path("/usuario")
public class UsuarioService {

    private UsuarioManager tm;

    public UsuarioService() {
        this.tm = UsuarioManagerImpl.getInstance();
    }


    @POST
    @ApiOperation(value = "create a new Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(Usuario u) {
        try {
            if (u.getUsername() == null || u.getPassword() == null) return Response.status(500).entity(u).build();
            this.tm.addUser(u);
            return Response.status(201).entity(u).build();
        }
        catch(Exception e) {
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "loggin", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    // si el loggin es correcto se devuelve 201 y usuario,
    // si el usuario o password es incorrecto se devuelve un 404 not found
    // si hay un fallo de conexion o los datos introducidos estan en blanco se devuelve un 500.

    @Path("/logginuser/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loggin(Usuario u) {
        try {
            if (u.getUsername() == null || u.getPassword() == null) return Response.status(500).entity(u).build();
            this.tm.loggin(u);
            if( this.tm.loggin(u) == null) return Response.status(404).build();
            else return Response.status(201).entity(u).build();


        }
        catch(Exception e) {
            return Response.status(500).build();
        }
    }


}
