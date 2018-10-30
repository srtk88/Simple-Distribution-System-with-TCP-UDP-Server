package com.grace.app.jrmi;

import java.rmi.Remote;

public interface LoadBalancerInterface extends Remote {
	public ProductStatus getProductStatus(int pid) throws Exception;

}
