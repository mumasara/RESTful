package cs9322_coffee_rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.UriInfo;

import cs9322_coffee_dao.coffeeDao;
import cs9322_coffee_dao.ordersFileDao;
import cs9322_coffee_dao.paymentFileDao;
import cs9322_coffee_model.Coffee;
import cs9322_coffee_model.Payment;



@Path("/payments")
public class coffeePayments {
	
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
	public List<Payment> getPaymentsBrowser() {
		List<Payment> cs = new ArrayList<Payment>();
		
		//invoke File operation ordersFileDao
		//cs.addAll( coffeeDao.instance.getOrders().values() );		
		
		List<Payment> ps = new ArrayList<Payment>();
		paymentFileDao pfd = new paymentFileDao();
		
		//cs.addAll( coffeeDao.instance.getOrders().values() );
		int i=0;
		ArrayList<Payment> temp = new ArrayList<Payment>(); 
		temp = pfd.getAllPayments();
		
		while(i<temp.size()){
			Payment p = new Payment();
			p = temp.get(i);
			ps.add(p);
			i++;
		}
		
		//System.out.println("for the user in eclipse"+ps.size());	
		return ps; 
	}
	
	
	
	// Return the list of coffees for client applications/programs
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Payment> getPayments() {
		List<Payment> cs = new ArrayList<Payment>();
		
		//invoke File operation ordersFileDao
		//cs.addAll( coffeeDao.instance.getOrders().values() );		
		
		List<Payment> ps = new ArrayList<Payment>();
		paymentFileDao pfd = new paymentFileDao();
		
		//cs.addAll( coffeeDao.instance.getOrders().values() );
		int i=0;
		ArrayList<Payment> temp = new ArrayList<Payment>(); 
		temp = pfd.getAllPayments();
		
		while(i<temp.size()){
			Payment p = new Payment();
			p = temp.get(i);
			ps.add(p);
			i++;
		}
		
		//System.out.println("for the user in the browser"+ps.size());	
		return ps; 
	}
	
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPaymentsCount() {
		//int count = BooksDao.instance.getStore().size();
		paymentFileDao pfd = new paymentFileDao();
		ArrayList<Payment> temp = new ArrayList<Payment>(); 
		temp = pfd.getAllPayments();
		int count = temp.size();
		return String.valueOf(count);
	}
	
	
	
	@Path("addpayment")
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newPayment(
			@FormParam("order_id") String order_id,
			@Context HttpServletResponse servletResponse
			) throws IOException {
		
		System.out.println("new order_id comes:: "+order_id);
		
		Coffee c = new Coffee();
		
		ordersFileDao ofd = new ordersFileDao();
		paymentFileDao pfd = new paymentFileDao();
		
		int i=0;
		ArrayList<Coffee> temp = new ArrayList<Coffee>(); 
		temp = ofd.getAllOrders();
		//find the corresponding order
		while(i<temp.size()){
			if (temp.get(i).getId().equals(order_id) == true){
				c = temp.get(i);
			}
			i++;
		}
		//card
		if (c.getPayType().equals("card") == true){
			Payment p = new Payment(c.getId(), c.getPayType(), c.getCost(), c.getPayType(), true);
			pfd.addPayment(order_id, p);
			return "card swipped !";
			
		}
		else{
			Payment p = new Payment(c.getId(), c.getPayType(), c.getCost(), c.getPayType(), true);
			pfd.addPayment(order_id, p);
			return "cash paid !";
		}
		
	}
	

	
	@Path("{id}")
	public coffeePayment getPayment(
			@PathParam("id") String id) {
		
		//System.out.println("coffeePayments.java:: "+id);
		
		return new coffeePayment(uriInfo, request, id);
	}
	
}
