import java.util.Iterator;

class DatabattleGrid implements ISectorWriteAccess {
	private IntGrid tiles;
	private Grid<ISector> secs;	// Huh huh huh, hey Beavis, 'secs', uhuhuhuhuh...
	
	public DatabattleGrid(int w, int h) {
		tiles = new IntGrid(w, h);
		tiles.fill(0);
		secs = new Grid<ISector>(w, h);
	}
	
	public int getGridWidth() {
		return tiles.getWidth();
	}
	
	public int getGridHeight() {
		return tiles.getHeight();
	}
	
	public ISector getSector(int x, int y) {
		return secs.get(x, y);
	}
	
	public boolean isSectorEmpty(int x, int y) {
		return (secs.get(x, y) == null);
	}
	
	public boolean isOn(int x, int y) {
		return tiles.get(x, y) != 0;
	}
	
	public boolean isValidCoord(int x, int y) {
		return tiles.isValidCoord(x, y);
	}
	
	public void setSector(int x, int y, ISector sec) {
		//System.out.println("setSector("+x+", "+y+", "+sec+") called");
		secs.set(x, y, sec);
		if (sec != null) {
			sec.setX(x);
			sec.setY(y);
		}
		//if (sec != null) {
			//System.out.println("sec.getX() = "+sec.getX()+"\nsec.getY() = "+sec.getY());
		//}
	}
	
	public void set(int x, int y, int val) {
		tiles.set(x, y, val);
	}
	
	public void moveSector(ISector sector, int tx, int ty) {
		//System.out.println("moveSector("+sector.getName()+", "+tx+", "+ty+") called");
		ISector target = getSector(tx, ty);
		int srcX = sector.getX();
		int srcY = sector.getY();
		
		setSector(tx, ty, sector);
		
		setSector(srcX, srcY, target);
	}
	
	public Iterator<TileInfo> iterator() {
		return new MyIter();
	}
	
	class MyIter implements Iterator<TileInfo> {
		private int i;
		private int x;
		private int y;
	
		public boolean hasNext() {
			return i < tiles.getArray().length;
		}
		
		public TileInfo next() {
			TileInfo ti = new TileInfo(x, y, (ISector) secs.getArray()[i], tiles.getArray()[i]);
			i++;
			x++;
			if (x >= tiles.getWidth()) {
				x = 0;
				y++;
			}
			return ti;
		}
		
		public void remove() {}
	}
}