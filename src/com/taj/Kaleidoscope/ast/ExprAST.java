package com.taj.Kaleidoscope.ast;

import org.jllvm.Value;

/**
 * 表达式的抽象语法树
 * @author taojx
 *
 */
public interface ExprAST {
	
	/**
	 * 代码生成函数
	 * @return
	 */
	public Value codegen();
}
