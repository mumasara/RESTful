package cs9322_coffee_rest;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import   java.text.SimpleDateFormat;


import cs9322_coffee_model.Coffee;
import cs9322_coffee_dao.additionCost;
import cs9322_coffee_dao.coffeeDao;
import cs9322_coffee_dao.drinkCostDao;
import cs9322_coffee_dao.ordersFileDao;
import cs9322_coffee_dao.paymentDao;
import cs9322_coffee_dao.sizeCostDao;



@Path("/orders")
public class coffeeOrders {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	@Context
	HttpServletRequest req;
	@Context
	HttpServletResponse resp;
	
	// Return the list of coffees to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Coffee> getOrdersBrowser() {
		List<Coffee> cs = new ArrayList<Coffee>();
		ordersFileDao ofd = new ordersFileDao();

		int i=0;
		ArrayList<Coffee> temp = new ArrayList<Coffee>(); 
		temp = ofd.getAllOrders();
		while(i<temp.size()){
			Coffee c = new Coffee();
			c = temp.get(i);
			cs.add(c);
			i++;
		}
		
		return cs; 
	}
	
	// Return the list of coffees for client applications/programs
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Coffee> getOrders() {
		List<Coffee> cs = new ArrayList<Coffee>();
		ordersFileDao ofd = new ordersFileDao();

		int i=0;
		ArrayList<Coffee> temp = new ArrayList<Coffee>(); 
		temp = ofd.getAllOrders();
		while(i<temp.size()){
			Coffee c = new Coffee();
			c = temp.get(i);
			cs.add(c);
			i++;
		}
		
//		Iterator it = coffeeDao.instance.getOrders().entrySet().iterator();
//		
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			Object key = entry.getKey();
//			Object value = entry.getValue();
//			System.out.println("key=" + key + " value=" + value);
//		}

		
		//System.out.println("for client app"+cs.size());
		
		return cs; 
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		//int count = coffeeDao.instance.getOrders().size();
		ordersFileDao ofd = new ordersFileDao();
		int i=0;
		ArrayList<Coffee> temp = new ArrayList<Coffee>(); 
		temp = ofd.getAllOrders();
		
		int count = temp.size();
		
		return String.valueOf(count);
	}
	
	
	
	@Path("generate_order")
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newOrder(
			@FormParam("id") String id,
			@FormParam("drink") String drink,
			@FormParam("size") String size,
			@FormParam("cost") float cost,
			@FormParam("addition") String addition,
			@FormParam("paytype") String paytype,
			@FormParam("status") boolean status,
			@Context HttpServletResponse servletResponse
	) throws IOException {
		
		System.out.println("new order comes");
		//System.out.println("addition::*"+addition+"*");
		//System.out.println("id::"+id+" drink::"+drink+" cost::"+cost+" addition::"+addition+" status::"+status);
		
		//Calculating the price
		float addition_cost=0;
		if(addition.equals("")==true){
			addition = "no";
		}
		else{
			addition_cost = additionCost.instance.getAdditionCost().get(addition);
		}
		
		float drink_cost = drinkCostDao.instance.getDrinkCost().get(drink);
		float size_cost = drink_cost* (1 + sizeCostDao.instance.getSizeCost().get(size));
		
		
		cost = size_cost + addition_cost;
		
		//generating id
		SimpleDateFormat formatter = new SimpleDateFormat ("mm-ss-sss");
		Date curDate = new Date(System.currentTimeMillis());
		id = formatter.format(curDate);
		
		
		Coffee c = new Coffee(id,drink,size,cost,addition,paytype,status);	
		System.out.println("id::"+id+" drink::"+drink+" cost::"+cost+" addition::"+addition+" status::"+status);
		
		ordersFileDao ofd =  new ordersFileDao();
		ofd.addOrder(c.getId(), c);
		
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();
		
	
		req.getSession().setAttribute("order_id", id);
		resp.sendRedirect("../../create_order.jsp");
	}
	

	@Path("update_order")
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateOrder(
			@FormParam("order_id") String id,
			@FormParam("drink") String drink,
			@FormParam("size") String size,
			@FormParam("cost") float cost,
			@FormParam("addition") String addition,
			@FormParam("paytype") String paytype,
			@FormParam("status") boolean status,
			@Context HttpServletResponse servletResponse
			) throws IOException {
		
		System.out.println("updated order comes");
		//System.out.println("addition::*"+addition+"*");
		//System.out.println("id::"+id+" drink::"+drink+" cost::"+cost+" addition::"+addition+" status::"+status);
		
		//Calculating the price
		float addition_cost=0;
		if(addition.equals("")==true){
			addition = "no";
		}
		else{
			addition_cost = additionCost.instance.getAdditionCost().get(addition);
		}
		
		float drink_cost = drinkCostDao.instance.getDrinkCost().get(drink);
		float size_cost = drink_cost* (1 + sizeCostDao.instance.getSizeCost().get(size));
		
		
		cost = size_cost + addition_cost;
		
		
		Coffee c = new Coffee(id,drink,size,cost,addition,paytype,status);	
		System.out.println("id::"+id+" drink::"+drink+" cost::"+cost+" addition::"+addition+" status::"+status);
		
		ordersFileDao ofd =  new ordersFileDao();
		ofd.updateAnOrder(id, c);
		
		System.out.println("order "+ id +" has been updated");
		
//		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
//		Response.created(uri).build();
//		
//	
//		req.getSession().setAttribute("order_id", id);
//		resp.sendRedirect("../../create_order.jsp");
		
		
		
	}
	
	
	
	@Path("delete")
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void deleteOrder(
			@FormParam("order_id") String id,
			@Context HttpServletResponse servletResponse
	) throws IOException{
		//Coffee c = coffeeDao.instance.getOrders().get(id);
		//System.out.println("id is +++" + id);
		
		Coffee delOrder = new Coffee();
		ordersFileDao ofd =  new ordersFileDao();
		
		delOrder = ofd.search4Order(id);
		
		if(delOrder==null)
			throw new RuntimeException("DELETE: Order with " + id +  " not found");
		
		int status = ofd.deleteOrder(id);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();
		
	
		///req.getSession().setAttribute("order_id", id);
		resp.sendRedirect("../../goodbye.jsp");
		
	}
		
	
	
	
	@Path("{order}")
	public coffeOrder getOrder(
			@PathParam("order") String id) {
		//System.out.println("id in coffeOrders.java::" + id);
		return new coffeOrder(uriInfo, request, id);
	}
	
	
}
