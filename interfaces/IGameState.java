interface IGameState {
	public boolean init();
	public void onChange();
	public void update(float deltaSec);
}