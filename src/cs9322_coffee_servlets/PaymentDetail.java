package cs9322_coffee_servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cs9322_coffee_model.Coffee;
import cs9322_coffee_model.Payment;

/**
 * Servlet implementation class PaymentDetail
 */
public class PaymentDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			
			//System.out.println("^^^^^^^^^^^^^^^^^^^ "+id);
			
			URL restURL = new URL(
					"http://localhost:8081/9322_assignment3/rest/payments/" + id);
			
		      BufferedReader br = new BufferedReader(new InputStreamReader(restURL.openStream()));
		      String orders = br.readLine();
		      
//		      response.setContentType("text/html");
//		      
//		      PrintWriter out = response.getWriter() ;
//		      out.println(orders);
		      
		      
		      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		      DocumentBuilder builder = factory.newDocumentBuilder();
		      InputSource inStream = new InputSource();
		      inStream.setCharacterStream(new StringReader(orders));
		      Document document = builder.parse(inStream);
		      
		      //payment
		      Node payment = document.getFirstChild();
		      //System.out.println("1::::payment::: n.getNodeName() "+ payment.getTextContent());

		      
		      Payment p = new Payment();
		      
		      //amount
		      Node n = payment.getFirstChild();
		      p.setAmount(Float.parseFloat(n.getTextContent()));
		      //System.out.println("2::: amount::: n.getNodeName() "+n.getTextContent());
		      
		      //details
		      n = n.getNextSibling();
		      //System.out.println("3::: details::: n.getNodeName() "+n.getTextContent());
		      
		      p.setDetails(n.getTextContent());
		      //System.out.println("3::: details::: n.getNodeName() "+n.getTextContent());
		      
		      //PID
		      n = n.getNextSibling();
		      p.setPID(n.getTextContent());
		      //System.out.println("4::: pID::: n.getNodeName() "+n.getTextContent());
		      
		      //payType
		      n = n.getNextSibling();
		      p.setPayType(n.getTextContent());
		      //System.out.println("5::: payType::: n.getNodeName() "+n.getTextContent());
		      
		      //statusFlag
		      n = n.getNextSibling();
		      //p.setStatusFlag(n.getTextContent());
		      if (n.getTextContent().equals("true") == true){
		    	  p.setStatusFlag(true);
		      }
		      else{
		    	  p.setStatusFlag(false);
		      }
		      //System.out.println("6::: statusFlag::: n.getNodeName() "+n.getTextContent());
		      
		      
		      
// ----------------------------------------------------------------------------------------
// 			  could be some problem from here		      
		      
		      //System.out.println(p.getPID());
		      System.out.println(p.getPID());
		      request.setAttribute("payment", p);
		      RequestDispatcher dispatcher = request.getRequestDispatcher("/paymentDetails.jsp");
		      dispatcher.forward(request, response);
		      

		} catch (SAXException ex) {
			Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
