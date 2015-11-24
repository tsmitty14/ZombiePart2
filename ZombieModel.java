package zombie;

import java.awt.Color;
import java.util.Random;

public class ZombieModel {
	
	private int tempx;
	private int tempy;
	
	private final Color[][] matrix;
	private final int width;
	private final int height;
	private final int dotSize;
	private Random randnum = new Random();
	private int x = 0;
	private double rcky;
	private Color temp;
	
	public ZombieModel(int widthArg, int heightArg, int dotSizeArg) {
		width = widthArg;
		height = heightArg;
		dotSize = dotSizeArg;
		matrix = new Color[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = Color.BLACK;
			}
		}
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDotSize() { return dotSize; }
	public Color getColor(int x, int y) { return matrix[x][y]; }
	public void setColor(int x, int y, Color color) { matrix[x][y] = color; }
	
	public void initialize() {
		initializeRiver();
		initializeRocks();
		initializeTree();
		initializeFire();
	}
	
	
	public void initializeRiver()
	{
		int size = randnum.nextInt(width - 5);
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < height; j++)
			{
				this.setColor(size, j, Color.BLUE);
			}
				size++;
		}
	}
	
	public void initializeFire()
	{
		for(int i = 0; i < 60; i++){
		resetrand(0);
		while(getColor(tempx, tempy) != Color.BLACK) resetrand(0);
		setColor(tempx, tempy, Color.ORANGE);
		}
	}
	
	public void initializeTree()
	{
	for(int i = 0; i < 40; i++) {
		tempx = randnum.nextInt(width - 2) + 1;		
		tempy = randnum.nextInt(height - 2) + 1;
		x = 0;
		while(x == 0) {
			if(this.getColor(tempx, tempy) == Color.BLACK && this.getColor(tempx, tempy+1) == Color.BLACK && this.getColor(tempx, tempy-1) == Color.BLACK && this.getColor(tempx+1, tempy) == Color.BLACK && this.getColor(tempx-1, tempy) == Color.BLACK) {
				x = 1;
			} 
			else {
				resetrand(2);
				tempx++;
				tempy++;
			}
		}
		this.setColor(tempx, tempy, Color.GREEN);
		this.setColor(tempx+1, tempy, Color.GREEN);
		this.setColor(tempx-1, tempy, Color.GREEN);
		this.setColor(tempx, tempy+1, Color.GREEN);
		this.setColor(tempx, tempy-1, Color.GREEN);
	}
}
	public void resetrand(int x)
	{
		tempx = randnum.nextInt(width - x) ;
		tempy = randnum.nextInt(height -x) ;
	}
	
	private void initializeRocks()
	{
		for(int a = 0; a < 6;)
		{
			x = 1;
			resetrand(5);
			int rockwidth = tempx;
			int rockheight = tempy;
			int diameter = randnum.nextInt(4) + 4;
			for(int i = 0; i < width; i++)
			{
				for (int j = 0; j < height; j++)
				{
					double rcky2 = Math.sqrt((i- rockwidth)*(i- rockwidth) + (j - rockheight)*(j - rockheight));
					rcky = rcky2;
					if (rcky < diameter && matrix[i][j] == Color.GRAY) x = 0;
				}
			}
			if (x == 1)
			{
				for(int i = 0; i < width; i++)
				{
					for (int j = 0; j < height; j++)
					{
					double rcky2 = Math.sqrt((i- rockwidth)*(i- rockwidth) + (j - rockheight)*(j - rockheight));
					if (rcky2 < diameter && matrix[i][j]  == Color.BLACK)
					matrix[i][j]=Color.GRAY;
	
					}
				}
				a++;
			}
		}
	}
	public void update() {
	}
}