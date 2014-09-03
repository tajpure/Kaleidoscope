package com.taj.Kaleidoscope.ast;

import org.jllvm.Value;

import com.taj.Kaleidoscope.codegen.CG;

/**
 * 变量抽象语法树
 * @author taojx
 *
 */
public class VariableExprAST implements ExprAST {
	
	private String name;
	
	public VariableExprAST(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Value codegen() {
		Value value = CG.nameValues.get(name);
		if (value == null) {
			return CG.errorV("Unknown variable name");
		}
		return value;
	}
}
