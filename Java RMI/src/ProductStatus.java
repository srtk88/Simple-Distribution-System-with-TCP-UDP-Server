package com.grace.app.jrmi;

public class ProductStatus implements java.io.Serializable  {
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = -4148555491482517435L;
	
	private int pid;
	 private int stock;
	 private int demand;
	 
  public int getpid() {
	  	return pid;
      }
  public int getstock() {
	  	return stock;
     }
  public int getdemamd() {
	  	return demand;
     }  
  public void setpid(int currentpid) {
	  	stock = currentpid;
     }  
  public void setstock(int currentstock) {
	  	stock = currentstock;
      }
  public void setdemand(int currentdemand) {
	  	demand = currentdemand;
     } 
}
