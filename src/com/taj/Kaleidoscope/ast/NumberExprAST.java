package com.taj.Kaleidoscope.ast;

/**
 * 数值抽象语法树
 * @author taojx
 *
 */
public class NumberExprAST implements ExprAST {
	// 数值
	private double val;
	
	public NumberExprAST(double val) {
		this.setVal(val);
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
	}
}
