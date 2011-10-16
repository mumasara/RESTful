/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package cs9322_coffee_servlets;

import cs9322_coffee_model.Coffee;
import cs9322_coffee_util.CoffeeMaker;
import cs9322_coffee_util.CoffeeParser;
import java.io.*;
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

/**
 *
 * @author charles
 */
public class Barista extends HttpServlet {

  /**
   * Processes requests for both HTTP
   * <code>GET</code> and
   * <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    CoffeeParser parser = new CoffeeParser("http://localhost:8080/9322_assignment3/rest/orders");
    Document document = parser.getParsedDocument();

    Node root = document.getFirstChild();

    NodeList coffees = root.getChildNodes();
    ArrayList<Coffee> coffeeList = new ArrayList<Coffee>();

    for (int i = 0; i < coffees.getLength(); i++) {
      Node coffeeNode = coffees.item(i);
      Coffee coffee = new Coffee();
      CoffeeMaker.make(coffee, coffeeNode);
      coffeeList.add(coffee);
    }
    request.setAttribute("orders", coffeeList);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/barista.jsp");
    dispatcher.forward(request, response);
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
