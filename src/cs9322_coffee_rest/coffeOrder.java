package cs9322_coffee_rest;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;


import cs9322_coffee_model.Coffee;
import cs9322_coffee_dao.coffeeDao;
import cs9322_coffee_dao.ordersFileDao;


public class coffeOrder {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String id;
	
	public coffeOrder(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	
	// Produces HTML for browser-based client
	@GET
	@Produces(MediaType.TEXT_XML)
	public Coffee getOrderHTML() {
//		Coffee c = coffeeDao.instance.getOrders().get(id);
		List<Coffee> cs = new ArrayList<Coffee>();
		ordersFileDao ofd = new ordersFileDao();
		Coffee c = new Coffee();
		
		int i=0;
		ArrayList<Coffee> temp = new ArrayList<Coffee>(); 
		temp = ofd.getAllOrders();
		
		while(i<temp.size()){
			if( temp.get(i).getId().equals(id) == true ){
				c = temp.get(i);
				break;
			}
		}
		
		if(c==null)
			throw new RuntimeException("GET: Coffee with " + id +  " not found");
		
		return c;
	}

	
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	public Response putOrder(JAXBElement<Coffee> c) {
		Coffee newb = c.getValue();
		System.out.println("add drink "+newb.getDrink());
		
		return putAndGetResponse(newb);
	}
	
	
	private Response putAndGetResponse(Coffee c) {
		Response res;
		if(coffeeDao.instance.getOrders().containsKey(c.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		coffeeDao.instance.getOrders().put(c.getId(), c);
		return res;
	}
	
}
