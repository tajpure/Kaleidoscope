package com.taj.Kaleidoscope.ast;

public class ErrorAST implements ExprAST {
	
	private String err;
	
	public ErrorAST() {
		
	}
	
	public ErrorAST(String err) {
		this.err = err;
	}
	
	public String toString() { 
		return "Error:" + err;
	}
}
