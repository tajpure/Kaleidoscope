package com.taj.Kaleidoscope.entity;

/**
 * 符号表 (Symbol Table)
 * @author taojx
 *
 */
public class ST {
	// 未定义
	public static final int UNDEDINE = 0;
	// 结束符
	public static final int EOF = 1;
	// 函数定义
	public static final int DEF = 2;
	// 外部声明
	public static final int EXTERN = 3;
	// 标识符
	public static final int IDENTIFIER = 4;
	// 数字
	public static final int NUMBER = 5;
	// 变量名
	public static final int VARIABLES = 6;
	// 整形
	public static final int INT = 7;
	// 浮点数
	public static final int FLOAT = 8;
	// 双精度浮点数
	public static final int DOUBLE = 9;
	// 字符串
	public static final int STRING = 10;
	// 静态标识符
	public static final int STATIC = 11;
	// 终态
	public static final int FINAL = 12;
	// 公有类型
	public static final int PUBLIC = 13;
	// 保护类型
	public static final int PROTECT = 14;
	// 私有类型
	public static final int PRIVATE = 15;
	// =
	public static final int EQUAL = 16;
	// +
	public static final int PLUS = 17;
	// -
	public static final int SUBTRACT = 18;
	// *
	public static final int MULTIPLY = 19;
	// /
	public static final int DIVIDE = 20;
	// 注释
	public static final int COMMENT = 21;
	// <
	public static final int LT = 22;
	// >
	public static final int BT = 23;
	// 左圆括号
	public static final int LP = 24;
	// 右圆括号
	public static final int RP = 25;
	// 分号
	public static final int SEMICOLON = 26;
	// 逗号
	public static final int COMMA = 27;
}
