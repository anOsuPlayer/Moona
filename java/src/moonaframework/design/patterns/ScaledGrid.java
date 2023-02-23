package moonaframework.design.patterns;

public class ScaledGrid extends Grid {

	private final int scale;
	
	
	
	public ScaledGrid(int width, int height, int scale) {
		super(width, height);
		this.scale = scale;
	}
	public ScaledGrid(int scale) {
		super(Grid.getDefaultWidth(), Grid.getDefaultHeight());
		this.scale = scale;
	}
	public ScaledGrid(Grid base, int scale) {
		super(base.getWidth(), base.getHeight());
		this.scale = scale;
	}
}
