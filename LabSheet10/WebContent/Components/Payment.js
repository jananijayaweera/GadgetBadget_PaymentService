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
	var status = validateItemForm();
	if (status != true)
		{
			$("#alertError").text(status);
			$("#alertError").show();
			return;
		}
	
	//If valid---------------------------------------------
	$("#formPayment").submit();
});

//UPDATE====================================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidpaymentIDSave").val($(this).closest("tr").find('#hidpaymentIDUpdate').val());
	$("#app_Code").val($(this).closest("tr").find('td:eq(0)').text());
	$("#cardType").val($(this).closest("tr").find('td:eq(1)').text());
	$("#nameOnCard").val($(this).closest("tr").find('td:eq(2)').text());
	$("#cardno").val($(this).closest("tr").find('td:eq(3)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(4)').text());
	$("#expdate").val($(this).closest("tr").find('td:eq(5)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(6)').text());
	
});

//CLIENT-MODEL==================================================
function validatepaymentForm()
{
	//CODE
	if ($("#app_Code").val().trim()== " ")
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
	if ($("#cardno").val().trim()== " ")
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
			return "Insert the app code.";
		}
	
	//is numeric value
	var tmpAmount = $("#amount").val().trim();
	if (!$.isNumeric(tmpAmount))
		{
			return "Insert the amount.";
		}
	
	return true;
}





