package com.taj.Kaleidoscope.entity;

/**
 * �ɴʷ��������ɵĶ�Ԫʽ
 * @author taojx
 *
 */
public class Token {
	
	private int number = -1;
	
	private String symbol = null;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		return "(" + number + "," + symbol + ")"; 
	}

}
