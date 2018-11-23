/**
 *
 * Copyright (C) 2018 Luvina JSC
 * Main.java Nov 21, 2018 Mai Khoa Huong
 */
package com.luvina.caro;

import java.util.Scanner;

/**
 * Class Main khởi tạo người chơi và máy, và các hành động của người chơi và máy
 * 
 * @author: Mai Khoa Huong
 *
 */
public class Main {
	/**
	 * Phương thức thực hiện điều khiển chương trình
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String check;
		do {
			try {

				// khởi tạo đối tương bàn cờ boardCaro
				BoardCaro boardCaro = new BoardCaro();
				// Vẽ bàn cờ
				boardCaro.paintBoard();
				// Khởi tạo danh sách thế cờ
				ListChess listChess = new ListChess();
				// Đọc file theco.txt
				listChess.read();
				// Khởi tạo một danh sách listPoint
				ListPoint listPoint = new ListPoint();
				// Khởi tạo một người chơi player
				Player player = new Player(boardCaro, "Bạn", listPoint);
				// Khởi tạo đối tượng com cho máy
				Computer com = new Computer(boardCaro, "Máy", listChess);
				// Hiển thị bàn cờ
				boardCaro.displayBoard();
				do {
					// Người thực hiện đánh nước cờ của mình
					player.play();
					if (boardCaro.check_win) { // Kiểm tra check win cho người
						boardCaro.displayBoard(); // Hiển thị bàn cờ
						System.out.println(player.name + " thắng.");
						break;
					}
					// Máy đánh
					com.playCom();
					if (boardCaro.check_win) { // Kiểm tra check win cho máy
						boardCaro.displayBoard(); // Hiển thị bàn cờ
						System.out.println(com.name + " thắng.");
						break;
					}
					// Lấy điểm máy vừa đánh lưu vào danh sách list của người chơi
					player.listPoint.listPoint.add(com.pointD);
					boardCaro.displayBoard(); // Hiển thị bàn cờ
				} while (!listPoint.checkFullBoard()); // vòng lặp dừng lại khi bàn cờ đã full

			} catch (Exception e) {
				System.out.println("Máy đã đầu hàng.");
			}
			do {
				System.out.println("Bạn có muốn chơi nữa không? y/n");
				check = scanner.nextLine(); // Nhập giá trị y/n từ bàn phím
			} while (!"y".equalsIgnoreCase(check) && !"n".equalsIgnoreCase(check));

		} while ("y".equalsIgnoreCase(check)); // Nếu là n thì dừng chương trình
		scanner.close(); // đóng scanner
	}
}
