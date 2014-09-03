package com.taj.Kaleidoscope.ast;

import org.jllvm.ConstantReal;
import org.jllvm.DoubleType;
import org.jllvm.Value;

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

	@Override
	public Value codegen() {
		DoubleType doubleType = new DoubleType();
		return new ConstantReal(doubleType,val);
	}
}
