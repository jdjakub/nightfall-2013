class SectorLink extends Sector {
	private ISector parent;
	private ISector child;
	
	protected SectorLink(int id, RenderInfo rnd, String name, String desc) {
		super(id, rnd, name, desc);
		parent = child = null;
	}
	
	public SectorLink(RenderInfo rnd, String name, String desc) {
		super(rnd, name, desc);
		parent = child = null;
	}
	
	public ISector getParent() {
		return parent;
	}
	
	public ISector getChild() {
		return child;
	}
	
	public boolean isSelectable() {
		return false;
	}
	
	public void setParent(ISector par) {
		parent = par;
	}
	
	public void setChild(ISector ch) {
		child = ch;
	}
	
	public ISector clone() {
		ISector tmp = new SectorLink(id, rnd, name, desc);
		tmp.setX(x);
		tmp.setY(y);
		return tmp;
	}
}