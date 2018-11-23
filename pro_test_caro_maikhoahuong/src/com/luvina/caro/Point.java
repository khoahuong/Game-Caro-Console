/**
 *
 * Copyright (C) 2018 Luvina JSC
 * Point.java Nov 20, 2018 Mai Khoa Huong
 */
package com.luvina.caro;

/**
 * Class đọc ghi các tọa độ vị trí của các điểm trên bàn cờ
 * 
 * @author: Mai Khoa Huong
 *
 */
public class Point {
	private int x;
	private int y;

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Khởi tạo đối tượng không có tham số truyền vào
	 */
	Point() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Khởi tạo đối tượng Point() với 2 tham số truyền vào là x, y
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Phương thức kiểm tra checkNull của một điểm
	 * 
	 * @return trả về false nếu có tạo độ điểm phù hợp
	 */
	public boolean isNull() {
		if (this.getX() == 0 && this.getY() == 0) {
			System.out.println("Không tìm thấy thế cờ cho máy.");
			return true;
		}
		return false; // Trả về false nếu tìm được điểm phù hợp
	}
}
