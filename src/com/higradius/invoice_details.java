package com.higradius;

   //POJO CLASS

// Importing Libraries
import java.util.Date;

public class invoice_details {
	
	private String name_customer, invoice_currency, cust_payment_terms,notes;
	
	private double total_open_amount;

	String cust_number;
	private Date predicted_payment_date, due_in_date; 
	
	
	public String getName_customer() {
		return name_customer;
	}


	public double getTotal_open_amount() {
		return total_open_amount;
	}


	public void setTotal_open_amount(double total_open_amount) {
		this.total_open_amount = total_open_amount;
	}


	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}


	public String getCust_number() {
		return cust_number;
	}


	public void setCust_number(String cust_number) {
		this.cust_number = cust_number;
	}


	public String getInvoice_currency() {
		return invoice_currency;
	}


	public void setInvoice_currency(String invoice_currency) {
		this.invoice_currency = invoice_currency;
	}


	public String getCust_payment_terms() {
		return cust_payment_terms;
	}


	public void setCust_payment_terms(String cust_payment_terms) {
		this.cust_payment_terms = cust_payment_terms;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Date getPredicted_payment_date() {
		return predicted_payment_date;
	}


	public void setPredicted_payment_date(Date predicted_payment_date) {
		this.predicted_payment_date = predicted_payment_date;
	}


	public Date getDue_in_date() {
		return due_in_date;
	}


	public void setDue_in_date(Date due_in_date) {
		this.due_in_date = due_in_date;
	}




	public String getInvoice_id() {
		return invoice_id;
	}


	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}


	private String invoice_id;
	
}