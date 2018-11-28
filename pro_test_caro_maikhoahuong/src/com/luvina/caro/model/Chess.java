/**
 *
 * Copyright (C) 2018 Luvina JSC
 * Chess.java Nov 20, 2018 Mai Khoa Huong
 */
package com.luvina.caro.model;

/**
 * Tạo class Chess là ma trận [5, 5] để lưu thể cờ
 * 
 * @author: Mai Khoa Huong
 *
 */
public class Chess {
	// Khởi tạo ma trận 5 * 5
	public String chess[][] = new String[5][5];

	/**
	 * Phương thức thêm thế cờ
	 * 
	 * @param str tham số truyền vào
	 * @return trả về một thế cờ
	 */
	public Chess addChess(String str) {
		// Khởi tạo 1 biến tạm thời ch
		Chess ch = new Chess();
		// tách chuỗi
		String[] arr = str.split("");
		// Khởi tạo biến đếm temp
		int temp = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				ch.chess[i][j] = arr[temp]; // gán mỗi phần tử của thế cờ bằng 1 phần tử của mảng arr
				temp++; // tăng biến đem temp thêm 1
			}
		}
		return ch; // Trả về 1 thế cờ
	}
}
