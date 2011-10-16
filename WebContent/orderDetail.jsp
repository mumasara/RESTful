<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="cs9322_coffee_model.Coffee" %>
<% Coffee coffee = (Coffee)request.getAttribute("coffee"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="default.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Details</title>
  </head>

  <body>
    <div id="admin-bar">
      <div class="quicklinks">
        <ul>
          <li id="home"><a href="/">Home</a></li>
          <li id="add-order"><a href="addOrder.jsp">Add</a></li>
          <li id="check-order"><a href="Cashier">Check</a></li>
          <li id="barista"><a href="Barista">Barista</a></li>
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
        <h1 id="page-title">Order details</h1>
      </div>
      <div id="content">
          <table class="main-table order">
            <thead>
              <tr>
                <th>Drink</th>
                <th>Addition</th>
                <th>Size</th>
                <th>Cost</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <%=coffee.getDrink() %>
                </td>
                <td>
                  <%=coffee.getAddition() %>
                </td>
                <td>
                  <%=coffee.getSize() %>
                </td>
                <td>
                  <%=coffee.getCost() %>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="buttons">
            <a href="" class="button submit-button">Update</a>
            <a href="" class="button submit-button">Cancel</a>
            <a href="" class="button submit-button">Pay</a>
            <a href="" class="button submit-button">Option</a>
          </div>
      </div>
  </body>
</html>