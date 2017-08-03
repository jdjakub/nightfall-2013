import java.io.*;

class FileDatabattleBuilder implements IDatabattleBuilder {
	private String filename;
	private ISectorWriteAccess grid;
	
	public FileDatabattleBuilder(String filename) {
		System.out.println("Filename is "+filename);
		this.filename = filename;
	}
	
	public boolean build() {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = br.readLine();
			String[] dims = line.split("x");
			if (dims.length != 2) return false;
			
			int width = Integer.parseInt(dims[0]);
			int height = Integer.parseInt(dims[1]);
			
			grid = new DatabattleGrid(width, height);
			
			for (int y=0; y<height; y++) {
				line = br.readLine();
				String[] vals = line.split("\\s");
				if (vals.length != width) return false;
				for (int x=0; x<width; x++) {
					int n = Integer.parseInt(vals[x]);
					grid.set(x, y, n);
				}
			}
			for (int y=0; y<height; y++) {
				line = br.readLine();
				String[] vals = line.split("\\s");
				if (vals.length != width) return false;
				for (int x=0; x<width; x++) {
					int n = Integer.parseInt(vals[x]);
					if (n != 0) {
						grid.setSector(x, y, Sector.create(n, x, y));
					}
				}
			}
			
		} catch (IOException e) {
			System.err.println("Error: "+e.getMessage());
			e.printStackTrace();
			return false;
		} catch (NumberFormatException e) {
			System.err.println("Error: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ISectorWriteAccess getGrid() {
		return grid;
	}
}