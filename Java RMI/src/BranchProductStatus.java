package com.grace.app.jrmi;

public class BranchProductStatus implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -4158111604490411980L;
	private int branchid;
	private int pid;
	private int sold;
	private int stock;
	private int demand;

	public int getbranchid() {
		return branchid;
	}

	public int getpid() {
		return pid;
	}

	public int getstock() {
		return stock;
	}

	public int getsold() {
		return sold;
	}

	public int getdemamd() {
		return demand;
	}

	public void setbranchid(int currentbranchid) {
		branchid = currentbranchid;
	}

	public void setpid(int currentpid) {
		branchid = currentpid;
	}

	public void setstock(int currentstock) {
		stock = currentstock;
	}

	public void setsold(int currentsold) {
		sold = currentsold;
	}

	public void setdemand(int currentdemand) {
		demand = currentdemand;
	}
}
