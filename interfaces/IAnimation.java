import org.jsfml.graphics.*;

interface IAnimation {
	public void reset();
	public boolean isVisible();
	public void setVisible(boolean visible);
	public void update(float deltaSec);
	public int getCurrentFrame();
}