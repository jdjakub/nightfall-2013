public interface ISectorWriteAccess extends ISectorReadAccess {
	public void setSector(int x, int y, ISector sec);
	public void set(int x, int y, int val);
	public void moveSector(ISector sector, int tx, int ty);
}