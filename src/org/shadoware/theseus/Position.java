package org.shadoware.theseus;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object position) {
		if (position instanceof Position) {
			int x_ = ((Position)position).x;
			int y_ = ((Position)position).y;
			return (x == x_) && (y == y_);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return x + "," + y;
	}
}
