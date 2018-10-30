package com.grace.app.jrmi;

import java.net.MalformedURLException;
import java.rmi.*;

public class BranchClient {
	public static void main(String[] args) {
		String conntype = args[0];
		if(conntype.equals("p")) {
			String hostip = args[1];
			int hostport = Integer.parseInt(args[2]);
			int productid = Integer.parseInt(args[3]);
			connectproductserver(hostip,hostport,productid);
		}
		if(conntype.equals("b")) {
			String hostip = args[1];
			int hostport = Integer.parseInt(args[2]);
			int branchid = Integer.parseInt(args[3]);
			int productid = Integer.parseInt(args[4]);
			connectbranchserver(hostip,hostport,branchid,productid);
		}	
		
		
	}
	
	public static void connectbranchserver(String hostip,int hostport,int bid,int pid) {
		String remoteHostName = hostip;
		int remotePort = hostport;
		String connectLocation = "//" + remoteHostName + ":" + remotePort + "/BranchProduct";

		BranchProductStatusInterface bp = null;
		try {
			System.out.println("Connecting to client at : " + connectLocation);
			bp = (BranchProductStatusInterface) Naming.lookup(connectLocation);
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

		BranchProductStatus bpresult = null;
		try {
			bpresult = bp.getBranchProductStatus(bid,pid);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Branch Sold is :" + bpresult.getsold());
		System.out.println("Branch Stock is :" + bpresult.getstock());
		System.out.println("Branch Demand is :" + bpresult.getdemamd());
	}
	
	public static void connectproductserver(String hostip,int hostport,int pid) {
		String remoteHostName = hostip;
		int remotePort = hostport;
		String connectLocation = "//" + remoteHostName + ":" + remotePort + "/Product";

		LoadBalancerInterface p = null;
		try {
			System.out.println("Connecting to client at : " + connectLocation);
			p = (LoadBalancerInterface) Naming.lookup(connectLocation);
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
		System.out.println("Stock is :" + presult.getstock());
		System.out.println("Demand is :" + presult.getdemamd());
	}

}
