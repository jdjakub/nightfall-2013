class Sector implements ISector {
	public static final int MAX_SECTORS = 256;
	private static int nextId = 0;	
	private static final ISector[] sectors = new ISector[MAX_SECTORS];
	
	public static final ISector empty			= new Sector(null, null, null);	// 0
	public static final ISector tail			= new SectorLink(new RenderInfo(63), "Tail", "This text should, theoretically, never appear");	// 1	
	public static final ISector upload			= new Sector(new RenderInfo(2), "Upload Zone", "Upload your programs here");	// 2
	public static final ISector dataItem		= new Sector(new RenderInfo(3), "Data Item", "Collect this to win the databattle");		// 3
	public static final ISector progHack		= new SectorProgram(new RenderInfo(5), "Hack", "Basic attack program", 2, 4, new ProgAction[]{Actions.slice});	// 4
	public static final ISector progBug			= new SectorProgram(new RenderInfo(6), "Bug", "Fast, cheap, and out of control", 5, 1, new ProgAction[]{Actions.glitch});		// 5
	public static final ISector progSling		= new SectorProgram(new RenderInfo(7), "Slingshot", "Basic ranged attack program", 2, 2, new ProgAction[]{Actions.stone});	// 6
	public static final ISector progDataDoc		= new SectorProgram(new RenderInfo(8), "Data Doctor", "Helps your programs grow", 4, 5, null);	// 7
	public static final ISector progBitMan		= new SectorProgram(new RenderInfo(9), "Bit-Man", "Makes sectors of the grid appear or disappear", 3, 3, null);	// 8
	public static final ISector progSentinel	= new SectorProgram(new RenderInfo(12), "Sentinel", "Corporate data defender", 1, 3, new ProgAction[]{Actions.cut});	// 9
	public static final ISector progHack2		= new SectorProgram(new RenderInfo(10), "Hack 2.0", "Improved hack with better attacks", 3, 4, new ProgAction[]{Actions.slice, Actions.dice});		// 10
	public static final ISector progBrainBurrow	= new SectorProgram(new RenderInfo(13), "BrainBurrow", "Caused by mental drilling", 2, 4, null);	// 11
	public static final ISector progAuto		= new SectorProgram(new RenderInfo(14), "Automaton", "Garbage in, garbage out", 1, 1, null);	// 12
	public static final ISector progHack3		= new SectorProgram(new RenderInfo(15), "Hack 3.0", "The top of the Hack series", 4, 4, new ProgAction[]{Actions.slice, Actions.mutilate});	// 13
	public static final ISector progMandelBug	= new SectorProgram(new RenderInfo(16), "MandelBug", "They can't kill what they can't catch", 5, 1, null);	// 14
	public static final ISector progHeisenBug	= new SectorProgram(new RenderInfo(17), "HeisenBug", "It's not a bug, it's a feature", 5, 1, null);	// 15
	public static final ISector progDeBug		= new SectorProgram(new RenderInfo(18), "De-Bug", "Does what it says on the tin", 5, 1, null);	// 16
	public static final ISector progWarden		= new SectorProgram(new RenderInfo(19), "Warden", "Slow and steady corporate attack program", 1, 5, new ProgAction[]{Actions.thump});	// 17
	public static final ISector progWardenP		= new SectorProgram(new RenderInfo(20), "Warden+", "Get out of its way", 2, 6, new ProgAction[]{Actions.bash});		// 18
	public static final ISector progWardenPP	= new SectorProgram(new RenderInfo(21), "Warden++", "The last word in corporate security", 3, 7, new ProgAction[]{Actions.crash});	// 19
	public static final ISector progWardenS		= new SectorProgram(new RenderInfo(22), "Warden#", "Master of brute force", 4, 8, new ProgAction[]{Actions.smash});	// 20
	public static final ISector progSentinel2	= new SectorProgram(new RenderInfo(23), "Sentinel 2.0", "Improved corporate data defender", 2, 4, new ProgAction[]{Actions.cut});	// 21
	public static final ISector progSentinel3	= new SectorProgram(new RenderInfo(24), "Sentinel 3.0", "Most certainly does NOT attack multiple programs at once", 2, 4, new ProgAction[]{Actions.taser});	// 22
	public static final ISector progSpeedy		= new SectorProgram(new RenderInfo(6), "Speedy Gonzales", "Arriba!", 10, 1, null);	// 23 - temp
	
	private static int getNextId() {
		int id = nextId;
		nextId++;
		return id;
	}
	
	public static ISector getPrototype(int id) {
		if (id >= MAX_SECTORS) {
			return null;
		} else {
			return sectors[id];
		}
	}
	
	public static ISector create(int id, int x, int y) {
		ISector sec = getPrototype(id);
		if (sec != null) {
			sec = sec.clone();
			sec.setX(x);
			sec.setY(y);
		}
		return sec;
	}
	
	protected final int id;
	protected int x;
	protected int y;
	protected RenderInfo rnd;
	protected String name;
	protected String desc;

	protected int currSize;
	
	protected Sector(int id, RenderInfo rnd, String name, String desc) {
		this.id = id;
		this.x = 0;
		this.y = 0;
		this.rnd = rnd;
		this.name = name;
		this.desc = desc;
		this.currSize =  1;
	}
	
	protected Sector(RenderInfo rnd, String name, String desc) {
		this(getNextId(), rnd, name, desc);
		if (sectors[id] == null) {
			sectors[id] = this;
		} else {
			throw new IllegalArgumentException("Sector id "+id+"is already occupied.");
		}
	}

	//ACCESSORS
	//=========
	
	public int getTypeId() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public RenderInfo getRenderInfo() {
		return rnd;
	}
	
	public int getMove() {
		return 0;
	}
	
	public int getDefaultMove() {
		return 0;
	}
	
	public int getMaxSize() {
		return 1;
	}
	
	public int getCurrSize() {
		return currSize;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public ISector getParent() {
		return null;
	}
	
	public ISector getChild() {
		return null;
	}
	
	public boolean isSelectable() {
		return true;
	}
	
	public ProgAction[] getActions() {
		return null;
	}
	
	//SETTERS
	//=======
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setRenderInfo(RenderInfo rnd) {
		this.rnd = rnd;
	}
	
	public void setMove(int move) {}
	
	public void setDefaultMove(int defMove) {}
	
	public void setMaxSize(int maxSize) {}
	
	public void setCurrSize(int currSize) {}
	
	public void setName(String newName) {
		this.name = name;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setParent(ISector par) {}
	
	public void setChild(ISector ch) {}
	
	public ISector clone() {
		ISector tmp = new Sector(id, rnd, name, desc);
		tmp.setX(x);
		tmp.setY(y);
		return tmp;
	}
}