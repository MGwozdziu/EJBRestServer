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

import pl.eds.mapper.ItemsMapper;
import pl.eds.model.entities.Items;

@Stateless
@Path("/items")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ItemsController {

    private static Logger log = Logger.getLogger(ItemsController.class);

    @EJB
    private ItemsMapper itemsMapper;
    
    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("itemId") long itemId) {
	JsonObject json = new JsonObject();
	try {
	    json.add("hero", new Gson().toJsonTree(itemsMapper.getItem(itemId)));
	    json.addProperty("status", "success");
	    json.addProperty("code", Response.Status.OK.getStatusCode());
	    json.addProperty("message", "");
	    return Response.ok(json.toString()).build();
	} catch (Exception e) {
	    log.error("Error at getItem " + e.getMessage());
	    json.addProperty("status", "error");
	    json.addProperty("code", Response.Status.NOT_FOUND.getStatusCode());
	    json.addProperty("message", "Error at getItem " + e.getMessage());
	    return Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
	}
    }
    
    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(Items item) {
	JsonObject json = new JsonObject();
	try {
	    itemsMapper.createItem(item);
	    json.addProperty("status", "success");
	    json.addProperty("code", Response.Status.OK.getStatusCode());
	    json.addProperty("message", "Item " + item.getName() + " created");
	    return Response.ok(json.toString()).build();
	} catch (Exception e) {
	    log.error("Error at createItem " + e.getMessage());
	    json.addProperty("status", "error");
	    json.addProperty("code", Response.Status.NOT_FOUND.getStatusCode());
	    json.addProperty("message", "Error at createItem " + e.getMessage());
	    return Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
	}
    }
}
