package com.taj.Kaleidoscope;

import java.util.Scanner;

/**
 * ´Ê·¨·ÖÎöÆ÷
 * @author taojx
 *
 */
public class Lexer {
	
	private static String IdentifierStr;
	
	private static double NumVal = 0;
	
	public static int gettok(char[] input) {
		int indexOfInput = 0;
		IdentifierStr = "";
		char LastChar = input[indexOfInput++];
		
		if (Character.isAlphabetic(LastChar)) {
			IdentifierStr += LastChar;
			while (indexOfInput < input.length && Character.isAlphabetic(LastChar = input[indexOfInput++])) {
				IdentifierStr += LastChar;
				if ("def".equals(IdentifierStr)) {
					return Token.TOK_DEF;
				} else if ("extern".equals(IdentifierStr)) {
					return Token.TOK_EXTERN;
				}
			}
		} else if (Character.isDigit(LastChar) || ".".equals(LastChar)) {
			String NumStr = "";
			do {
				NumStr += LastChar;
				LastChar = input[indexOfInput++];
				} while (Character.isDigit(LastChar) || ".".equals(LastChar));
				
				NumVal = Double.parseDouble(NumStr);
				return Token.TOK_NUMBER;
		} else if (LastChar == '#') {
			do {
				LastChar = input[indexOfInput++];
			} while (indexOfInput != input.length && LastChar != '\n' && LastChar != '\r');
			
			if (indexOfInput != input.length) {
				return gettok(input);
			}
		}
		if (indexOfInput == input.length) {
			return Token.TOK_EOF;
		}
		int ThisChar = LastChar;
		LastChar = input[indexOfInput];
		return ThisChar;
	}
	
	public static void main(String[] args) {
		 @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	     System.out.println("Input:");
	     while(sc.hasNextLine()) {
	    	 String line = sc.nextLine();
	    	 if (line == null || line.length() == 0) {
	    		 continue;
	    	 }
	    	 line = line.trim();
	    	 System.out.println(gettok(line.toCharArray()));
	     }
	}
}
