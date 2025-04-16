package com.restaurant.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/restaurant")
public class RestaurantResource {

    private static final Map<String, Dish> menu = new HashMap<>();

    static {
        menu.put("Pizza Margarita", new Dish("Pizza Margarita", 8.50));
        menu.put("Spaghetti Carbonara", new Dish("Spaghetti Carbonara", 9.00));
        menu.put("Lasagna", new Dish("Lasagna", 10.00));
        menu.put("Tiramisu", new Dish("Tiramisu", 4.50));
    }

    // GET /restaurant/menu
    @GET
    @Path("/menu")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Dish> getMenu() {
        return menu.values();
    }

    // GET /restaurant/menu/{name}
    @GET
    @Path("/menu/{name}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDish(@PathParam("name") String name) {
        Dish dish = menu.get(name);
        if (dish == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Dish not found: " + name).build();
        }
        return Response.ok(dish).build();
    }

    // POST /restaurant/menu
    @POST
    @Path("/menu")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addDish(Dish dish, @Context UriInfo uriInfo) {
        if (menu.containsKey(dish.getName())) {
            return Response.status(Response.Status.CONFLICT)
                           .entity("Dish already exists").build();
        }
        menu.put(dish.getName(), dish);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(dish.getName());
        return Response.created(builder.build()).build();
    }

    // PUT /restaurant/menu/{name}
    @PUT
    @Path("/menu/{name}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateDish(@PathParam("name") String name, Dish updatedDish) {
        if (!menu.containsKey(name)) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Dish not found: " + name).build();
        }
        menu.put(name, updatedDish);
        return Response.noContent().build(); // 204
    }

    // DELETE /restaurant/menu/{name}
    @DELETE
    @Path("/menu/{name}")
    public Response deleteDish(@PathParam("name") String name) {
        if (menu.remove(name) != null) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Dish not found: " + name).build();
        }
    }


}
