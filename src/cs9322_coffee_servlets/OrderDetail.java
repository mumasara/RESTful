/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs9322_coffee_servlets;

import cs9322_coffee_model.Coffee;
import cs9322_coffee_util.CoffeeMaker;
import cs9322_coffee_util.CoffeeParser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author charles
 */
public class OrderDetail extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String id = request.getParameter("id");
    CoffeeParser parser = new CoffeeParser("http://localhost:8080/9322_assignment3/rest/orders/" + id);
    Document document = parser.getParsedDocument();

    Node root = document.getFirstChild();
    Node coffeeNode = root;

    Coffee coffee = new Coffee();
    CoffeeMaker.make(coffee, coffeeNode);
    request.setAttribute("coffee", coffee);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/orderDetail.jsp");
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
