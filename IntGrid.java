class IntGrid {
	private int[] arr;
	private int width;
	private int height;
	
	private int indexFromCoords(int x, int y) {
		return y*width + x;
	}
	
	public IntGrid(int w, int h) {
		arr = new int[w*h];
		width = w;
		height = h;
	}
		
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void fill(int b) {
		for (int i=0; i<arr.length; i++) {
			arr[i] = b;
		}
	}
	
	public int get(int x, int y) {
		return arr[indexFromCoords(x, y)];
	}
	
	public void set(int x, int y, int b) {
		arr[indexFromCoords(x, y)] = b;
	}
	
	public boolean isValidCoord(int x, int y) {
		if (x < 0) return false;
		else if (y < 0) return false;
		else if (x >= width) return false;
		else if (y >= height) return false;
		else return true;
	}
	
	// Ugly, but to make iteration less of a nightmare
	public int[] getArray() {
		return arr;
	}
}