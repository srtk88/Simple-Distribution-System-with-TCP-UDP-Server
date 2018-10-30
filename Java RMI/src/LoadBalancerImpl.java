package com.grace.app.jrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadBalancerImpl extends UnicastRemoteObject implements LoadBalancerInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9105880455104566790L;

	protected LoadBalancerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ProductStatus getProductStatus(int pid) throws Exception {
		String DB_URL = "jdbc:mysql://localhost:3306/banana";

		// Database credentials
		String USER = "bananadbadm";
		String PASS = "Banana@2018";

		Connection conn = null;
		PreparedStatement stmt = null;
		int ptypeid = 1;
		// Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Load Balancer  Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Load Balancer Connected database successfully...");

			// Execute a query
			System.out.println("Load Balancer  Creating statement...");

			stmt = conn.prepareStatement("SELECT * FROM product where pid=?");
		
			stmt.setInt(1,pid);
			
			ResultSet rs = stmt.executeQuery();
			

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				ptypeid = rs.getInt("ptypeid");

				break;
			}
			System.out.println("Load Balancer Close DB Connection");
			rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ptypeid = "+ptypeid);
		String remoteHostName = "127.0.0.1";
		int remotePort = 1091;
		if(ptypeid==1) {
		  remotePort = 1091;
		}
		if(ptypeid==2) {
			remotePort = 1092;
		}
		if(ptypeid==3) {
			remotePort = 1093;
		}		
		String connectLocation = "//" + remoteHostName + ":" + remotePort + "/Product";

		ProductStatusInterface p = null;
		try {
			System.out.println("Connecting to client at : " + connectLocation);
			p = (ProductStatusInterface) Naming.lookup(connectLocation);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ProductStatus presult = null;
		try {
			presult = p.getProductStatus(pid);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return presult;
	}

}
