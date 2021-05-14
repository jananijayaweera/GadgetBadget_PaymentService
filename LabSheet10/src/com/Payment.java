package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	public Connection connect()
	{ 
		
		Connection con = null; 
	 
	 try 
	 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/it18069464", "root", ""); 
		 //For testing
		 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
		 e.printStackTrace(); 
	 } 
	 
	 return con; 
	
	}
	
	//Insert new payment details
	public String insertPayment(String appCode, String cardType, String name, String cardNo, String phone, String expdate, String amount) 
	{
		String output ="";
		
		try {
			Connection con = connect();
			if (con == null) 
			{ 
					return "Error while connecting to the database"; 
			}
		
			// create a prepared statement
			String query = " INSERT INTO `payment`(`paymentID`, `appCode`, `cardType`, `nameOnCard`, `cardNo`, `phone`, `expdate`, `amount`)" + " values (?,?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, appCode);
			preparedStmt.setString(3, cardType);
			preparedStmt.setString(4, name);
			preparedStmt.setInt(5, Integer.parseInt(cardNo));
			preparedStmt.setString(6, phone);
			preparedStmt.setString(7, expdate);
			preparedStmt.setDouble(8, Double.parseDouble(amount));
		
			// execute the statement
			preparedStmt.execute();
			con.close();
		
			output = "Inserted successfully";
			}
			catch (Exception e) {
				output = "Error while inserting the card details.";
				System.err.println(e.getMessage());
			}
			return output;

	}
	
	//Retrieve payment details 
	public String readPayment() 
	{
		String output ="";
				
		try
		{ 
			Connection con = connect(); 
		if (con == null) 
			{ 
			return "Error while connecting to the database for reading."; 
			} 
				
			// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Payment ID</th> <th>App Code</th> <th>CardType</th> <th>Name</th> <th>CardNo</th> <th>Phone</th ><th>Exp_date</th> <th>Amount</th> </tr>";
				String query = "SELECT * FROM `payment`";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
			// iterate through the rows in the result set
				while (rs.next()) {
					String paymentID = Integer.toString(rs.getInt("paymentID"));
					String appCode = rs.getString("appCode");
					String cardType = rs.getString("cardType");
					String nameOnCard = rs.getString("nameOnCard");
					String cardNo = Integer.toString(rs.getInt("cardNo"));
					String phone = rs.getString("phone");
					String expdate = rs.getString("expdate");
					String amount = Double.toString(rs.getDouble("amount"));
					
			// Add into the html table
					output = "<tr><td><input id='hidpaymentIDUpdate' name='hidpaymentIDUpdate' type='hidden' value=' " + paymentID + " '>" 
								+ appCode + "</td>";
					output += "<td>" + cardType + "</td>";
					output += "<td>" + nameOnCard + "</td>";
					output += "<td>" + cardNo + "</td>";
					output += "<td>" + phone + "</td>";
					output += "<td>" + expdate + "</td>";
					output += "<td>" + amount + "</td>";
					
					//buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn=secondary'></td>"
							+ "<td><form method='post' action='PaymentService.jsp'>"
							+ "<input name='btnRemove' "
							+ "type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='hidpaymentIDDelete' type='hidden' value=' " + paymentID + " '>" + "</form></td></tr>";
					
				}
					con.close();
			// Complete the html table
			output += "</table>";
			} 
			catch (Exception e) {
				output = "Error while reading the card details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	
	//Update the status of payments
	public String updatePayment(String paymentID, String appCode, String cardType, String name, String cardNo,String phone,String expdate,String amount)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE `payment` SET `paymentID`=?,`appCode`=?,`cardType`=?,`nameOnCard`=?,`cardNo`=?,`phone`=?,`expdate`=?,`amount`=? WHERE paymentID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, appCode);
			preparedStmt.setString(2, cardType);
			preparedStmt.setString(3, name);
			preparedStmt.setInt(4, Integer.parseInt(cardNo));
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, expdate);
			preparedStmt.setDouble(7, Double.parseDouble(amount));
			preparedStmt.setInt(8, Integer.parseInt(paymentID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully ";
			
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
			
			
	//Delete if a payment is not valid
	public String deletePayment(String paymentID)
	{
				String output = "";
				
				try 
				{
					Connection con = connect();
					if(con == null) {
						return"Error while connecting to the databae for deleting.";
					}
					
					//Create a prepared statement
					String query = "delete from items where paymentID=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					//binding the statement
					preparedStmt.setInt(1, Integer.parseInt(paymentID));
					
					//execute the statement
					preparedStmt.execute();
					con.close();
					
					output = "Delete Successfully";	
				}
				catch (Exception e)
				{
					output = "Error while deleting the item.";
					System.err.println(e.getMessage());
				}
			
				return output;
			}
			
	}
