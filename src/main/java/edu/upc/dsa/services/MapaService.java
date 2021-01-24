package edu.upc.dsa.services;

import edu.upc.dsa.*;
import edu.upc.dsa.models.Mapa;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/mapa", description = "Endpoint to Objetos Service")
@Path("/mapa")
public class MapaService {

        private UsuarioManager tm;
        private ObjetosManager om;
        private MapaManager mm;
        final static Logger logger = Logger.getLogger(edu.upc.dsa.services.MapaService.class);

        public MapaService() {
            this.tm = UsuarioManagerImpl.getInstance();
            this.om= ObjetosManagerImpl.getInstance();
            this.mm= MapaManagerImpl.getInstance();
        }

    @GET
    @ApiOperation(value = "get Mapa", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mapa.class),
            @ApiResponse(code = 404, message = "Mapa not found")
    })
    @Path("/getmapa/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMapa(@PathParam("id") String id) {

        Mapa mapa = this.mm.getMapa(id);

        if (mapa == null) return Response.status(404).build();
        else  return Response.status(201).entity(mapa).build();
    }

}
