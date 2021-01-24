package edu.upc.dsa.services;
import edu.upc.dsa.*;
import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.Objetos;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.UsuarioManager;
import edu.upc.dsa.UsuarioManagerImpl;
import edu.upc.dsa.ObjetosManager;
import edu.upc.dsa.ObjetosManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Api(value = "/Objetos", description = "Endpoint to Objetos Service")
@Path("/Objetos")
public class ObjetosService {

    private UsuarioManager tm;
    private ObjetosManager om;
    final static Logger logger = Logger.getLogger(ObjetosService.class);

    public ObjetosService() {
        this.tm = UsuarioManagerImpl.getInstance();
        this.om= ObjetosManagerImpl.getInstance();
    }

/*
    @GET
    @ApiOperation(value = "get all Objects", notes = "Devuelve todos los objetos del juego")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objetos.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllObjects() {

        List<Objetos> objetos = this.om.getAllObjects();

        GenericEntity<List<Objetos>> entity = new GenericEntity<List<Objetos>>(objetos) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get an Object", notes = "Devuelve un objeto del juego por su nombre")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objetos.class),
            @ApiResponse(code = 404, message = "Object not found")
    })
    @Path("/{objectName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectFromAllObjects(@PathParam("objectName") String objectName) {
        Objetos o = this.om.getObjectFromAllObjects(objectName);
        if (o == null) return Response.status(404).build();
        else  return Response.status(201).entity(o).build();
    }

    @GET
    @ApiOperation(value = "get an Object from User", notes = "Devuelve un objeto de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objetos.class),
            @ApiResponse(code = 404, message = "Object not found")
    })
    @Path("/{objectName}/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectFromUser(@PathParam("objectName") String objectName,@PathParam("userName") String userName) {
        Objetos o = this.om.getObjectUser(this.tm.getUser(userName),objectName);
        if (o == null) return Response.status(404).build();
        else  return Response.status(201).entity(o).build();
    }
*/


///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GET
    @ApiOperation(value = "get all Objects", notes = "Devuelve todos los objetos del juego")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objetos.class, responseContainer="List"),
    })
    @Path("/getobjetos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllObjetos() {

        List<Objetos> objetos = this.om.getAllObject();

        GenericEntity<List<Objetos>> entity = new GenericEntity<List<Objetos>>(objetos) {};
        return Response.status(201).entity(entity).build()  ;

    }
    @POST
    @ApiOperation(value = "addobjeto", notes = "Añadir un objeto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objetos.class),
            @ApiResponse(code = 402, message = "No hay sufiente dinero"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/addobjeto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addObjeto(Objetos o) {

        if(o.getId()==null || o.getUserId()==null)  return Response.status(400).build();
        if(o.getId().equals("")||o.getUserId().equals("")||o.getUserId().isEmpty()||o.getId().isEmpty()) return Response.status(400).build();

        if(this.om.addObject(o) == true){
            return Response.status(201).entity(o).build();
        }
        else return Response.status(402).build();

    }

    @PUT
    @ApiOperation(value = "borrar un objeto de un usuario", notes = "usar un objeto de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objetos.class),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/deleteobjetousario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteObjetos(Objetos o) {
        int resultado = this.om.deleteObjectUsuario(o);
        if(resultado == 0){
            return Response.status(201).entity(o).build();
        }
        else return Response.status(402).build();

    }

}