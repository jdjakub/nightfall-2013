public abstract class ProgAction {
	enum Type {
		OFFENSE(0),
		META(1),
		GRID(2);
		
		public final int x;
		
		Type(int x) {this.x = x;}
	}

	protected final String name;
	protected final String desc;
	protected final int sizeReq;
	
	public ProgAction(String nam, String descrip, int req) {
		if (req > 0) {
			StringBuilder strb = new StringBuilder(nam);
			strb.append("<");
			strb.append(req);
			strb.append(">");
			name = strb.toString();
		} else {
			name = nam;
		}
		desc = descrip;
		sizeReq = req;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public int getSizeReq() {
		return sizeReq;
	}
	
	public abstract Type getType();
	
	public abstract boolean isValidTarget(int relX, int relY);
	
	public abstract void execute(IDatabattleContext ctx, ISector invoker, int tx, int ty);
}