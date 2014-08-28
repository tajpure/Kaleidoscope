package com.taj.Kaleidoscope.ast;

/**
 * 变量抽象语法树
 * @author taojx
 *
 */
public class VariableExprAST implements ExprAST {
	public String Name;
	
	public VariableExprAST(String name) {
		this.Name = name;
	}
}
