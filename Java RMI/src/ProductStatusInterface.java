package com.grace.app.jrmi;

import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface ProductStatusInterface extends Remote {
	public ProductStatus getProductStatus(int pid) throws RemoteException;
}
