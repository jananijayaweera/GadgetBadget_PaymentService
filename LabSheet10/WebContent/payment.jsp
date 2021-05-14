<%@page import="com.Payment" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

<title>Payment Management</title>
</head>
<body>
	
	<div class="container">
					<div class="row">
							<div class="col-8">
							
								<h1>Payment Management</h1>
								<form id="formPayment" name="formPayment" method="post" action="payment.jsp">
			 						App Code: <input name="appCode" type="text" type="text" class="form-control form-control-sm" ><br>
			 						Card Type: <input name="cardType" type="text" type="text" class="form-control form-control-sm"><br>
 			 						Name On Card: <input name="nameOnCard" type="text" type="text" class="form-control form-control-sm"><br>
 			 						Card No: <input name="cardNo" type="text" type="text" class="form-control form-control-sm"><br>
			 						Phone: <input name="phone" type="text" type="text" class="form-control form-control-sm"><br>
 			 						Expiry Date: <input name="expdate" type="text" type="text" class="form-control form-control-sm" ><br>
 								 	Amount: <input name="amount" type="text" type="text" class="form-control form-control-sm"><br>
 									<br>
 									<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 									<input type="hidden" id="hidpaymentIDSave" name="hidpaymentIDSave" value="">
								</form>
								
								<div id="alertSuccess" class="alert alert-success"></div>
								<div id="alertError" class="alert alert-danger"></div>
								
								<br> 
								<div id="divPaymentGrid">
								<%
 									Payment paymentObj = new Payment(); 
 									out.print(paymentObj.readPayment()); 
								%>
								</div>
								
								
		
</div></div></div>
</body>
</html>