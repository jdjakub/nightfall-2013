class SectorProgram extends SectorLink {
	private int defMove;
	private int move;
	private int maxSize;
	private ProgAction[] actions;

	private SectorProgram(int id, RenderInfo rnd, String name, String desc, int move, int maxSize, ProgAction[] acts) {
		super(id, rnd, name, desc);
		this.move = defMove = move;
		this.maxSize = maxSize;
		actions = acts;
	}
	
	public SectorProgram(RenderInfo rnd, String name, String desc, int move, int maxSize, ProgAction[] acts) {
		super(rnd, name, desc);
		this.move = defMove = move;
		this.maxSize = maxSize;
		actions = acts;
	}
	
	public int getMove() {
		return move;
	}
	
	public int getDefaultMove() {
		return defMove;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public boolean isSelectable() {
		return true;
	}
	
	public ProgAction[] getActions() {
		return actions;
	}
	
	public void setMove(int move) {
		this.move = move;
		if (this.move < 0) {
			this.move = 0;
		}
	}
	
	public void setDefaultMove(int defMove) {
		this.defMove = defMove;
		if (this.defMove < 0) {
			this.defMove = 0;
		}
	}
	
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
		if (this.maxSize < 1) {
			this.maxSize = 1;
		}
	}
	
	public void setCurrSize(int currSize) {
		this.currSize = currSize;
		if (this.currSize < 1) {
			this.currSize = 1;
		}
	}
	
	public ISector clone() {
		SectorProgram tmp = new SectorProgram(id, rnd, name, desc, move, maxSize, actions);
		tmp.setX(x);
		tmp.setY(y);
		return tmp;
	}
}