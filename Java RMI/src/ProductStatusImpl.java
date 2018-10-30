package com.grace.app.jrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductStatusImpl extends UnicastRemoteObject implements ProductStatusInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ProductStatusImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ProductStatus getProductStatus(int pid) throws RemoteException {
		String DB_URL = "jdbc:mysql://localhost:3306/banana";

		// Database credentials
		String USER = "bananadbadm";
		String PASS = "Banana@2018";

		Connection conn = null;
		PreparedStatement stmt = null;
		ProductStatus pstatus = new ProductStatus();
		pstatus.setpid(pid);
		pstatus.setdemand(0);
		pstatus.setstock(0);
		// Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			// Execute a query
			System.out.println("Creating statement...");

			stmt = conn.prepareStatement("SELECT * FROM productstatus where pid=?");
		
			stmt.setInt(1,pid);
			
			ResultSet rs = stmt.executeQuery();

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int ppid = rs.getInt("pid");

				int pstock = rs.getInt("stock");
				int pdemand = rs.getInt("demand");

				// Setting the values
				pstatus.setpid(ppid);
				pstatus.setdemand(pdemand);
				pstatus.setstock(pstock);
				break;
			}
			System.out.println("Close DB Connection");
			rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pstatus;
	}

}
