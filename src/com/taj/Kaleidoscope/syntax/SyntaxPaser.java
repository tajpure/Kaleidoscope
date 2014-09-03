package com.taj.Kaleidoscope.syntax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import com.taj.Kaleidoscope.ast.BinaryExprAST;
import com.taj.Kaleidoscope.ast.CallExprAST;
import com.taj.Kaleidoscope.ast.ExprAST;
import com.taj.Kaleidoscope.ast.FunctionAST;
import com.taj.Kaleidoscope.ast.NumberExprAST;
import com.taj.Kaleidoscope.ast.PrototypeAST;
import com.taj.Kaleidoscope.ast.VariableExprAST;
import com.taj.Kaleidoscope.entity.ST;
import com.taj.Kaleidoscope.entity.Token;
import com.taj.Kaleidoscope.lexical.Lexer;

/**
 * 语法分析器
 * @author taojx
 *
 */
public class SyntaxPaser {
	
	//二元式序列的当前指针
	private  int indexOfTokenList = 0;
	//由词法分析器生成的二元式序列
	private  List<Token> tokenList;
	//算符优先级表
	private  Map<Integer, Integer> binopPrecedence = new HashMap<Integer, Integer>();
	
	public SyntaxPaser() {
		//啥都不干
	}
	
	public SyntaxPaser(List<Token> tokenList) {
		this.tokenList = tokenList;
	}
	
	/**
	 * 获取当前Token
	 * @return
	 */
	public  Token getCurToken() {
		return tokenList.get(indexOfTokenList);
	}
	
	/**
	 * 获取下一Token
	 * @return
	 */
	public  Token getNextToken() {
		indexOfTokenList++;
		if (indexOfTokenList >= tokenList.size()) {
			return null;
		}
		Token token = tokenList.get(indexOfTokenList);
		return token;
	}
	
	public  ExprAST parseNumberExpr(double numVal) {
		ExprAST result = new NumberExprAST(numVal);
		getNextToken(); //eat number
		return result;
	}
	
	public  ExprAST parseParenExpr() {
		getNextToken(); //eat (。
		ExprAST v = parseExpression();
		if (getCurToken().getSymbol() == ")") {
			error("expected ')'"); 
		}
		getNextToken();
		return v;
	}
	
	public  ExprAST parseIdentifierExpr(String idName) {
		Token curToken = getNextToken();
		if (curToken == null) {
			return null;
		}
		//ExprAST ast = new VariableExprAST(idName);
		if (curToken.getNumber() != ST.LP) {
			return new VariableExprAST(idName);
		}
		Vector<ExprAST> args = new Vector<ExprAST>();
		if (curToken.getNumber() != ST.RP) {
			getNextToken();	//eat (
			while (true) {
				ExprAST arg = parseExpression();
				if (arg == null) {
					return null;
				}
				args.add(arg);
				curToken = getCurToken();
				getNextToken();	//eat , or )
				if (curToken.getNumber() == ST.RP) {
					break;
				}
				if (curToken.getNumber() != ST.COMMA) {
					return error("Expected ')' or ',' in argument list");
				}
			}
		}
		
		return new CallExprAST(idName, args);
	}
	
	public  ExprAST parseExpression() {
		ExprAST LHS = parsePrimary();
		if (LHS == null) {
			return null;
		}
		return parseBinOpRHS(0, LHS);
	}
	
	public  ExprAST parsePrimary() {
		Token token = getCurToken();
		if (token == null) {
			return null;
		}
		switch (token.getNumber()) {
		case ST.IDENTIFIER : return parseIdentifierExpr(token.getSymbol());
		case ST.NUMBER : return parseNumberExpr(Double.parseDouble(token.getSymbol()));
		case '(' : return parseParenExpr();
		default: return error("unknown token when expecting an expression");
		}
	}
	
