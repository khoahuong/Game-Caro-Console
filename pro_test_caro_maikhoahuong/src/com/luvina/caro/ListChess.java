/**
 *
 * Copyright (C) 2018 Luvina JSC
 * ListChess.java Nov 21, 2018 Mai Khoa Huong
 */
package com.luvina.caro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Đọc file và lưu thế cờ vào 1 danh sách list các thế cờ
 * 
 * @author: Mai Khoa Huong
 *
 */
public class ListChess {
	// Tạo một mảng để lưu các thế cờ
	public ArrayList<Chess> list = new ArrayList<Chess>();

	/**
	 * Phương thức đọc file từ file theco.txt
	 * 
	 * @throws IOException           Xử lý lỗi không đọc được file
	 * @throws FileNotFoundException Xử lý lỗi không tìm thấy file
	 */
	public void read() throws IOException, FileNotFoundException {
		BufferedReader br = null; // Khởi tạo biến br
		FileReader fr = null; // Khởi tạo biến fr
		try {
			// Lấy file thế cờ
			fr = new FileReader("theco.txt");
			br = new BufferedReader(fr);
			// đọc file
			String str = br.readLine();
			while (str != null) { // Nếu str = null thì dừng vòng lặp
				// Khởi tạo 1 biến ch
				Chess ch = new Chess();
				// Khởi tạo một chuỗi str1
				String str1 = "";
				for (int i = 0; i < 5; i++) {
					// Sau mỗi vòng lặp thêm 1 dòng str vào chuỗi str1
					str1 += str;
					str = br.readLine(); // Thực hiện đọc dòng tiếp theo
				}
				// Thêm thế cờ vào danh sách
				list.add(ch.addChess(str1));
			}
		} catch (IOException e) { // xử lí lỗi đọc file
			System.out.println(e.getMessage());
		} finally {
			try {
				// Đóng br
				if (br != null) {
					br.close();
				}
				// Đóng fr
				if (fr != null) {
					fr.close();
				}
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
