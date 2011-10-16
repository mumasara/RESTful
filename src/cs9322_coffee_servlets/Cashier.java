/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs9322_coffee_servlets;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import cs9322_coffee_dao.ordersFileDao;
import cs9322_coffee_model.Coffee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
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

/**
 *
 * @author charles
 */
public class Cashier extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      URL restURL = new URL("http://localhost:8081/9322_assignment3/rest/orders");
      BufferedReader br = new BufferedReader(new InputStreamReader(restURL.openStream()));
      String orders = br.readLine();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      InputSource inStream = new InputSource();
      inStream.setCharacterStream(new StringReader(orders));
      Document document = builder.parse(inStream);
      
      Node root = document.getFirstChild();
      
      NodeList coffees = root.getChildNodes();
      ArrayList<Coffee> coffeeList = new ArrayList<Coffee>();
      
      for(int i = 0; i < coffees.getLength(); i ++) {
    	  Node coffee = coffees.item(i);
    	  Coffee newItem = new Coffee();
    	  coffee = coffee.getFirstChild();
    	  newItem.setAddition(coffee.getTextContent());
    	  
    	  //System.out.println("in Cashier:: "+coffee.getTextContent());
    	  
    	  coffee = coffee.getNextSibling();
    	  newItem.setCost(Float.parseFloat(coffee.getTextContent()));
    	  coffee = coffee.getNextSibling();
    	  newItem.setDrink(coffee.getTextContent());
    	  coffee = coffee.getNextSibling();
    	  newItem.setId(coffee.getTextContent());
    	  
    	  coffee = coffee.getNextSibling();
    	  newItem.setPayType(coffee.getTextContent());
    	  
    	  coffee = coffee.getNextSibling();
    	  newItem.setSize(coffee.getTextContent());
    	  coffee = coffee.getNextSibling();
    	  newItem.setStatusFlag(Boolean.parseBoolean(coffee.getTextContent()));
    	  coffeeList.add(newItem);
      }
      request.setAttribute("orders", coffeeList);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/cashier.jsp");
      dispatcher.forward(request, response);
    } catch (SAXException ex) {	
      Logger.getLogger(Cashier.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
}
