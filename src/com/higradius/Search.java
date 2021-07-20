package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Fetch
 */
public class Search extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/invoice";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String USER = "root";
	    String PASS = "abuzaid";

		PrintWriter out = response.getWriter();

	     Connection conn = null;
	     try {
	    	 
				String searchValue = request.getParameter("searchValue");
				System.out.println(searchValue);
			
	    	 Class.forName(JDBC_DRIVER);
	    	 conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	 String sql = "SELECT name_customer, cust_number, invoice_id, total_open_amount,predicted_payment_date, due_in_date, notes FROM  invoice_details WHERE invoice_id LIKE '" + searchValue + "%'";
	    	System.out.println(sql);
	    	 PreparedStatement ps = conn.prepareStatement(sql);
	  
	    	 
	    	 ArrayList<invoice_details> list = new ArrayList<>();

	    	 
	    	 ResultSet rs = ps.executeQuery();  
	            while(rs.next()){  
	                invoice_details object = new invoice_details();
	                object.setName_customer(rs.getString("name_customer"));  
	                object.setCust_number(rs.getString("cust_number"));  
	                object.setInvoice_id(rs.getString("invoice_id"));  
	                object.setPredicted_payment_date(rs.getDate("predicted_payment_date"));
	                object.setTotal_open_amount(rs.getDouble("total_open_amount"));  
	                object.setDue_in_date(rs.getDate("due_in_date"));
	                object.setNotes(rs.getString("notes"));
	                list.add(object);  
	                
	               
	            }
	          
				
	            rs.close();	      
	            conn.close();  
	            Gson gson = new GsonBuilder().setPrettyPrinting().create();
				response.setContentType("application/json");
				String dataj = gson.toJson(list);
				
				out.print(dataj);
	     }
	     catch(Exception e) {
		    	 e.printStackTrace();
	     }
	     
	     	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}