package com.taj.Kaleidoscope.ast;

import org.jllvm.Value;

/**
 * 函数抽象语法树
 * @author taojx
 *
 */
public class FunctionAST {
	
	// 函数原型
	private PrototypeAST protp;
	// 函数体
	private ExprAST body;
	
	public FunctionAST(PrototypeAST proto, ExprAST body) {
		this.setProtp(proto);
		this.setBody(body);
	}

	public PrototypeAST getProtp() {
		return protp;
	}

	public void setProtp(PrototypeAST protp) {
		this.protp = protp;
	}

	public ExprAST getBody() {
		return body;
	}

	public void setBody(ExprAST body) {
		this.body = body;
	}
	
	public Value codegen() {
		
		return null;
	}
}
