package com.taj.Kaleidoscope.ast;

/**
 * 二元算术表达式抽象语法树
 * @author taojx
 *
 */
public class BinaryExprAST implements ExprAST {
	
	// 运算操作
	private int op;
	
	// 左算式
	private ExprAST lhs;
	
	// 右算式
	private ExprAST rhs;
	
	public BinaryExprAST(int op, ExprAST lhs, ExprAST rhs) {
		this.setOp(op);
		this.setLhs(lhs);
		this.setRhs(rhs);
	}

	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}

	public ExprAST getLhs() {
		return lhs;
	}

	public void setLhs(ExprAST lhs) {
		this.lhs = lhs;
	}

	public ExprAST getRhs() {
		return rhs;
	}

	public void setRhs(ExprAST rhs) {
		this.rhs = rhs;
	}
}
