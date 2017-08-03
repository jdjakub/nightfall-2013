class Actions {
	public static final ProgAction slice		= new StandardAttackAction("Slice", 1, 2, 0);
	public static final ProgAction cut			= new StandardAttackAction("Cut", 1, 2, 0);
	public static final ProgAction dice			= new StandardAttackAction("Dice", 1, 3, 3);
	public static final ProgAction mutilate		= new StandardAttackAction("Mutilate", 1, 4, 4);
	public static final ProgAction glitch		= new StandardAttackAction("Glitch", 1, 2, 0);
	public static final ProgAction stone		= new StandardAttackAction("Stone", 2, 1, 0);
	public static final ProgAction taser		= new StandardAttackAction("Taser", 1, 4, 0);
	public static final ProgAction thump		= new StandardAttackAction("Thump", 1, 3, 0);
	public static final ProgAction bash			= new StandardAttackAction("Bash", 1, 5, 0);
	public static final ProgAction crash		= new StandardAttackAction("Crash", 1, 7, 0);
	public static final ProgAction smash		= new StandardAttackAction("Smash", 1, 9, 0);
}