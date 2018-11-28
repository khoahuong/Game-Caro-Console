/**
 *
 * Copyright (C) 2018 Luvina JSC
 * BoardCaro.java Nov 20, 2018 Mai Khoa Huong
 */
package com.luvina.caro.view;

import com.luvina.caro.model.Point;

/**
 * Tạo bàn cờ với ma trận 20*20, và cách check win cho ván đấu
 * 
 * @author: Mai Khoa Huong
 *
 */
public class BoardCaro {
	// Khởi tạo bàn cờ với ma trận 20*20
	public String board[][] = new String[21][21];
	// Khởi tạo biến kiểm tra check win
	public boolean check_win = false;

	/**
	 * Vẽ bàn cờ với ma trân 20*20
	 */
	public void paintBoard() {
		// Khởi tạo một ví trí trên bàn cờ
		board[0][0] = "";
		// in ra vị trí các hàng, các cột của bàn cờ
		for (int i = 1; i < 21; i++) {
			board[0][i] = board[i][0] = String.valueOf(i);
		}
		for (int row = 1; row < 21; row++) {
			for (int col = 1; col < 21; col++) {
				// Gán vị trí này cho kí tự "-"
				this.board[row][col] = "-";
			}
		}
	}

	/**
	 * Hiển thị bàn cờ trên console
	 */
	public void displayBoard() {
		for (int row = 0; row < 21; row++) {
			for (int col = 0; col < 21; col++) {
				// Hiển thị phần tử
				System.out.print(this.board[row][col] + "\t");
//				System.out.print("|  ");
			}
			System.out.println();
			System.out.println();
//			System.out.println(
//					"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
		}
	}

	/**
	 * Phương thức điền quân cờ cần đánh vào bàn cờ
	 * 
	 * @param p   tham số truyền vào cho đối tượng Point là điểm cần đánh
	 * @param str tham số truyền vào là kí tự cần đánh
	 */
	public void insertBoard(Point p, String str) {
		// Điền kí tự cần đánh vào ví trị trên bàn cờ
		this.board[p.getX()][p.getY()] = str;
	}

	/**
	 * Phương thức kiểm tra check win
	 * 
	 * @param str kí cần check win
	 * @param p   vị trí vừa nhập cần check win
	 * @return true nếu có 5 kí tự liên tiếp theo hàng ngang, hàng dọc và hàng chéo
	 */
	public boolean checkWin(String str, Point p) {
		if (checkWinRow(str, p) || checkWinCol(str, p) || checkWinCrossDown(str, p) || checkWinCrossUp(str, p)) {
			return true; // Trả về true nếu 1 trong 4 trường hợp kiểm tra trên trả về true
		} else {
			return false; // Trả về false nếu cả 4 trường hợp check win trên trả về giá trị false
		}
	}

	/**
	 * Phương thức kiểm tra check win theo hàng. Nên tung độ không đổi và hoành độ
	 * thay đổi
	 * 
	 * @param str kí tự truyền vào cần kiểm tra check win
	 * @param p   Vị trí vừa truyền vào cần kiểm tra check win
	 * @return trả về true nếu có 5 kí tự liên tiếp theo hàng ngang
	 */
	public boolean checkWinRow(String str, Point p) {
		int checked = 0;
		// Do tại vị trí cần kiểm tra check win, ta phải kiểm tra cả phạm vi bên trái và
		// bên phải của ví trí đó xem có những kí tự giống kí tự đó không
		for (int row = -4; row < 5; row++) {
			if (checkLengthRow(row, p)) { // Nếu phạm vi điểm kiểm tra hơp lệ trong bàn cờ
				if (str.equals(this.board[p.getX() + row][p.getY()])) { // Nếu giá trị vị trí điểm đang xét trùng với kí
																		// tự cửa str truyền vào
					checked++; // tăng biến đếm checked thêm 1
					if (checked == 5) {
						return true; // trả về true nếu có 5 kí tự giống nhau
					}
				} else if (checked < 5) {
					checked = 0; // nếu checked < 5 thì trả về checked = 0;
				}
			}
		}
		return false; // trả về false nếu có nhỏ hơn 5 kí tự giống nhau
	}

	/**
	 * Kiểm tra phạm vi hợp lệ trong bàn cờ theo hàng ngang
	 * 
	 * @param row giá trí hàng ngang truyền vào tại vị trí cần kiểm tra
	 * @param p   vị trí cần kiểm tra
	 * @return trả về true nếu phạm vi hợp lệ
	 */
	public boolean checkLengthRow(int row, Point p) {
		if ((row + p.getX()) >= 0 && (row + p.getX()) <= 20) {
			return true; // phạm vi hợp lệ
		}
		return false; // trả về false nếu các vị trí kiểm tra nằm ngoài phạm vi hợp lệ của bàn cờ
	}

