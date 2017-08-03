import org.jsfml.graphics.*;
import org.jsfml.window.event.*;
import org.jsfml.system.*;

class ProgInfoWindow extends Container {
	private static final int SIDE_WND_WIDTH = 200;
	private static final int SIDE_FULL_CONTROL_WIDTH = SIDE_WND_WIDTH - 2*8;
	private static final int BTN_HEIGHT = 0x18;
	private static final int BTN_PAD = 8;
	
	private static final int DESC_HEIGHT = 0x30;

	private Tilesheet progTiles;
	private ConstFont normalFont;
	private ConstFont emphFont;
	
	private Sprite sectorSpr;
	private boolean secVisible;
	
	private MiniWindow progInfoWnd;
	private StaticTextControl progData;
	private StaticTextControl progName;
	private StaticTextControl cmdsHeader;
	//private CommandButtonControl[] cmdButtons;
	private StaticTextControl desc;
	
	protected void showInfo(boolean val) {
		secVisible = val;
		progData.setVisible(val);
		progName.setVisible(val);
		cmdsHeader.setVisible(val);
		desc.setVisible(val);
	}
	
	protected void onClickDown(IScreenElement elem, int x, int y) {
		//System.out.println("Clicked: "+elem+" at ("+x+", "+y+")");
		//if (elem == testBtn) {
		//	testBtn.onPress();
		//} else {
		//}
	}
	
	protected void onClickUp(IScreenElement elem, int x, int y) {
		//System.out.println("Released: "+elem+" at ("+x+", "+y+")");
		//testBtn.onRelease();
	}
	
	protected void setAllVisible(boolean val) {
		for (IScreenElement elem : elems) {
			elem.setVisible(val);
		}
	}

	public ProgInfoWindow(Tilesheet progTiles, ConstFont normalFont, ConstFont emphFont) {
		this.progTiles = progTiles;
		this.normalFont = normalFont;
		this.emphFont = emphFont;
		setPosition(8, 216);
		setDimensions(SIDE_WND_WIDTH, 592-216);
	}
	
	public boolean init() {
		sectorSpr = progTiles.makeSprite(0);
		sectorSpr.setPosition(8+0x10, 216+0x18);
	
		progInfoWnd = new MiniWindow(normalFont, 10);
		progInfoWnd.setPosition(8, 216);
		progInfoWnd.setDimensions(SIDE_WND_WIDTH, 592-216);
		progInfoWnd.setTitle("program.info");
		addChild(progInfoWnd);

		progData = new StaticTextControl(normalFont, 10);
		progData.setPosition(110, 216+0x18);
		progData.setDimensions(84, 0x30);
		progData.setText("HELLO WORLD");
		addChild(progData);
		
		progName = new StaticTextControl(normalFont, 12);
		progName.setPosition(8+8, 216+0x50);
		progName.setDimensions(SIDE_FULL_CONTROL_WIDTH, 0x10);
		progName.setText("NOTHING");
		addChild(progName);
		
		cmdsHeader = new StaticTextControl(emphFont, 14);
		cmdsHeader.setPosition(8+8, 216+0x50+0x18);
		cmdsHeader.setDimensions(SIDE_FULL_CONTROL_WIDTH, 0x10);
		cmdsHeader.setColor(new Color(0x80, 0x80, 0x80, 0xFF));
		cmdsHeader.setText("COMMANDS");
		addChild(cmdsHeader);
		
		desc = new StaticTextControl(normalFont, 12);
		desc.setPosition(8+8, (600-0x10-DESC_HEIGHT));
		desc.setDimensions(SIDE_FULL_CONTROL_WIDTH, DESC_HEIGHT);
		desc.setText("Nothing yet...");
		addChild(desc);
	
		if (!super.init()) return false;
		return true;
	}
	
	public void reset(ISector sector) {
		if (sector != null) {
			String data = 	"Move: "+sector.getMove()+ 
							"\nMax Size: "+sector.getMaxSize() +
							"\nCurrent Size: "+sector.getCurrSize();
			progData.setText(data);
			progName.setText(sector.getName());
			desc.setText(sector.getDesc().toUpperCase());
			progTiles.changeSprite(sectorSpr, sector.getRenderInfo().imgIndex);
			showInfo(true);
		} else {
			showInfo(false);
		}
	}
	
	public void render(RenderWindow wnd) {
		super.render(wnd);
		if (secVisible) {
			wnd.draw(sectorSpr);
		}
	}
}