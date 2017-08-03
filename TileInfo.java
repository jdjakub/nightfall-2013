class TileInfo {
	public final int x;
	public final int y;
	public final ISector sector;
	public final int tile;
	
	public TileInfo(int x, int y, ISector s, int t) {
		this.x = x;
		this.y = y;
		sector = s;
		tile = t;
	}
}