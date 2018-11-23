/**
 *
 * Copyright (C) 2018 Luvina JSC
 * Computer.java Nov 21, 2018 Mai Khoa Huong
 */
package com.luvina.caro;

import java.util.Random;

/**
 * Class xử lý logic cho việc máy đánh theo thế cờ và khi không tìm thấy thế cờ
 * trong file
 * 
 * @author: Mai Khoa Huong
 *
 */
public class Computer {
	public BoardCaro board; // khai báo biên bàn cờ
	public String name; // khai báo biến tên máy
	public ListChess listChess; // khai báo biến lưu các thế cờ
	public Point pointD; // khai báo biến lưu list các thế cờ

	/**
	 * Khởi tạo đối tượng Computer với các tham số truyền vào là board, name,
	 * listChess
	 * 
	 * @param board     tham số truyền vào cho đối tượng bàn cờ
	 * @param name      tham số truyền vào là tên của máy
	 * @param listChess tham số truyền vào để lưu list các thế cờ
	 */
	public Computer(BoardCaro board, String name, ListChess listChess) {
		pointD = new Point(); // Khởi tạo tạo độ điểm cần đánh của máy
		this.board = board;
		this.name = name;
		this.listChess = listChess;
	}

	/**
	 * Phương thức tìm điểm cần đánh cho máy
	 * 
	 * @param chess     tham số truyền vào cho đối tượng thế cờ Chess
	 * @param boardCaro tham số truyền vào cho đối tượng bàn cờ
	 * @return trả về true nếu tìm được điểm cần đánh của máy
	 */
	public boolean findPoint(Chess chess, BoardCaro boardCaro) {
		Chess ch = new Chess(); // Khởi tạo một đối tượng thế cờ [5][5] để lưu từng ma trận [5][5] trên bàn cờ
		for (int row = 1; row < 16; row++) {
			for (int col = 1; col < 16; col++) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						// gán giá trị của ma trận [5][5] tạm thời của thế cờ là giá trị của ma trận
						// [5][5] trên bàn cờ
						ch.chess[i][j] = boardCaro.board[row + i][col + j];
					}
				}
				if (compareChess(chess, ch)) { // nếu ma trận [5][5] của thế cờ giống ma trận [5][5] trên bàn cờ
					Point p = findD(chess); // Lấy tọa độ vị trí của kí tự "D" cần đánh
					pointD.setX(row + p.getX()); // Set x của điểm máy cần đánh
					pointD.setY(col + p.getY()); // set y của điểm máy cần đánh
					return true; // Trả về true khi tìm được điểm cần đánh cho máy
				}
			}
		}
		return false; // Trả về false nếu không tìm được điểm cần đánh cho máy
	}

	/**
	 * Phương thức tìm điểm máy cần đánh "D" trong thế cờ
	 * 
	 * @param chess tham số truyền vào cho thế cờ
	 * @return trả về một tạo độ điểm p là tọa độ của vị trí D máy cần đánh
	 */
	private Point findD(Chess chess) {
		Point p = new Point(); // Khởi tạo một đối tượng Point p để lưu điểm "D"
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if ("D".equals(chess.chess[row][col])) { // Nếu giá trị vị trí điểm đang xét = "D"
					p.setX(row); // set giá trị x cho điểm p
					p.setY(col); // set giá tri y cho điểm p
				}
			}
		}
		return p; // trả về p là tọa độ vị trí của điểm "D" mà máy cần đánh
	}

	/**
	 * Phương thức thực hiện so sánh giữa 2 đối tượng ma trận [5][5] của thế cờ với
	 * ma trận [5][5] của bàn cờ
	 * 
	 * @param chess tham số truyền vào cho ma trận [5][5] của thế cờ
	 * @param ch    tham số truyền vào cho ma trận [5][5] của bàn cờ
	 * @return trả về true nếu 2 ma trận trên giống nhau và ngược lại
	 */
	private boolean compareChess(Chess chess, Chess ch) {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				String chessValue = chess.chess[row][col]; // Lấy giá trị của thế cờ
				String chValue = ch.chess[row][col]; // Lấy giá trị của bàn cờ
				switch (chessValue) {
				// Trong trường hợp giá trị của thế cờ là "X" hoặc "O"
				case "X":
				case "O":
					if (!chessValue.equals(chValue)) { // Nếu giá trị của thế cờ khác với giá trị của bàn cờ
						return false; // thì trả về false nếu 2 ma trận không giống nhau
					}
					break;
				// Trong trường hợp giá trị của thế cờ là "T" hoặc "D"
				case "T":
				case "D":
					if (!chValue.equals("")) { // Nếu giá trị của bàn cờ khác ""
						return false; // thì trả về false
					}
					break;
				}
			}
		}
		return true; // Trả về true nếu 2 ma trận trên giống nhau
	}

	/**
	 * Phương thức thực hiện việc đánh cờ của máy
	 */
	public void playCom() {
		// Duyệt tất cả các thế cờ có trong list
		for (Chess chess : listChess.list) {
			// Nếu tìm được điểm cần đánh thì thực hiện in ra màn hình vị trí đó
			if (findPoint(chess, board)) {
				board.insertBoard(pointD, "X");
				break;
			}
		}
		if (pointD.isNull()) { // Kiểm tra điểm máy cần đánh có phù vs thế cờ nào trong list hay không
			Random rd = new Random(); // Khởi tạo một đối tượng cho máy đánh khi không tìm được thế cờ
			int x = 0, y = 0, t = 0; // Khởi tạo các biến hàng ngang x, cột dọc y và biến đếm t
			do {
				x = rd.nextInt(20); // Lấy giá trị ngẫu nhiên cho hàng ngang trong số 20 hàng
				y = rd.nextInt(20); // Lấy giá trị ngẫu nhiên cho cột dọc trong số 20 cột
				pointD.setX(x); // Set giá trị hàng ngang cho điểm máy cần đánh
				pointD.setY(y); // Set giá trị cột dọc cho điểm máy cần đánh
				board.insertBoard(pointD, "X"); // Hiển thị điểm random máy vừa đánh
				t = 1;
			} while (t != 1); // Nếu t = 1 thì thực hiện dừng vòng lặp
		} else if (board.checkWin("X", pointD)) { // Kiểm tra check win cho máy
			board.check_win = true; // nếu kiểm tra checkWin trả về true thì gán giá trị true cho biến check_win
		}
	}
}
