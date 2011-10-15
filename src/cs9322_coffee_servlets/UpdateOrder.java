package cs9322_coffee_servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import cs9322_coffee_dao.ordersFileDao;
import cs9322_coffee_model.Coffee;

/**
 * Servlet implementation class UpdateOrder
 */
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			URL restURL = new URL(
					"http://localhost:8081/9322_assignment3/rest/orders/" + id);
			
		      BufferedReader br = new BufferedReader(new InputStreamReader(restURL.openStream()));
		      String orders = br.readLine();
		      
		      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		      DocumentBuilder builder = factory.newDocumentBuilder();
		      InputSource inStream = new InputSource();
		      inStream.setCharacterStream(new StringReader(orders));
		      Document document = builder.parse(inStream);
		      
		      Node order = document.getFirstChild();
		      
		      
		      
		      /***** display old info???***/
			
			
			
			
			//request.setAttribute("prevOrder", prevOrder);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("updateOrder.jsp");
		    //dispatcher.forward(request, response);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
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
