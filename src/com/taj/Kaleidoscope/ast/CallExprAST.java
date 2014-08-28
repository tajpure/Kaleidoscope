package com.taj.Kaleidoscope.ast;

import java.util.Vector;

public class CallExprAST implements ExprAST {
	
	private String callee;
	
	private Vector<ExprAST> args;
	
	public CallExprAST(String callee, Vector<ExprAST> args) {
		this.setCallee(callee);
		this.setArgs(args);
	}

	public String getCallee() {
		return callee;
	}

	public void setCallee(String callee) {
		this.callee = callee;
	}

	public Vector<ExprAST> getArgs() {
		return args;
	}

	public void setArgs(Vector<ExprAST> args) {
		this.args = args;
	}
}
