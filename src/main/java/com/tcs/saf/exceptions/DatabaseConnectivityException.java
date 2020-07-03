package com.tcs.saf.exceptions;

@SuppressWarnings("serial")
public class DatabaseConnectivityException extends Exception{

	public DatabaseConnectivityException(String message)
	{
		super(message);
	}
}
