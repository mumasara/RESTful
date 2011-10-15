<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>place your order here</title>
</head>

<SCRIPT language=JavaScript>

function Calculate(){
	var drink = document.getElementById('drink').value;
	var size = document.getElementById('size').value;
	var addition = document.getElementById('addition').value;
	var total=0;
	var cost_drink=0;
	var cost_size=0;
	var cost_addition=0;
	
	if (drink == ""){
		alert("u didnt select any drink!");
		window.location.reload();
		//window.location.history();
	}
	else{
		cost_drink = 5;
		if (size != ""){
			cost_size=3;
		}
		if(addition != ""){
			cost_addition = 2;
		}
	}
	total = cost_drink + cost_size + cost_addition;
	document.getElementById("cost").value = total;
	//alert(drink);
}


</SCRIPT>

<body>
<FORM id = "form1" name="form1" action="rest/orders/generate_order" method=post>

<table border="1" >
<tr >

<td > drink </td>
<td> 
	<select name="drink" id = "drink">
		<option value="" selected></option>
  		<option value="chai">Chai</option>
  		<option value="cuppccino">Cuppccino</option>
  		<option value="mocca">Mocca</option>
  		<option value="latte">Latte</option>
  		<option value="longblack">LongBlack</option>
  		<option value="flatwhite">FlatWhite</option>
	</select>
</td>
</tr>

<tr>
<td > size </td>
<td >
	<select name="size" id = "size">
  		<option value="large">Large</option>
  		<option value="medium">Medium</option>
  		<option value="small">Small</option>
	</select>
</td>
</tr>


<tr >

<td > addition </td>
<td  >  
	<select name="addition" id = "addition">
		<option selected></option>
  		<option value="sugar">sugar</option>
  		<option value="syrup">syrup</option>
  		<option value="milk">milk</option>
  		<option value="choc">choc</option>
	</select>
</td>

</tr>

<tr>
<td>Pay Type</td>
<td>
	<select name="paytype" id = "paytype">
		<option value="cash" selected>cash</option>
  		<option value="card">card</option>
	</select>
</td>
</tr>

<tr >
<td > 
	 
</td>
<td >
<input name="submit" type="submit" value="Order" >
</td>
</tr>

</table>
</FORM>

<hr>




</body>
</html>