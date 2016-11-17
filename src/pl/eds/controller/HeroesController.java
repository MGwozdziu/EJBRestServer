package pl.eds.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import pl.eds.mapper.HeroesMapper;
import pl.eds.model.entities.Heroes;
import pl.eds.model.entities.Items;

@Stateless
@Path("/heroes")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class HeroesController {

    private static Logger log = Logger.getLogger(HeroesController.class);

    @EJB
    private HeroesMapper heroesMapper;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeroes() {
	JsonObject json = new JsonObject();
	try {
	    json.add("heroes", new Gson().toJsonTree(heroesMapper.getHeroes()));
	    json.addProperty("status", "success");
	    json.addProperty("code", Response.Status.OK.getStatusCode());
	    json.addProperty("message", "");
	    return Response.ok(json.toString()).build();
	} catch (Exception e) {
	    log.error("Error at createHeroes " + e.getMessage());
	    json.addProperty("status", "error");
	    json.addProperty("code", Response.Status.NOT_FOUND.getStatusCode());
	    json.addProperty("message", "Error at getHeroes " + e.getMessage());
	    return Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
	}
    }
    
    @GET
    @Path("/{heroId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHero(@PathParam("heroId") long heroId) {
	JsonObject json = new JsonObject();
	try {
	    json.add("hero", new Gson().toJsonTree(heroesMapper.getHero(heroId)));
	    json.addProperty("status", "success");
	    json.addProperty("code", Response.Status.OK.getStatusCode());
	    json.addProperty("message", "");
	    return Response.ok(json.toString()).build();
	} catch (Exception e) {
	    log.error("Error at createHeroes " + e.getMessage());
	    json.addProperty("status", "error");
	    json.addProperty("code", Response.Status.NOT_FOUND.getStatusCode());
	    json.addProperty("message", "Error at getHeroes " + e.getMessage());
	    return Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
	}
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHero(Heroes heroes) {
	JsonObject json = new JsonObject();
	try {
	    heroesMapper.createHero(heroes);
	    json.addProperty("status", "success");
	    json.addProperty("code", Response.Status.OK.getStatusCode());
	    json.addProperty("message", "Hero " + heroes.getName() + " created");
	    return Response.ok(json.toString()).build();
	} catch (Exception e) {
	    log.error("Error at createHeroes " + e.getMessage());
	    json.addProperty("status", "error");
	    json.addProperty("code", Response.Status.NOT_FOUND.getStatusCode());
	    json.addProperty("message", "Error at createHeroes " + e.getMessage());
	    return Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
	}
    }
    
    @POST
    @Path("/{heroId}/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(@PathParam("heroId") long heroId, Items item) {
	JsonObject json = new JsonObject();
	try {
	    heroesMapper.createItem(heroId, item);
	    json.addProperty("status", "success");
	    json.addProperty("code", Response.Status.OK.getStatusCode());
	    json.addProperty("message", "Item " + item.getName() + " created");
	    return Response.ok(json.toString()).build();
	} catch (Exception e) {
	    log.error("Error at createHeroes " + e.getMessage());
	    json.addProperty("status", "error");
	    json.addProperty("code", Response.Status.NOT_FOUND.getStatusCode());
	    json.addProperty("message", "Error at createItem " + e.getMessage());
	    return Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
	}
    }

}
