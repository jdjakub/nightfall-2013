class StandardAttackAction extends StandardRangedAction {
	protected final int damage;
	
	private static String makeDesc(int dmg) {
		StringBuilder desc = new StringBuilder("Deletes ");
		desc.append(dmg);
		desc.append(" sectors from target");
		return desc.toString();
	}
	
	public StandardAttackAction(String name, int range, int dmg, int req) {
		
		super(name, makeDesc(dmg), range, req);
		damage = dmg;
	}
	
	public void execute(IDatabattleContext ctx, ISector invoker, int tx, int ty) {
		ICommandQueue cmdQueue = Services.getCommandQueue();
		ISectorReadAccess grid = ctx.getGrid();
		
		ISector target = grid.getSector(tx, ty);
		if (target == null) return;
		
		int size = target.getCurrSize();
		int requiredDamage = damage;
		if (requiredDamage > size) {
			requiredDamage = size;
		}
		
		//cmdQueue.addCommand(new CmdActionActivated(SOMETHING...???));
		
		for (int i=0; i<requiredDamage; i++) {
			//cmdQueue.addCommand(new CmdDeleteLastSector(target));
		}
	}
	
	public ProgAction.Type getType() {
		return ProgAction.Type.OFFENSE;
	}
}