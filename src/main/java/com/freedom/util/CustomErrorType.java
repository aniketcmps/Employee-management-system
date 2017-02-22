package com.freedom.util;
/**
 * Custom error class 
 */

public class CustomErrorType {
	private String error;
	 
    public CustomErrorType(String error){
        this.error = error;
    }
 
    public String getErrorMessage() {
        return error;
    }
}
