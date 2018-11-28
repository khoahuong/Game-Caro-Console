/**
 *
 * Copyright (C) 2018 Luvina JSC
 * Player.java Nov 21, 2018 Mai Khoa Huong
 */
package com.luvina.caro.model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.luvina.caro.view.BoardCaro;

/**
 * Class xử lý logic cho người đánh
 * 
 * @author: Mai Khoa Huong
 *
 */
public class Player {
	public BoardCaro board; // khai báo biên bàn cờ
	public String name; // khai báo biến tên người chơi
	public ListPoint listPoint; // khai báo biến để lưu các vị trí đã đánh
	private static Scanner scanner;

	/**
	 * Khởi tạo đối tượng Player với 3 tham số truyền vào
	 * 
	 * @param broard    tham số truyền vào cho đối tượng BoardCaro
	 * @param name      tham số truyền vào cho tên người chơi
	 * @param listPoint tham số truyền vào để lưu các vị trí đã đánh
	 */
	public Player(BoardCaro broard, String name, ListPoint listPoint) {
		this.board = broard;
		this.name = name;
		this.listPoint = listPoint;
	}

	/**
	 * Phương thức thực hiện các hành động của người chơi
	 */
	public void activePlayer() {
		// Khai báo biến p để lưu vi trí điểm vừa đánh
		Point p = new Point();
		int x, y; // khai báo 2 số nguyên x, y
		do {
			System.out.println("Nhập tọa độ vị trí cần đánh.");
			do {
				System.out.print("Nhập vào hàng muốn chọn: ");
				x = Player.getValidate("hàng"); // Nhập vào giá trị của x: hàng
			} while (x == -1); // Nếu x = -1 thì thực hiện lại vòng lặp
			do {
				System.out.print("Nhập vào cột muốn chọn: ");
				y = Player.getValidate("cột"); // Nhập vào giá trị của y: cột
			} while (y == -1); // Nếu y = -1 thì thực hiện lại vòng lặp
			p.setX(x); // set tọa độ x vào Point
			p.setY(y); // set tọa độ y vào Point
		} while (listPoint.checkPoint(p)); // Nếu vị trí đánh tồn tại rồi thì thực hiện lại vòng lặp
		// Thêm vị trí vừa đánh vào listPoint
		listPoint.listPoint.add(p);
		// Thực hiện điền vị trí quân cờ vừa đánh vào bàn cờ
		board.insertBoard(p, "O");
		// Kiểm tra check win cho vị trí vừa đánh
		if (board.checkWin("O", p)) {
			board.check_win = true; // nếu kiểm tra checkWin trả về true thì gán giá trị true cho biến check_win
		}
	}

	/**
	 * Phương thức lấy giá trị và kiểm tra dữ liệu vừa nhâp vào
	 * 
	 * @param str tham số truyền vào tên của trục cần nhập
	 * @return trả về giá trị đã nhập hợp lệ của bàn cờ
	 */
	public static int getValidate(String str) {
		scanner = new Scanner(System.in);
		String num = "";
		int t = 0, a = 0;
		do {
			// Nhập giá trị cho biến num từ bàn phím
			num = scanner.nextLine();

			// Kiểm tra điều kiện chưa nhập gì hoặc là nhập khoảng trắng
			if (num.trim().isEmpty()) {
				System.out.print("Hãy nhập vào " + str + " bạn muốn: ");
				continue;
			}

			// Kiểm tra điều kiện có phải số hay không
			Pattern pattern = Pattern.compile("\\d*"); // Tạo đối tượng pattern kiểm tra một số nguyên
			Matcher matcher = pattern.matcher(num.trim()); // lấy ra đối tượng matcher kiểm tra chuỗi vừa nhập vào num
			if (matcher.matches()) {
				// chuyển đổi giá trị về dạng số
				a = Integer.parseInt(num.trim());
			} else {
				System.out.println("Bạn vừa nhập không phải là số.");
				System.out.print("Hãy nhập vào " + str + " bạn muốn: ");
				continue;
			}

			// Kiểm tra điều điện là số từ 1 - 20
			if (a <= 0 || a > 20) {
				System.out.println("Hãy nhập vào số nguyên từ 1 - 20.");
				System.out.print("Hãy nhập vào " + str + " bạn muốn: ");
				continue;
			} else {
				t = 1; // Thực hiện thành công gán giá trị cho t1 = 1
			}
			return a;
		} while (t != 1); // Nếu t1 khác 1 thì thực hiện lại vòng lặp
		return -1; // trả về -1 nếu không đúng định dạng
	}
}
