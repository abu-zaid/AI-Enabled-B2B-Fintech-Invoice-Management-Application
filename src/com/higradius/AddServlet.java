package com.higradius;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static Connection conn = null;
	public static PreparedStatement pst = null;
	static ArrayList<invoice_details> data ;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/invoice";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "abuzaid";
	
	
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();

		System.out.print("Insertion Successfull !!");
		invoice_details invoice=  new invoice_details();
		
		PrintWriter out = response.getWriter();
		
		try {
			String custName = request.getParameter("cust_name");
			String custNum = request.getParameter("cust_number");
			String invoice_id = request.getParameter("invoice_id");
			Double inv_amt = Double.parseDouble(request.getParameter("invoice_amount"));
			//String due_date=request.getParameter("due_date");
			String notes=request.getParameter("notes");

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	
//			invoice.setDate
			invoice.setCust_number(custNum);
			invoice.setName_customer(custName);
			invoice.setInvoice_id(invoice_id);
			invoice.setTotal_open_amount(inv_amt);
		
			System.out.println(custNum);
			System.out.println(invoice.getCust_number());
			//invoice.setInvoice_id(invoice_id);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO invoice_details (cust_number,name_customer,invoice_id,total_open_amount,notes) VALUES (?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, invoice.getCust_number());
			pst.setString(2, invoice.getName_customer());
			pst.setString(3, invoice.getInvoice_id());
			pst.setDouble(4, invoice.getTotal_open_amount());
		    //pst.setDate(5, (Date) invoice.getDue_in_date());
			pst.setString(5,invoice.getNotes());
			pst.addBatch();
			pst.executeBatch();
			conn.commit();
			conn.close();
			System.out.print("Insertion Successfull !!");
				
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(pst!=null)
					pst.close();
			}catch(SQLException se2){
			// nothing we can do
			}
			try{
				if(conn!=null)
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
				
	}
}