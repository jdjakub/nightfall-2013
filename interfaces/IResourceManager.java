import org.jsfml.graphics.*;

interface IResourceManager {
	public boolean init();
	public Texture getTexture(Resource which);
	public Image getImage(Resource which);
	public ConstFont getFont(Resource which);
	public void loadTexture(Resource which, String filename);
}