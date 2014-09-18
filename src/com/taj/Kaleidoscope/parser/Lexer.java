package com.taj.Kaleidoscope.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.taj.Kaleidoscope.entity.ST;
import com.taj.Kaleidoscope.entity.Token;

/**
 * 词法分析器
 * @author taojx
 *
 */
public class Lexer {
	
	private static char[] sourceOfCharArray;
	
	//源文件里的当前位置指针
	private static int indexOfSource;
	
	private static void init() {
		indexOfSource = 0;
	}
	
	public static Token getToken() {
		String IdentifierStr = "";
		char LastChar = sourceOfCharArray[indexOfSource++];
		Token token = new Token();
		
		if (Character.isAlphabetic(LastChar)) {
			IdentifierStr += LastChar;
			while (indexOfSource < sourceOfCharArray.length && Character.isAlphabetic(LastChar = sourceOfCharArray[indexOfSource++])) {
				IdentifierStr += LastChar;
			}
			if ("public".equals(IdentifierStr)) {
				token.setNumber(ST.PUBLIC);
			} else if ("protect".equals(IdentifierStr)) {
				token.setNumber(ST.PROTECT);
			} else if ("private".equals(IdentifierStr)) {
				token.setNumber(ST.PRIVATE);
			} else if ("def".equals(IdentifierStr)) {
				token.setNumber(ST.DEF);
			} else if ("extern".equals(IdentifierStr)) {
				token.setNumber(ST.EXTERN);
			} else if ("static".equals(IdentifierStr)) {
				token.setNumber(ST.STATIC);
			} else if ("final".equals(IdentifierStr)) {
				token.setNumber(ST.FINAL);
			}  else {
				token.setNumber(ST.IDENTIFIER);
				token.setSymbol(IdentifierStr);
			}
			//由于指针前移了一位，此时回退
			if (indexOfSource <= sourceOfCharArray.length) {
				indexOfSource--;
			}
		} else if (Character.isDigit(LastChar) || '.' == LastChar) {
			String NumStr = "";
			do {
				NumStr += LastChar;
				LastChar = sourceOfCharArray[indexOfSource++];
			} while (indexOfSource < sourceOfCharArray.length && Character.isDigit(LastChar) || '.' == LastChar);
				
			token.setNumber(ST.NUMBER);
			token.setSymbol(NumStr);
			//由于指针前移了一位，此时回退
			if (indexOfSource <= sourceOfCharArray.length) {
				indexOfSource--;
			}
		} else if (LastChar == '#') {
			String comments = "";
			while (indexOfSource < sourceOfCharArray.length && LastChar != '\n' && LastChar != '\r') {
				LastChar = sourceOfCharArray[indexOfSource++];
				comments += LastChar;
			} 
			token.setNumber(ST.COMMENT);
			token.setSymbol(comments);
		} else if (LastChar == '=') {
			token.setNumber(ST.EQUAL);
		} else if (LastChar == '+') {
			token.setNumber(ST.PLUS);
		} else if (LastChar == '-') {
			token.setNumber(ST.PLUS);
		} else if (LastChar == '*') {
			token.setNumber(ST.MULTIPLY);
		} else if (LastChar == '/') {
			token.setNumber(ST.DIVIDE);
		} else if (LastChar == '(') { 
			token.setNumber(ST.LP);
		} else if (LastChar == ')') { 
			token.setNumber(ST.RP);
		} else if (LastChar == '>') { 
			token.setNumber(ST.BT);
		} else if (LastChar == '<') { 
			token.setNumber(ST.LT);
		} else if (LastChar == ';') { 
			token.setNumber(ST.SEMICOLON);
		} else if (LastChar == ',') {
			token.setNumber(ST.COMMA);
		} else if (LastChar == ' ') {
			token = getToken();
		} else {
			token.setNumber(ST.UNDEDINE);
			token.setSymbol("" + LastChar);
		}
//		if (indexOfSource == sourceOfCharArray.length) {
//			token.setNumber(SymbolTable.EOF);
//		}
		return token;
	}
	
	public static List<Token> analysis(String source) {
		 List<Token> tokenList = new ArrayList<Token>();
    	 init();
    	 sourceOfCharArray = source.trim().toCharArray();
	     while(indexOfSource < source.length()) {
	    	 tokenList.add(getToken());
	     }

    	 System.out.println(tokenList);
	     return tokenList;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    System.out.println("input:");
	    String line = sc.nextLine();
	    analysis(line);
	}
}
