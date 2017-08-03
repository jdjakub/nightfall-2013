import java.util.Iterator;

interface ISectorReadAccess extends Iterable<TileInfo> {
	public int getGridWidth();
	public int getGridHeight();
	public ISector getSector(int x, int y);
	public boolean isSectorEmpty(int x, int y);
	public boolean isOn(int x, int y);
	public boolean isValidCoord(int x, int y);
	public Iterator<TileInfo> iterator();
}