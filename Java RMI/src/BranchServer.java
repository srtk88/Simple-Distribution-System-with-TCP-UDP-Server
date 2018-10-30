package com.grace.app.jrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class BranchServer extends BranchProductStatusImpl {
	/**
 * 
 */
private static final long serialVersionUID = -7253361841229678841L;

public BranchServer(String ipaddr,int port) throws RemoteException {
	BranchProductStatusImpl pstatus = new BranchProductStatusImpl();
	try { // special exception handler for registry creation
		LocateRegistry.createRegistry(port);
		System.out.println("java RMI registry created.");
	} catch (RemoteException e) {
		// do nothing, error means registry already exists
		System.out.println("java RMI registry already exists.");
	}

	String bindLocation = "//" + ipaddr + ":" + port + "/BranchProduct";
	try {
		Naming.bind(bindLocation, pstatus);
		System.out.println("Branch Server is ready at:" + bindLocation);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		System.out.println("Branch Serverfailed: " + e);
	}

}
public static void main(String[] args) {
	// TODO Auto-generated method stub
	try {
		String hostip = args[0];
		int hostport = Integer.parseInt(args[1]);
		BranchServer bpserver1 = new BranchServer(hostip,hostport);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
