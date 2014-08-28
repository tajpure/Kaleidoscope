package com.taj.Kaleidoscope.semantic;

import java.util.HashMap;
import java.util.Map;

/**
 * 语义分析器
 * @author taojx
 *
 */
public class Paser {
	
	private int CurTok = 0;
	private Map<Character, Integer> BinopPrecedence = new HashMap<Character, Integer>();  
	
//	public int getNextToken() {
//		Lexer.scan();
//		return CurTok = Lexer.gettok();
//	}
	
//	public int GetTokPrecedence() {
//		if (!CharUtil.isAscii((char)CurTok)) {
//			return -1;
//		}
//		int TokPrec = BinopPrecedence[CurTok];
//	}
	
}
