package com.grace.app.jrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ProductServer extends ProductStatusImpl {
		/**
	 * 
	 */
	private static final long serialVersionUID = -7253361841229678841L;
	
	public ProductServer(String ipaddr,int port) throws RemoteException {
		ProductStatusImpl pstatus = new ProductStatusImpl();
		try { // special exception handler for registry creation
			LocateRegistry.createRegistry(port);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			// do nothing, error means registry already exists
			System.out.println("java RMI registry already exists.");
		}

		String bindLocation = "//" + ipaddr + ":" + port + "/Product";
		try {
			Naming.bind(bindLocation, pstatus);
			System.out.println("Product Server is ready at:" + bindLocation);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Product Serverfailed: " + e);
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String hostip = args[0];
			int hostport = Integer.parseInt(args[1]);
			ProductServer pserver1 = new ProductServer(hostip,hostport);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
