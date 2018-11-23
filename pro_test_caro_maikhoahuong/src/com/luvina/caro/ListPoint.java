/**
 *
 * Copyright (C) 2018 Luvina JSC
 * ListPoint.java Nov 21, 2018 Mai Khoa Huong
 */
package com.luvina.caro;

import java.util.ArrayList;

/**
 * Tạo danh sách các điểm và kiểm tra các vị trị các điểm vừa được nhập vào
 * 
 * @author: Mai Khoa Huong
 *
 */
public class ListPoint {
	// Khởi tạo một danh sách listPoint
	ArrayList<Point> listPoint = new ArrayList<Point>();

	/**
	 * Phương thức kiểm tra vị trí vừa nhập đã tồn tại hay chưa
	 * 
	 * @param p tham số truyền vào cho đối tượng Point
	 * @return trả về false nếu vị trí chưa tồn tại và trả về true nếu ví trí đã tồn
	 *         tại
	 */
	public boolean checkPoint(Point p) {
		for (int i = 0; i < listPoint.size(); i++) {
			// Kiểm tra vị trí của từng phần tử
			if (listPoint.get(i).getX() == p.getX() && listPoint.get(i).getY() == p.getY()) {
				System.out.println("Vị trí đã tồn tại.");
				return true;
			}
		}
		return false;
	}

	/**
	 * Kiểm tra bàn cờ đã đầy các vị trí hay chưa
	 * 
	 * @return false nếu bàn cờ chưa hết các vị trí và trả về true nếu bàn cờ đã đầy
	 *         và đưa ra thông báo ván đấu đã hòa
	 */
	public boolean checkFullBoard() {
		// Kiểm tra số điểm đã đánh với tổng số điểm trên bàn cờ
		if (listPoint.size() == 20 * 20) {
			System.out.println("Bạn và máy đã hòa");
			return true;
		}
		return false;
	}
}
