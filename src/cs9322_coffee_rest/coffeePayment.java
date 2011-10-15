package cs9322_coffee_rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import cs9322_coffee_dao.ordersFileDao;
import cs9322_coffee_dao.paymentFileDao;
import cs9322_coffee_model.Coffee;
import cs9322_coffee_model.Payment;

public class coffeePayment {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String id;
	
	public coffeePayment(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	
	
	// Produces HTML for browser-based client
	@GET
	@Produces(MediaType.TEXT_XML)
	public Payment getPaymentHTML() {
		List<Payment> cs = new ArrayList<Payment>();
		paymentFileDao pfd = new paymentFileDao();
		Payment p = new Payment();
		
		//System.out.println("in the coffeePayment.java "+ id);
		
		p = pfd.getPayment(id);
		
		if(p == null)
			throw new RuntimeException("GET: Payment with " + id +  " not found");
		
		return p;
	}
	
	
	
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	public Response putPayment(JAXBElement<Payment> p) {
		Payment newP = p.getValue(); 
		//System.out.println("putPayment:: "+id);
		
		return putAndGetResponse(newP);
	}
	
	
	
	private Response putAndGetResponse(Payment p) {
		Response resp;
		
		//System.out.println("PutAndGetResponse:: "+id );
		
		paymentFileDao pfd = new paymentFileDao();
		Payment temp = pfd.getPayment(id);
		
		
		//if the payment exists
		//that is, temp != null
		if(temp.equals(null) == false){
			resp = Response.noContent().build();
		}
		
		//else create a new one
		//temp 
		else {
			resp = Response.created(uriInfo.getAbsolutePath()).build();
		}
		//BooksDao.instance.getStore().put(b.getId(), b);
		pfd.addPayment(id, p);
		return resp;
	}
	
	
}
