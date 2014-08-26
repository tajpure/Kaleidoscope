package com.taj.Kaleidoscope;

import java.util.HashMap;
import java.util.Map;

/**
 * Óï·¨·ÖÎöÆ÷
 * @author taojx
 *
 */
public class Paser {
	
	private int CurTok = 0;
	private Map<Character, Integer> BinopPrecedence = new HashMap<Character, Integer>();  
	
	public int getNextToken(char[] input) {
		return CurTok = Lexer.gettok(input);
	}
	
//	public int GetTokPrecedence() {
//		if (!CharUtil.isAscii((char)CurTok)) {
//			return -1;
//		}
//		int TokPrec = BinopPrecedence[CurTok];
//	}
	
}
