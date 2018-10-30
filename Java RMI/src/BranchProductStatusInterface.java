package com.grace.app.jrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BranchProductStatusInterface extends Remote {
	public BranchProductStatus getBranchProductStatus(int bid,int pid) throws RemoteException;

}
