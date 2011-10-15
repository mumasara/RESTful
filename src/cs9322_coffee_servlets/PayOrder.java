package cs9322_coffee_servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import cs9322_coffee_dao.ordersFileDao;
import cs9322_coffee_model.Coffee;
import cs9322_coffee_model.Payment;

/**
 * Servlet implementation class PayOrder
 */
public class PayOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(getBaseURI());
			
			
			String id = request.getParameter("id");
			//System.out.println("^^^^^^^^^^^^^^^^^^^ "+id);
			
			//get the coffee order here
			
			ordersFileDao ofd = new ordersFileDao();
			Coffee c = ofd.search4Order(id);
			
			
			if(c.equals(null) == false){
			//create the payment obj
				Payment p = new Payment();
				p.setPID(id);
				p.setPayType(c.getPaytype());
				p.setAmount(c.getCost());
				p.setDetails(c.getPaytype());
				p.setStatusFlag(true);
				
				//System.out.println("payment details:: "+p.getDetails());
				
				//same as the book client
				ClientResponse ClientResponse = service.path("rest").path("payments").path(id).accept(MediaType.APPLICATION_XML).put(ClientResponse.class, p);
				
				response.sendRedirect("/9322_assignment3/Cashier");
			}
			else{
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("order not found");
			}
			
			
		      
		      
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8081/9322_assignment3/").build();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
