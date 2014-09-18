package com.taj.Kaleidoscope.ast;

import org.llvm.Value;

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

	@Override
	public Value codegen() {
		// TODO Auto-generated method stub
		return null;
	}
}
