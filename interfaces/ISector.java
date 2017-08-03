interface ISector {
	public int getTypeId();
	public int getX();
	public int getY();
	public RenderInfo getRenderInfo();
	public int getMove();
	public int getDefaultMove();
	public int getMaxSize();
	public int getCurrSize();
	public String getName();
	public String getDesc();
	public ISector getParent();
	public ISector getChild();
	public boolean isSelectable();
	//public boolean isSolid();
	public ProgAction[] getActions();
	
	public void setX(int x);
	public void setY(int y);
	public void setRenderInfo(RenderInfo rnd);
	public void setMove(int move);
	public void setDefaultMove(int defMove);
	public void setMaxSize(int maxSize);
	public void setCurrSize(int currSize);
	public void setName(String newName);
	public void setDesc(String desc);
	public void setParent(ISector par);
	public void setChild(ISector ch);
	
	public ISector clone();
}