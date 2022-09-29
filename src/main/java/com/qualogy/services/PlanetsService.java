package com.qualogy.services;

import com.qualogy.entities.Planet;
import com.qualogy.repository.PlanetRepo;
import io.quarkus.security.spi.runtime.AuthorizationController;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/planets")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class PlanetsService {

    PlanetRepo planetRepo = new PlanetRepo();

    @Path("/get/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Planet> planetInfo() {
        return planetRepo.getPlanets();
    }

    @Path("/get/planet/by/name")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPlanet(@QueryParam("name") String name){
        return planetRepo.findPlanet(name)
                .map(Response::ok)
                .orElseGet(() -> Response.status(NOT_FOUND))
                .build();
    }

    @Path("/user/restricted/get/planet/by/name")
    @GET
    @RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getUserPlanet(@QueryParam("name") String name){
        return planetRepo.findPlanet(name)
                .map(Response::ok)
                .orElseGet(() -> Response.status(NOT_FOUND))
                .build();
    }

    @Path("/admin/allPermissions/get/planet/by/name")
    @GET
    @RolesAllowed("admin")
//    @PermitAll
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML})
    public Response getAdminPlanet(@QueryParam("name") String name, @Context SecurityContext sec){
        Principal user = sec.getUserPrincipal();
        String username = user != null ? user.getName() : "anonymous";
        return planetRepo.findPlanet(name)
                .map(Response::ok)
                .orElseGet(() -> Response.status(NOT_FOUND))
                .build();
    }


}