abstract class StandardRangedAction extends ProgAction {
	protected int range;
	
	private static String makeDesc(String str, int rng, int req) {
		StringBuilder strb = new StringBuilder("Range: ");
		strb.append(rng);
		if (req > 0) {
			strb.append(" Req: ");
			strb.append(req);
		}
		strb.append(" ");
		strb.append(str);
		return strb.toString();
	}
	
	public StandardRangedAction(String name, String desc, int rng, int req) {
		
		super(name, makeDesc(desc, rng, req), req);
		range = rng;
	}
	
	public boolean isValidTarget(int relX, int relY) {
		relX = Math.abs(relX);
		relY = Math.abs(relY);
		
		int sum = relX+relY;
		return (sum <= range && sum != 0);
	}
	
	public abstract ProgAction.Type getType();
	
	public abstract void execute(IDatabattleContext ctx, ISector invoker, int tx, int ty);
}