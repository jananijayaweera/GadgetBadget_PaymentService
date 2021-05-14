$(document).ready(function()
{
	if ($("#alertSuccess").text().trim()==" ")
		{
			$("#alertSuccess").hide();
		}
		$("#alertError").hide();

});
//SAVE================================================
$(document).on("click", "#btnSave", function(event)
{
	//Clear alerts-------------------------------------
	$("#alertSuccess").text(" ");
	$("#alertSuccess").hide();
	$("#alertError").text(" ");
	$("#alertError").hide();
	
	//Form validation-----------------------------------
	var status = validatepaymentForm();
	if (status != true)
		{
			$("#alertError").text(status);
			$("#alertError").show();
			return;
		}
	
	//If valid---------------------------------------------
	var type = ($("#hidpaymentIDSave").val() == "") ? "POST" : "PUT"; 
	
 	$.ajax( 
 	{ 
 		url : "PaymentsAPI", 
 		type : type, 
 		data : $("#formPayment").serialize(), 
 		dataType : "text", 
 		complete : function(response, status) 
 	{ 
 		onPaymentSaveComplete(response.responseText, status); 
 	} 
 	}); 
});


//UPDATE====================================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidpaymentIDSave").val($(this).data("paymentid"));
	$("#appCode").val($(this).closest("tr").find('td:eq(0)').text());
	$("#cardType").val($(this).closest("tr").find('td:eq(1)').text());
	$("#nameOnCard").val($(this).closest("tr").find('td:eq(2)').text());
	$("#cardNo").val($(this).closest("tr").find('td:eq(3)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(4)').text());
	$("#expdate").val($(this).closest("tr").find('td:eq(5)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(6)').text());
	
});

//DELETE====================================================
$(document).on("click", ".btnRemove", function(event)
{ 
 	$.ajax( 
 	{ 
 		url : "PaymentsAPI", 
 		type : "DELETE", 
 		data : "paymentID=" + $(this).data("paymentid"),
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onPaymentDeleteComplete(response.responseText, status); 
 		} 
 	}); 
});

function onPaymentDeleteComplete(response, status)
{ 
		if (status == "success") 
		{ 
		 		var resultSet = JSON.parse(response); 
		 	
		 		if (resultSet.status.trim() == "success") 
		 		{ 
						 $("#alertSuccess").text("Successfully deleted."); 
		 				 $("#alertSuccess").show(); 
		 				 
		 				 $("#divPaymentGrid").html(resultSet.data); 
		 		} else if (resultSet.status.trim() == "error") 
		 		{ 
		 				$("#alertError").text(resultSet.data); 
						 $("#alertError").show(); 
		 		} 
		 } else if (status == "error") 
		 { 
		 		$("#alertError").text("Error while deleting."); 
		 		$("#alertError").show(); 
		 } else
		 { 
		 		$("#alertError").text("Unknown error while deleting.."); 
		 		$("#alertError").show(); 
		 }
 }

function onPaymentSaveComplete(response, status)
{ 
		if (status == "success") 
		{ 
			 	var resultSet = JSON.parse(response); 
			 	
			 	if (resultSet.status.trim() == "success") 
			 	{ 
			 		$("#alertSuccess").text("Successfully Saved."); 
			 		$("#alertSuccess").show(); 
			 		
			 		$("#divPaymentGrid").html(resultSet.data); 
				} else if (resultSet.status.trim() == "error") 
			 	{ 
			 		$("#alertError").text(resultSet.data); 
			 		$("#alertError").show(); 
			 	} 
		 } else if (status == "error") 
		 { 
		 		$("#alertError").text("Error while saving."); 
		 		$("#alertError").show(); 
		 } else
		 { 
		 		$("#alertError").text("Unknown error while saving.."); 
		 		$("#alertError").show(); 
		 } 
		 		$("#hidpaymentIDSave").val(""); 
		 		$("#formPayment")[0].reset(); 
}

//CLIENT-MODEL==================================================
function validatepaymentForm()
{
	//CODE
	if ($("#appCode").val().trim()== " ")
		{
			return "Insert the app code.";
		}
	
	//CARD TYPE
	if ($("#cardType").val().trim()== " ")
		{
			return "Insert the card type.";
		}
	
	//NAME ON CARD
	if ($("#nameOnCard").val().trim()== " ")
		{
			return "Insert the name on card.";
		}
	
	//CARD NO
	if ($("#cardNo").val().trim()== " ")
		{
			return "Insert the card number.";
		}
	
	//PHONE
	if ($("#phone").val().trim()== " ")
		{
			return "Insert the phone number.";
		}
	
	//EXPIRY DATE
	if ($("#expdate").val().trim()== " ")
		{
			return "Insert the expiry date of the card.";
		}
	
	//AMOUNT
	if ($("#amount").val().trim()== " ")
		{
			return "Insert the amount.";
		}
	
	//is numeric value
	var tmpAmount = $("#amount").val().trim();
	if (!$.isNumeric(tmpAmount))
		{
			return "Insert the amount.";
		}
	
	return true;
}




