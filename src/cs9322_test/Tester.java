package cs9322_test;


import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


import cs9322_coffee_dao.ordersFileDao;
import cs9322_coffee_dao.paymentFileDao;
import cs9322_coffee_dao.processingItemDao;
import cs9322_coffee_model.Coffee;
import cs9322_coffee_model.Payment;


public class Tester {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		
		
//		// Create one coffee
//		Coffee c = new Coffee("5", "Latte","large",(float) 4.5,"syrup","cash",false);
//		ClientResponse response = service.path("rest").path("orders").path(c.getId()).accept(MediaType.APPLICATION_XML).put(ClientResponse.class, c);
//		// Return code should be 201 == created resource
//		System.out.println(response.getStatus());
//		Coffee a = new Coffee("1", "chai","medium",(float)4.5,"sugar","card",false);
//		Coffee b = new Coffee("2", "cupccino","large",(float)4.5,"syrup","cash",false);
//		Coffee c = new Coffee("3", "cccc","large",(float)4.5,"syrup","cash",false);
//		Coffee d = new Coffee("4", "hot_choc","large",(float)4.5,"syrup","cash",false);
		ordersFileDao ofd = new ordersFileDao();
//		
//		ofd.addOrder(a.getId(), a);
//		ofd.addOrder(b.getId(), b);
//		ofd.addOrder(c.getId(), c);
//		ofd.addOrder(d.getId(), d);
//		ofd.deleteOrder("3");
		
//		ofd.updateAnOrder("3", c);
//		ofd.payOrder("3");
		
////////////////////////////////////////////////////////////////////////////////////////////////
		
		
//		Payment p1 = new Payment("1","cash",(float)14.0,"cash",true);
//		Payment p2 = new Payment("2","card",(float)10.0,"1234567890",false);
//		
//		paymentFileDao pfd = new paymentFileDao();
//		
//		//pfd.addPayment("1", p1);
//		//pfd.addPayment("2", p2);
//		
//		float f = pfd.getCurrentBalance("2");
//		
//		System.out.println("sum is::" + f);
		
		
////////////////////////////////////////////////////////////////////////////////////////////////

//		Coffee c = ofd.search4Order("10-30-030");
//		processingItemDao pid = new processingItemDao();
		
//		pid.addItem("10-30-030", c);
		
//		pfd.deletePayment("2");
		
//		//Get orders/1
//		System.out.println(service.path("rest").path("orders/5").accept(
//				MediaType.APPLICATION_XML).get(String.class));
		
		
		
		
		//System.out.println(service.path("rest").path("orders").accept(MediaType.TEXT_XML).get(String.class));
		
		
		//14-45-045 mocca small 3.6 choc cash false
		//ordersFileDao ofd = new ordersFileDao();
		Coffee c = ofd.search4Order("14-45-045");
		
		if(c.equals(null) == true){
			System.out.println("order is NULL");
		}
		else{
//			System.out.println("c.getID:"+c.getId());
//			System.out.println("c.getPayType:"+c.getPaytype());
//			System.out.println("c.getCost:"+c.getCost());
//			System.out.println("c.getDetails:"+c.getPaytype());
//			System.out.println("c.getstatusFlag:"+c.getStatusFlag());
			
			paymentFileDao pfd = new paymentFileDao();	
			Payment p = new Payment(c.getId(),c.getPayType(),c.getCost(),c.getPayType(),true);
			pfd.addPayment(c.getId(), p);
		}

		
	}
	
    // Here, the Web application root ... 
    // (then the remainder of the path should contain 'rest/*')
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8081/9322_assignment3").build();
	}
}
