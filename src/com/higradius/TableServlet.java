package com.higradius;
    import java.io.IOException; 
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import com.google.gson.Gson; 
	import com.google.gson.GsonBuilder;

	public class TableServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;

		public static Connection conn;
		public static PreparedStatement statement;
		static ArrayList<invoice_details> data ;
		
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost:3306/invoice";
		// Database credentials
		static final String USER = "root";
		static final String PASS = "abuzaid";
		

			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//String i = req.getParameter("num1");
				PrintWriter out = response.getWriter();
				
				List<invoice_details> data = new ArrayList<>();
				try {
					String selectQuery = "select name_customer,cust_number,invoice_id,total_open_amount,due_in_date,predicted_payment_date,notes from invoice_details ORDER BY column_0 DESC  ";
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					//creating a statement object
					statement = conn.prepareStatement(selectQuery);
					
					ResultSet rs = statement.executeQuery();
					while(rs.next()) {
						invoice_details invoice = new invoice_details();
						
						invoice.setName_customer(rs.getString(1));
						invoice.setCust_number(rs.getString(2));
						invoice.setInvoice_id(rs.getString(3));
						invoice.setTotal_open_amount(rs.getDouble(4));
						invoice.setDue_in_date(rs.getDate(5));
				     	invoice.setPredicted_payment_date(rs.getTimestamp(6));
						invoice.setNotes(rs.getString(7));
						
						data.add(invoice);
						
					}
					
					//Clean up environment
					rs.close();
					statement.close();
					conn.close();
					
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					response.setContentType("application/json");
					String dataj = gson.toJson(data);
					
					out.print(dataj);
					
					
				} catch(SQLException se){
					//Handle errors for JDBC
					se.printStackTrace();
				}catch(Exception e){
					//Handle errors for Class.forName
					e.printStackTrace();
				}finally{
					//finally block used to close resources
					try{
						if(statement!=null)
							statement.close();
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