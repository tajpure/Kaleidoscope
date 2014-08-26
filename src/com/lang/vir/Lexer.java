package com.lang.vir;

/**
 * 
 * @author taojx
 *
 */
public class Lexer {
	
	private static String IdentifierStr = "";
	
	private static double NumVal = 0;
	
	public static int gettok(char[] input) {
		int indexOfInput = 0;
		char LastChar = input[indexOfInput];
		while (Character.isSpaceChar(LastChar)) {
			LastChar = input[indexOfInput++];
		}
			if (Character.isAlphabetic(LastChar)) {
				IdentifierStr += LastChar;
				while (Character.isDigit(LastChar = input[indexOfInput++])) {
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
}
