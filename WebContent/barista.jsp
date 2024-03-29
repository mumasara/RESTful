<%--
    Document   : index
    Created on : Oct 12, 2011, 8:31:33 PM
    Author     : charles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cs9322_coffee_model.Coffee"%>
<% ArrayList<Coffee> orders = (ArrayList<Coffee>) request.getAttribute("orders");%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="default.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Zig Zag Order System</title>
  </head>
  <body>
    <div id="admin-bar">
      <div class="quicklinks">
        <ul>
          <li id="home"><a href="/">Home</a></li>
          <li id="refresh"><a href="Barista">Refresh</a></li>
          <li id="cashier"><a href="Cashier">Customer/Cashier</a></li>
        </ul>
      </div>
      <div id="admin-bar-search-wrap">
        <form action="orderDetail" method="get" id="adminbarsearch">
          <input class="admin-bar-input" name="id" id="admin-bar-search" type="text" maxlength="150px" />
          <input type="submit" class="admin-bar-button" value="Search" />
        </form>
      </div>
    </div>
    <div id="page" class="hfeed">
      <div id="branding" class="banner">
        <h1 id="page-title">Barista View</h1>
      </div>
      <div id="content">
        <table id="order-table" class="main-table">
          <% for(Coffee order: orders) { %>
          <tr>
            <td class="order-id"><%=order.getId() %></td>
            <td class="action prepare"><a href="PrepareOrder?id=<%=order.getId()%>">Prepare</a></td>
            <td class="action check"><a href="CheckOrderPayment?id=<%=order.getId()%>">Check Payment</a></td>
            <td class="action release"><a href="ReleaseCoffee?id=<%=order.getId()%>">Release</a></td>
          </tr>
          <% } %>
        </table>
      </div>
    </div>
  </body>
</html>