	/**
	 * Phương thức kiểm tra check win theo cột. Nên hoành độ không đổi và tung độ
	 * thay đổi
	 * 
	 * @param str Kí tự truyền vào cần kiểm tra check win
	 * @param p   Vị trí vừa truyền vào cần kiểm tra check win
	 * @return Trả về true nếu có 5 kí tự liên tiếp giống nhau theo cùng một cột
	 */
	public boolean checkWinCol(String str, Point p) {
		int checked = 0;
		for (int col = -4; col < 5; col++) {
			if (checkLengthCol(col, p)) { // Nếu phạm vi điểm kiểm tra hợp lệ trên bàn cờ
				if (str.equals(this.board[p.getX()][p.getY() + col])) { // Nếu giá trị vị trí điểm đang xét trùng với kí
																		// tự cửa str truyền vào
					checked++; // Tăng biến đếm checked thêm 1
					if (checked == 5) {
						return true; // nếu checked = 5 thì trả về true
					}
				} else if (checked < 5) {
					checked = 0; // Nếu checked < 5 thì trả về checked = 0
				}
			}
		}
		return false; // trả về false nếu có nhỏ hơn 5 kí tự giống nhau
	}

	/**
	 * Kiểm tra phạm vi hợp lệ trong bàn cờ theo cột dọc.
	 * 
	 * @param col Giá trị theo cột truyền vào tại ví trí cần kiểm tra
	 * @param p   Vị trí cần kiểm tra
	 * @return trả về true nếu phạm vi hợp lệ
	 */
	public boolean checkLengthCol(int col, Point p) {
		if ((col + p.getY()) >= 0 && (col + p.getY()) <= 20) {
			return true; // phạm vi hợp lệ
		}
		return false; // phạm vi không hợp lệ
	}

	/**
	 * Phương thức kiểm tra check win theo đường chéo từ trên xuống, nên hoành độ sẽ
	 * giảm và tung độ sẽ tăng hoặc ngược lại.
	 * 
	 * @param str Kí tự truyền vào cần check win
	 * @param p   Vị trí vừa truyền vào cần được kiểm tra check win
	 * @return Trả về true nếu có 5 kí tự liên tiếp giống nhau theo hàng chéo từ
	 *         trên xuống
	 */
	public boolean checkWinCrossDown(String str, Point p) {
		int checked = 0;
		for (int i = -4; i < 5; i++) {
			if (checkLengthCrossDown(i, p)) { // Nếu phạm vi điểm kiểm tra hợp lệ
				if (str.equals(this.board[p.getX() + i][p.getY() - i])) { // Nếu giá trị của điểm đang xét trùng với kí
																			// tự truyền vào cho biến str
					checked++; // Tăng biến đếm checked thêm 1
					if (checked == 5) {
						return true; // trả về true nếu checked = 5
					}
				} else if (checked < 5) {
					checked = 0; // Nếu checked < 5 thì trả về checked = 0
				}
			}
		}
		return false; // trả về false nếu có nhỏ hơn 5 kí tự giống nhau
	}

	/**
	 * Kiểm tra phạm vi hợp lệ theo đường chéo từ trên xuống
	 * 
	 * @param i tham số truyền vào
	 * @param j tham số truyền vào
	 * @param p tham số truyền vào cho đối tượng Point
	 * @return trả về true nếu phạm vi hợp lệ và ngược lại
	 */
	public boolean checkLengthCrossDown(int i, Point p) {
		if ((i + p.getX()) >= 0 && (i + p.getX()) <= 20 && (-i + p.getY()) >= 0 && (-i + p.getY()) <= 20) {
			return true; // phạm vi hợp lệ
		}
		return false; // phạm vi không hợp lệ
	}

	/**
	 * Phương thức kiểm tra check win theo đường chéo từ dưới lên. Nên cả hoành độ
	 * và tung độ cùng tăng và cùng giảm.
	 * 
	 * @param str Kí tự truyền vào cần kiểm tra check win
	 * @param p   Vị trí vừa truyền vào cần được kiểm tra check win
	 * @return trả về true nếu có 5 kí tự liên tiếp giống nhau theo hàng chéo từ
	 *         dưới lên
	 */
	public boolean checkWinCrossUp(String str, Point p) {
		int checked = 0;
		for (int i = -4; i < 5; i++) {
			if (checkLengthCrossUp(i, p)) { // Nếu phạm vi điểm kiểm tra hợp lệ
				if (str.equals(this.board[p.getX() + i][p.getY() + i])) { // Nếu giá trị vị trí điểm đang xét trùng với
																			// kí tự truyền vào cho biển str
					checked++; // thì tăng biến đếm checked thêm 1
					if (checked == 5) {
						return true; // trả về true nếu checked = 5
					}
				} else if (checked < 5) {
					checked = 0; // Nếu checked < 5 thì trả về checked = 0
				}
			}
		}
		return false; // trả về false nếu có nhỏ hơn 5 kí tự giống nhau
	}

	/**
	 * Kiểm tra phạm vi hợp lệ theo đường chéo từ dưới lên
	 * 
	 * @param i tham số truyền vào
	 * @param j tham số truyền vào
	 * @param p tham số truyền vào cho đối tượng Point
	 * @return trả về true nếu phạm vi hợp lệ và ngược lại
	 */
	public boolean checkLengthCrossUp(int i, Point p) {
		if ((i + p.getX()) >= 0 && (i + p.getX()) <= 20 && (i + p.getY()) >= 0 && (i + p.getY()) <= 20) {
			return true; // phạm vi hợp lệ
		}
		return false; // phạm vi không hợp lệ
	}
}
