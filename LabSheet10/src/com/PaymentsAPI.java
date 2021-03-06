package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PaymentsAPI")
public class PaymentsAPI extends HttpServlet {
	
	Payment paymentObj = new Payment(); 
	
	private static final long serialVersionUID = 1L;
	
    
	
    //public PaymentsAPI() {
        //super();
        
    //}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = paymentObj.insertPayment(request.getParameter("appCode"), 
				request.getParameter("cardType"), 
				request.getParameter("nameOnCard"), 
				request.getParameter("cardNo"),
				request.getParameter("phone"),
				request.getParameter("expdate"),
				request.getParameter("amount"));
		
				response.getWriter().write(output); 
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		 String output = paymentObj.updatePayment(paras.get("hidpaymentIDSave").toString(), 
		 paras.get("appCode").toString(), 
		 paras.get("cardType").toString(), 
		 paras.get("nameOnCard").toString(), 
		 paras.get("cardNo").toString(),
		 paras.get("phone").toString(),
		 paras.get("expdate").toString(),
		 paras.get("amount").toString()); 
		 
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		 
		String output = paymentObj.deletePayment(paras.get("paymentID").toString()); 
		
		response.getWriter().write(output); 
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
				String queryString = scanner.hasNext() ? 
							scanner.useDelimiter("\\A").next() : ""; 
				scanner.close(); 
							
				String[] params = queryString.split("&"); 
				for (String param : params) 
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]); 
				} 
		} 
		catch (Exception e) 
		{ 
		} 
	return map; 
	}


}
