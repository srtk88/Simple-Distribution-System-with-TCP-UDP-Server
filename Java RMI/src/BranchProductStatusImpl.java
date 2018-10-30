package com.grace.app.jrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchProductStatusImpl extends UnicastRemoteObject implements BranchProductStatusInterface {

	protected BranchProductStatusImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public BranchProductStatus getBranchProductStatus(int bid, int pid) throws RemoteException {
		String DB_URL = "jdbc:mysql://localhost:3306/banana";

		// Database credentials
		String USER = "bananadbadm";
		String PASS = "Banana@2018";

		Connection conn = null;
		PreparedStatement stmt = null;
		BranchProductStatus bpstatus = new BranchProductStatus();
		bpstatus.setbranchid(bid);
		bpstatus.setpid(bid);
		bpstatus.setsold(0);
		bpstatus.setdemand(0);
		bpstatus.setstock(0);
		// Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("BranchProduct Server Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("BranchProduct Server Connected database successfully...");

			// Execute a query
			System.out.println("BranchProduct Server Creating statement...");

			stmt = conn.prepareStatement("SELECT * FROM branchproductstatus where branchid=? and pid=?");
		
			stmt.setInt(1,bid);	
			stmt.setInt(2,pid);
			
			ResultSet rs = stmt.executeQuery();

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int branchid = rs.getInt("branchid");
				int bppid = rs.getInt("pid");
				int bpsold = rs.getInt("sold");
				int bpstock = rs.getInt("stock");
				int bpdemand = rs.getInt("demand");

				// Setting the values
				
				bpstatus.setbranchid(branchid);
				bpstatus.setpid(bppid);
				bpstatus.setsold(bpsold);
				bpstatus.setdemand(bpdemand);
				bpstatus.setstock(bpstock);
				break;
			}
			System.out.println("BranchProduct Server Close DB Connection");
			rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bpstatus;
	}

}
