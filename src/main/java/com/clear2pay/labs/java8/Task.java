package com.clear2pay.labs.java8;


public interface Task<T1, T2> {

	public T2 work(T1 request);
		
	boolean isApplicable();
}
