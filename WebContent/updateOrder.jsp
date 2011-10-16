<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cs9322_coffee_model.Coffee"%>


<% Coffee c =(Coffee) request.getAttribute("prevOrder"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="default.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update your order <%=c.getId() %> here</title>
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
        <h1 id="page-title">Update your order</h1>
      </div>
      
      
      

     
 
      
      
      <div id="content">
        <form id="form1" name="form1" action="rest/orders/update_order" method=post>

          <table class="main-table order">
            <thead>
              <tr>
              	<th></th>
                <th>Coffee</th>
                <th>Size</th>
                <th>Addition</th>
                <th>Pay By</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              	<td>Previous choice:</td>
              	<td><%=c.getDrink() %></td>
      			<td><%=c.getSize() %></td>
      			<td><%=c.getAddition() %></td>
      			<td><%=c.getPayType() %>
              </tr>
            
              <tr>
              	<td>choose NOW:</td>
                <td><select name="drink" id="drink">
                    <option value="" selected></option>
                    <option value="chai">Chai</option>
                    <option value="cuppccino">Cuppccino</option>
                    <option value="mocca">Mocca</option>
                    <option value="latte">Latte</option>
                    <option value="longblack">LongBlack</option>
                    <option value="flatwhite">FlatWhite</option>
                  </select>
                </td>

                <td><select name="size" id="size">
                    <option value="large">Large</option>
                    <option value="medium">Medium</option>
                    <option value="small">Small</option>
                  </select>
                </td>
                <td><select name="addition" id="addition">
                    <option selected></option>
                    <option value="sugar">sugar</option>
                    <option value="syrup">syrup</option>
                    <option value="milk">milk</option>
                    <option value="choc">choc</option>
                  </select>
                </td>
                <td><select name="paytype" id="paytype">
                    <option value="cash" selected>cash</option>
                    <option value="card">card</option>
                  </select>
                </td>
              </tr>
              
              <tr>
              <input type="hidden" name = "order_id" id = "order_id" value=<%=c.getId() %> >
              </tr>
              
            </tbody>
          </table>
          <div class="buttons">
            <input type="submit" class="button submit-button" value="Place Order" />
          </div>
        </form>
      </div>
  </body>
  
</html>