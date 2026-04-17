package services;

import models.Estudiante;
import models.Instituto;
import manager.MathManager;
import manager.MathManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import models.Operacion;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Math", description = "Endpoint to Math Service")
@Path("/Math")

public class MathService {

    private MathManager bm;

    public MathService() {
        this.bm = MathManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Afegir un nou estudiant")
    @Path("/estudiant")
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response addEstudiant(Estudiante estudiante) {
        this.bm.añadirEstudiante(estudiante,  estudiante.getId());
        return Response.status(201).build();
    }
    @POST
    @ApiOperation(value = "Afegir un nou institut")
    @Path("/institut")
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response addInstitut(Instituto instituto) {
        this.bm.añadirInstituto(instituto);
        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "Enviar Operacion")
    @Path("/Operacion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOperacion(Operacion operacion) {
        this.bm.recibirOperacion(operacion);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Procesar operacio que toca ")
    @Path("/procesar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response procesarOperacion() {
        Operacion o = this.bm.procesarSiguienteOperacion();
        if (o == null) return Response.status(404).build(); // Cola vacia
        return Response.status(200).entity(o).build();
    }



    @GET
    @ApiOperation(value = "Consultar operaciones de un estudiante")
    @Path("/estudiantes/{idEstudiante}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperacionesEstudiante(@PathParam("idEstudiante") String idEstudiante) {
        List<Operacion> lista = this.bm.getOperacionesPorEstudiante(idEstudiante);
        GenericEntity<List<Operacion>> entity = new GenericEntity<List<Operacion>>(lista) {};
        return Response.status(200).entity(entity).build();
    }
    @GET
    @ApiOperation(value = "Consultar operaciones de un instituto")
    @Path("/instituto/{idInstituto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperacionesInstituto(@PathParam("idInstituto") String idInstituto) {
        List<Operacion> lista = this.bm.getOperacionesPorInstituto(idInstituto);
        GenericEntity<List<Operacion>> entity = new GenericEntity<List<Operacion>>(lista) {};
        return Response.status(200).entity(entity).build();
    }


}

