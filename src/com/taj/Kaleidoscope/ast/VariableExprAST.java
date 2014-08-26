package com.taj.Kaleidoscope.ast;

public class VariableExprAST implements ExprAST {
	public String Name;
	
	public VariableExprAST(String name) {
		this.Name = name;
	}
}
