package com.taj.Kaleidoscope.ast;

import java.util.Vector;

import org.jllvm.Value;

/**
 * 函数原型抽象语法树
 * @author taojx
 *
 */
public class PrototypeAST {
	
	private String name;
	
	private Vector<String> args;
	
	public PrototypeAST(String name, Vector<String> args) {
		this.setName(name);
		this.setArgs(args);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<String> getArgs() {
		return args;
	}

	public void setArgs(Vector<String> args) {
		this.args = args;
	}
	
	public Value codegen() {
		// TODO Auto-generated method stub
		return null;
	}
}
