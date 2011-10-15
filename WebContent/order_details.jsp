<%@ page language="java" import = "cs9322_coffee_model.Coffee;" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ur order details:</title>
</head>
<body>
<% 
Coffee c = (Coffee)session.getAttribute("order_details"); 
%>

<h2>ur order_id <%=c.getId() %></h2><br>
<h3>ur drink <%=c.getDrink() %></h3><br>
<h3>ur cost <%=c.getCost() %></h3>



</body>
</html>