	public  ExprAST parseBinOpRHS(int exprPrec, ExprAST LHS) {
		while (true) {
			int tokPrec = getTokPrecedence();
			if (tokPrec < exprPrec) {
				return LHS;
			}
			int binOp = getCurToken().getNumber();
			getNextToken(); // eat binop
			
			// Parse the primary expression after the binary operator.
			ExprAST RHS = parsePrimary();
			if (RHS == null) {
				return null;
			}
			int nextPrec = getTokPrecedence();
			if (tokPrec < nextPrec) {
				// if body omitted
			}
			// Merge LHS/RHS
			LHS = new BinaryExprAST(binOp, LHS, RHS);
		}
		
	}
	
	/**
	 * 解析函数原型
	 * @return
	 */
	public  PrototypeAST parsePrototype() {
		Token curToken = getCurToken();
		if (curToken.getNumber() != ST.IDENTIFIER) {
			return errorP("Expected function name in prototype");
		}
		String fnName = curToken.getSymbol();
		curToken = getNextToken();
		if (curToken.getNumber() != ST.LP) {
			return errorP("Expected '(' in prototype");
		}
		Vector<String> argNames = new Vector<String>();
		curToken = getNextToken();
		while (curToken.getNumber() == ST.IDENTIFIER) {
			argNames.add(curToken.getSymbol());
			curToken = getNextToken();
		}
		if (curToken.getNumber() != ST.RP) {
			return errorP("Expected ')' in prototype");
		}
		getNextToken();	// eat )
		return new PrototypeAST(fnName, argNames);
	}
	
	/**
	 * 解析函数定义
	 * @return
	 */
	public  FunctionAST parseDefinition() {
		getNextToken();
		PrototypeAST proto = parsePrototype();
		if (proto == null){
			return null;
		}
		ExprAST ast = parseExpression();
		if (ast != null) {
			return new FunctionAST(proto, ast);
		} else {
			return null;
		}
	}
	
	public  PrototypeAST parseExtern() {
		getNextToken();
		return parsePrototype();
	}
	
	public  FunctionAST parseTopLevelExpr() {
		ExprAST e = parseExpression();
		if (e != null) {
			PrototypeAST proto = new PrototypeAST("", new Vector<String>());
			return new FunctionAST(proto, e);
		}
		return null;
	}
	
	public  ExprAST error(String err) {
		System.out.println(err);
		return null;
	}
	
	public  PrototypeAST errorP(String err) {
		System.out.println(err);
		return null;
	}
	
	public  FunctionAST errorF(String err) {
		System.out.println(err);
		return null;
	}
	
	public  void initBinopPrecedence() {
		binopPrecedence.put(ST.LT, 10);
		binopPrecedence.put(ST.PLUS, 20);
		binopPrecedence.put(ST.SUBTRACT, 20);
		binopPrecedence.put(ST.MULTIPLY, 40);
	}
	
	//获取当前二元式优先级
	public  int getTokPrecedence() {
		 //System.out.println(getCurToken().getNumber());
		 Integer tokPrec = binopPrecedence.get(getCurToken().getNumber());
		 if (tokPrec == null) {
			 return -1;
		 }
		  return tokPrec;
	}
	
	public  void mainLoop() {
		while (indexOfTokenList < tokenList.size()) {
			Token curToken = getCurToken();
			switch (curToken.getNumber()) {
			case ST.EOF : return;
			case ST.SEMICOLON : getNextToken(); break;
			case ST.DEF : handleDefinition(); break;
			case ST.EXTERN : handleExtern(); break;
			default : handleTopLevelExpression(); break;
			}
		}
	}
	
	public  void handleDefinition() {
		if (parseDefinition() != null) {
			System.out.println("Parsed a function definition.");
			//getNextToken();
		} else {
			getNextToken();
		}
	}
	
	public  void handleExtern() {
		if (parseExtern() != null) {
			System.out.println("Parsed an extern");
		} else {
			getNextToken();
		}
	}

	public  void handleTopLevelExpression() {
		if (parseTopLevelExpr() != null) {
			System.out.println("Parsed a top-level expr");
		} else {
			getNextToken();
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    System.out.println("input:");
	    String line = sc.nextLine();
	    
		SyntaxPaser paser = new SyntaxPaser(Lexer.analysis(line));
		paser.initBinopPrecedence();
	    paser.mainLoop();
	}
	
}
