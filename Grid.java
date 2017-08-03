class Grid<T> {
	private Object[] arr;
	private int width;
	private int height;
	
	private int indexFromCoords(int x, int y) {
		return y*width + x;
	}
	
	public Grid(int w, int h) {
		arr = new Object[w*h];
		width = w;
		height = h;
	}
		
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void fill(T t) {
		for (int i=0; i<arr.length; i++) {
			arr[i] = (Object) t;
		}
	}
	
	public T get(int x, int y) {
		return (T) arr[indexFromCoords(x, y)];
	}
	
	public void set(int x, int y, T t) {
		arr[indexFromCoords(x, y)] = (Object) t;
	}
	
	public boolean isValidCoord(int x, int y) {
		if (x < 0) return false;
		else if (y < 0) return false;
		else if (x >= width) return false;
		else if (y >= height) return false;
		else return true;
	}
	
	public Object[] getArray() {
		return arr;
	}
}