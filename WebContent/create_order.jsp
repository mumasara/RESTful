<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>order created</title>
  </head>
  <body>
    <%
      String order_id = (String) session.getAttribute("order_id");
    %>
    <h1>ur order <%=order_id%> has been successfully created!</h1>
    <form id = "form1" name="form1" action="rest/orders/get_order" method=post>
      <input type="hidden" name = "order_id" id = "order_id" value=<%=order_id%> >
      <input name="submit" type="submit" value="Check">
    </form>
    <form id = "form2" name="form2" action="rest/orders/delete" method=post>
      <input type="hidden" name = "order_id" id = "order_id" value=<%=order_id%> >
      <input name="submit" type="submit" value="Cancel">
    </form>
    <form id = "form3" name="form3" action="rest/payments/addpayment" method=post>
      <input type="hidden" name = "order_id" id = "order_id" value=<%=order_id%> >
      <input name="submit" type="submit" value="Pay">
    </form>
  </body>
</html>