package com.taj.Kaleidoscope.ast;

import java.util.Vector;

/**
 * 调用函数抽象语法树
 * @author taojx
 *
 */
public class CallExprAST implements ExprAST {
	
	// 调用函数名
	private String callee;
	
	// 调用函数实参
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
