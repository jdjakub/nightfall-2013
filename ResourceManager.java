import org.jsfml.graphics.*;

import java.util.EnumMap;
import java.nio.file.Paths;

class ResourceManager implements IResourceManager {
	private EnumMap<Resource, Texture> textures;
	private EnumMap<Resource, Image> images;
	private EnumMap<Resource, ConstFont> fonts;
	
	public ResourceManager() {
		textures = new EnumMap<Resource, Texture>(Resource.class);
		images = new EnumMap<Resource, Image>(Resource.class);
		fonts = new EnumMap<Resource, ConstFont>(Resource.class);
		
		textures.put(Resource.TEX_PROGRAMS, null);
		textures.put(Resource.TEX_ENVIRONMENT, null);
		textures.put(Resource.TEX_GUI, null);
		textures.put(Resource.TEX_DATABATTLE_BACKG, null);
		
		images.put(Resource.IMG_TAILS, null);
		
		fonts.put(Resource.FNT_NORMAL, null);
		fonts.put(Resource.FNT_EMPHASIS, null);
	}
	
	public boolean init() {
		Texture prog = new Texture();
		Texture env = new Texture();
		Texture gui = new Texture();
		Texture dbBackg = new Texture();
		
		Image tails = new Image();
		
		Font normal = new Font();
		Font emph = new Font();
		
		try {
			prog.loadFromFile(Paths.get("progs.png"));
			gui.loadFromFile(Paths.get("gui.png"));
			env.loadFromFile(Paths.get("env.png"));
			dbBackg.loadFromFile(Paths.get("backg_binary.png"));
			
			tails.loadFromFile(Paths.get("tail_cols.png"));
			
			normal.loadFromFile(Paths.get("C:/Windows/Fonts/arial.ttf"));
			emph.loadFromFile(Paths.get("C:/Windows/Fonts/impact.ttf"));
		} catch (Exception e) {
			System.err.println("Error loading resource: "+e.getMessage());
			return false;
		}
		
		textures.put(Resource.TEX_PROGRAMS, prog);
		textures.put(Resource.TEX_ENVIRONMENT, env);
		textures.put(Resource.TEX_GUI, gui);
		textures.put(Resource.TEX_DATABATTLE_BACKG, dbBackg);
		
		images.put(Resource.IMG_TAILS, tails);
		
		fonts.put(Resource.FNT_NORMAL, normal);
		fonts.put(Resource.FNT_EMPHASIS, emph);
		
		return true;
	}
	
	public Texture getTexture(Resource which) {
		return textures.get(which);
	}
	
	public Image getImage(Resource which) {
		return images.get(which);
	}
	
	public ConstFont getFont(Resource which) {
		return fonts.get(which);
	}
	
	public void loadTexture(Resource which, String filename) {
		Texture tmp = new Texture();
		try {
			tmp.loadFromFile(Paths.get(filename));
		} catch (Exception e) {
			System.err.println("Error loading resource: "+e.getMessage());
			return;
		}
		
		if (textures.containsKey(which)) {
			textures.put(which, tmp);
		}
	}
}