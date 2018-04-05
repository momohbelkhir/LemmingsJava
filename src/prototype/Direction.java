package prototype;

public enum Direction {
	Up(0, -1), Down(0, 1), Left(-1, 0), Right(1, 0), Bloquer(0,0);

	private int x;
	private int y;
	
	   Direction(int x, int y) {
		   
		this.setX(x);
		this.setY(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	

}
