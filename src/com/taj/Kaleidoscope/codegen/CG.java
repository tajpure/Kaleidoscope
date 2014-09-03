package com.taj.Kaleidoscope.codegen;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jllvm.Context;
import org.jllvm.InstructionBuilder;
import org.jllvm.Module;
import org.jllvm.Value;

import com.taj.Kaleidoscope.lexical.Lexer;
import com.taj.Kaleidoscope.syntax.SyntaxPaser;

/**
 * 使用JLLVM进行代码生成的代码产生器
 * @author taojx
 *
 */
public class CG {
	
	public static Module theModule;
	
	public static InstructionBuilder builder;
	
	public static Map<String, Value> nameValues = new HashMap<String, Value>();;
	
	public static Value errorV(String err) {
		System.out.println(err);
		return null;
	}
	
	public static void main (String[] args) {
		Context ctx = new Context();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    System.out.println("input:");
	    String line = sc.nextLine();
	    
	    Module moudle = new Module("my cool jit", ctx);
	    
		SyntaxPaser paser = new SyntaxPaser(Lexer.analysis(line));
		paser.initBinopPrecedence();
	    paser.mainLoop();
	    
	    moudle.dump();
	}
}
