package com.taj.Kaleidoscope.ast;

import java.util.Vector;

public class CallExprAST implements ExprAST {
	private String Callee;
	private Vector<ExprAST> args;
	
	public CallExprAST(String callee, Vector<ExprAST> args) {
		
	}
}
