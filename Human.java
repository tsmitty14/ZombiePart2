package zombie;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

//Run check doesnt work.  Human Check Doesnt work
public class Human {
	private ZombieModel Mod;
	private int width;
	private int height;
	private int x = 0;
	private Direction direction;
	private Random randnum = new Random();
	private boolean TurnAround = false;
	private boolean isRunning = false;
	private int rest = 0;
	private boolean Zombie = false;
	private Color temp;
	private Color zom;
	private Color temp2 = Color.BLACK;
	private boolean AlphaZombie = false;
	private Direction Dtemp;
	
	
	public void setAlphaZombie()
	{
		AlphaZombie = true;
	}
	
	public Human(ZombieModel model)
	{
		Mod = model;
		getParams();
		x = 0;
		while(true)
		{
			if(Mod.getColor(width, height) == Color.BLACK)
				break;
			else getParams();
		}
			Mod.setColor(width, height, Color.WHITE);
			direction = Direction.NORTH;
	}
	public void getParams()
	{
		width = randnum.nextInt(Mod.getWidth());
		height = randnum.nextInt(Mod.getHeight());
	}
	public void setDirection(Direction x) {
		direction = x;
	}
	
	public void DirectionLeft(Direction x)
	{
		switch(x)
		{
				case NORTH: direction = Direction.WEST;
						break;
				case SOUTH: direction = Direction.EAST;
						break;
				case WEST:	direction = Direction.SOUTH;
						break;
				case EAST:	direction = Direction.NORTH;
						break;
		}
	}
	
	public void DirectionRight(Direction x)
	{
		switch(x)
		{
				case NORTH: direction = Direction.EAST;
						break;
				case SOUTH: direction = Direction.WEST;
						break;
				case WEST:	direction = Direction.NORTH;
						break;
				case EAST:	direction = Direction.SOUTH;
						break;
		}
	}
	
	public void DirectionTurn(Direction x)
	{
		switch(x)
		{
				case NORTH: direction = Direction.SOUTH;
						break;
				case SOUTH: direction = Direction.NORTH;
						break;
				case WEST:	direction = Direction.EAST;
						break;
				case EAST:	direction = Direction.WEST;
						break;
		}
	}
	
	public void findDirection()
	{
		int num = randnum.nextInt(100);
		if(num >= 0 && num < 10) DirectionLeft(direction);
		if(num >= 10 && num <20) DirectionRight(direction);
		if(num >= 20 && num < 25)DirectionTurn(direction);
	}
	
	public void findDirectionRunning()
	{
		int num = randnum.nextInt(100);
		if(num >= 0 && num < 40) DirectionLeft(direction);
		if(num >= 40 && num <80) DirectionRight(direction);
		if(num >= 80 && num < 90)DirectionTurn(direction);
	}
	
	public void setZombie()
	{
		Zombie = true;
	}
	
	public void setRun()
	{
		isRunning = true;
	}
	public void checkZombieSpace()
	{
		if(AlphaZombie == true || Zombie == true) return;
		if(height > 1) { if(Mod.getColor(width, height -1 ) == Color.RED  || Mod.getColor(width, height -1 ) == Color.PINK) setZombie();}  //move this to inside the for loop?
		if(height < Mod.getHeight() - 1) {if(Mod.getColor(width, height +1 ) == Color.RED || Mod.getColor(width, height +1 ) == Color.PINK) setZombie(); }
		if(width> 1){ if(Mod.getColor(width - 1, height) == Color.RED || Mod.getColor(width - 1, height) == Color.PINK) setZombie();}
		if(width < Mod.getWidth() - 1) {if(Mod.getColor(width + 1, height) == Color.RED || Mod.getColor(width + 1, height) == Color.PINK) setZombie();}
	}
	public void checkZombieToRun(Direction x)
	{
		switch(x)
		{
				case NORTH:
					for(int i = 0; i <= 10; i++)
					{
						if(height - i <= 0) break;
						//if(Mod.getColor(width, height -i) != Color.BLACK  || Mod.getColor(width, height -i) != Color.RED) return;
						if(Mod.getColor(width, height -i ) == Color.RED) {
							setRun();			
						rest = 7;
						return;
						}
					}
					//checkZombieSpace();
//					if(Mod.getColor(width, height -1 ) == Color.RED) Zombie = true;  //move this to inside the for loop?

						break;
				case SOUTH: 
					for(int i = 0; i <= 10; i++)
					{
						if(height + i >= Mod.getHeight()) break;
						//if(Mod.getColor(width, height +i) != Color.BLACK  || Mod.getColor(width, height +i) != Color.RED) return;
						if(Mod.getColor(width, height + i ) == Color.RED) {
							setRun();			
						rest = 7;
						return;
						}
					}
					//checkZombieSpace();

//					if(Mod.getColor(width, height +1 ) == Color.RED) Zombie = true;
						break;
				case WEST:
					for(int i = 0; i <= 10; i++)
					{
						if(width - i <= 0) break;
						//if(Mod.getColor(width - i, height) != Color.BLACK  || Mod.getColor(width - i, height) != Color.RED) return;
						if(Mod.getColor(width - i, height) == Color.RED)  {
							setRun();			
						rest = 7;
						return;
						}
					}
					//checkZombieSpace();

				//	if(Mod.getColor(width - 1, height) == Color.RED) Zombie = true;
						break;
				case EAST:	
					for(int i = 0; i <= 10; i++)
					{
						
						if(width + i >= Mod.getWidth()) break;
						//if(Mod.getColor(width + i, height) != Color.BLACK  || Mod.getColor(width + i, height) != Color.RED) return;
						if(Mod.getColor(width + i, height) == Color.RED)  {
							setRun();			
						rest = 7;
						return;
						}
					}
					//checkZombieSpace();

					//if(Mod.getColor(width + 1, height) == Color.RED) Zombie = true;
						break;
		}
	}
	public void RunState(Direction x)
	{
		if(rest <= 2) return;
		if(rest == 7 || TurnAround == true){
		DirectionTurn(direction);
		}
		else findDirectionRunning();
		if(direction == Direction.NORTH && height > 0)//  && Mod.getColor(width, height -1) == Color.BLACK)
		{
			rest--;
			if(Mod.getColor(width, height -1) == Color.BLACK){
			Mod.setColor(width, height - 1, Color.MAGENTA);
			Mod.setColor(width, height, Color.BLACK);
			height--;
			TurnAround = false;
			//findDirectionRunning();
			
			return;
			}
			TurnAround = true;
		//findDirectionRunning();
		}
		else if(direction == Direction.SOUTH  && height <= Mod.getHeight() - 2)//&& Mod.getColor(width,  height + 1) == Color.BLACK)
		{
			rest--;
			if(Mod.getColor(width,  height + 1) == Color.BLACK){
			Mod.setColor(width, height + 1, Color.MAGENTA);
			Mod.setColor(width, height, Color.BLACK);
			height++;
			TurnAround = false;
			//findDirectionRunning();
			
			return;

			}
			TurnAround = true;
			//findDirectionRunning();
		}
		else if(direction == Direction.EAST && width <= Mod.getWidth() - 2)// && Mod.getColor(width + 1,  height) == Color.BLACK)
		{
			rest--;
			if(Mod.getColor(width + 1,  height) == Color.BLACK){
			Mod.setColor(width + 1, height, Color.MAGENTA);
			Mod.setColor(width, height, Color.BLACK);
			width++;
			TurnAround = false;
			//findDirectionRunning();
			return;

			}
			TurnAround = true;
		//findDirectionRunning();
		}
		else if(direction == Direction.WEST && width > 0)// && Mod.getColor(width - 1,  height) == Color.BLACK)
		{
			rest--;
			if(Mod.getColor(width - 1,  height) == Color.BLACK){
			Mod.setColor(width - 1, height, Color.MAGENTA);
			Mod.setColor(width, height, Color.BLACK);
			width--;
			TurnAround = false;
			//indDirectionRunning();
			
			return;

			}
			TurnAround = true;
			//findDirectionRunning();
		}
		rest--;
		return;
	}
	public void findDirectionZombie()
	{
		int num = randnum.nextInt(100);
		if(num >= 0 && num < 20) DirectionLeft(direction);
		if(num >= 20 && num <40) DirectionRight(direction);
		if(num >= 40 && num < 50)DirectionTurn(direction);
		//if(num >= 50) Dtemp = direction;
		
	} 
	public boolean HumanIsClose(Direction x)
	{
		boolean found = false;
		switch(x)
		{
				case NORTH:
					for(int i = 0; i <= 10; i++)
					{
						if(height - i <= 0) break;
						//if(Mod.getColor(width, height -i) != Color.BLACK  || Mod.getColor(width, height -i) != Color.WHITE) found = false;
						if(Mod.getColor(width, height -i ) == Color.WHITE){
							Dtemp = Direction.NORTH;
							return true;			
						}
					}

						break;
				case SOUTH: 
					for(int i = 0; i <= 10; i++)
					{
						if(height + i >= Mod.getHeight()) break;
						//if(Mod.getColor(width, height +i) != Color.BLACK  || Mod.getColor(width, height +i) != Color.WHITE) found = false;
						if(Mod.getColor(width, height +i ) == Color.WHITE) {
							Dtemp = Direction.SOUTH;
							return true;	
						}
					}
						break;
				case WEST:
					for(int i = 0; i <= 10; i++)
					{
						if(width - i <= 0) break;
						//if(Mod.getColor(width - i, height) != Color.BLACK  || Mod.getColor(width - i, height) != Color.WHITE) found = false;
						if(Mod.getColor(width - i, height) == Color.WHITE){
							Dtemp = Direction.WEST;
							return true;	
						}
				
					}
						break;
				case EAST:	
					for(int i = 0; i <= 10; i++)
					{
						if(width + i >= Mod.getWidth()) break;
						//if(Mod.getColor(width + i, height) != Color.BLACK  || Mod.getColor(width + i, height) != Color.WHITE) found =  false;
						if(Mod.getColor(width + i, height) == Color.WHITE){
							Dtemp = Direction.EAST;
							return true;	
						}
					
					}
						break;
		}
		return found;
	}
	
	public void mousePressed(MouseEvent e) {
	    if(e.getY() < 175) direction = (Direction.NORTH);
		if(e.getY() > 175 && e.getY() < 425 && e.getX() > 600) direction = (Direction.EAST);
	    if(e.getY() > 175 && e.getY() < 425 && e.getX() < 200) direction = (Direction.WEST);
		if(e.getY() > 425) direction = (Direction.SOUTH); 
	}
	public void ZombieUpdate()
	{
		if(Zombie == true)
			zom = Color.RED;
		else zom = Color.PINK;
		Mod.setColor(width, height, zom);
		if(HumanIsClose(direction) != true)
		{
			findDirectionZombie();
		}
		else direction = Dtemp;
		if(TurnAround = true)
			DirectionTurn(direction);
		//else findDirectionZombie();
		//if(AlphaZombie == true) mousePressed();
		if(direction == Direction.NORTH && height > 0)//  && Mod.getColor(width, height -1) == Color.BLACK)
		{
			if(Mod.getColor(width, height -1) == Color.BLACK  || Mod.getColor(width, height -1) == Color.BLUE){
				temp = Mod.getColor(width, height - 1);
				Mod.setColor(width, height, temp2);
				Mod.setColor(width, height - 1, zom);
				temp2 = temp;
				height--;
				TurnAround = false;
				return;
			}
			TurnAround = true;
		}
		else if(direction == Direction.SOUTH  && height <= Mod.getHeight() - 2)//&& Mod.getColor(width,  height + 1) == Color.BLACK)
		{
			if(Mod.getColor(width, height +1) == Color.BLACK  || Mod.getColor(width, height + 1) == Color.BLUE){
				temp = Mod.getColor(width, height + 1);
				Mod.setColor(width, height, temp2); 
				Mod.setColor(width, height + 1, zom);
				temp2 = temp;
			height++;
			
			TurnAround = false;
			return;
			}
			TurnAround = true;
			
		}
		else if(direction == Direction.EAST && width <= Mod.getWidth() - 2)// && Mod.getColor(width + 1,  height) == Color.BLACK)
		{
			if(Mod.getColor(width + 1, height) == Color.BLACK  || Mod.getColor(width + 1, height) == Color.BLUE){
				temp = Mod.getColor(width + 1, height);
				Mod.setColor(width, height, temp2);
				Mod.setColor(width + 1, height, zom);
				temp2 = temp;
			width++;
			TurnAround = false;
			return;
			}
			TurnAround = true;
		}
		else if(direction == Direction.WEST && width > 0)// && Mod.getColor(width - 1,  height) == Color.BLACK)
		{
			if(Mod.getColor(width - 1, height) == Color.BLACK  || Mod.getColor(width - 1, height) == Color.BLUE){
				temp = Mod.getColor(width - 1, height);
				Mod.setColor(width, height, temp2);
				Mod.setColor(width - 1, height, zom);
				temp2 = temp;
			width--;
			TurnAround = false;
			return;
			}
			TurnAround = true;
		}
	}
	//Put the update thing into a method and call it within update 2 times in order to do the run.  use global variable to check turns.
	public void update()
	{
		if(rest == 0) isRunning = false;
		checkZombieSpace();
		if(Zombie == true || AlphaZombie == true)
		{
			ZombieUpdate();
			return;
		}
		if(rest == 0)
		checkZombieToRun(direction);
		if(isRunning == true && rest > 2){
		RunState(direction);
		RunState(direction);
		System.out.println(rest);
		return;
		}
		if(isRunning == true && rest <= 2 && rest > 0)
		{
			rest--;
			return;
		}
		
		else if(TurnAround == false)
		findDirection();
		else if(TurnAround == true)
		DirectionTurn(direction);
		
		if(direction == Direction.NORTH && height > 0)//  && Mod.getColor(width, height -1) == Color.BLACK)
		{
			if(Mod.getColor(width, height -1) == Color.BLACK){
			Mod.setColor(width, height - 1, Color.WHITE);
			Mod.setColor(width, height, Color.BLACK);
			height--;
			TurnAround = false;
			return;
			}
			TurnAround = true;
		}
		if(direction == Direction.SOUTH  && height <= Mod.getHeight() - 2)//&& Mod.getColor(width,  height + 1) == Color.BLACK)
		{
			if(Mod.getColor(width,  height + 1) == Color.BLACK){
			Mod.setColor(width, height + 1, Color.WHITE);
			Mod.setColor(width, height, Color.BLACK);
			height++;
			TurnAround = false;
			return;
			}
			TurnAround = true;
		}
		else if(direction == Direction.EAST && width <= Mod.getWidth() - 2)// && Mod.getColor(width + 1,  height) == Color.BLACK)
		{
			if(Mod.getColor(width + 1,  height) == Color.BLACK){
			Mod.setColor(width + 1, height, Color.WHITE);
			Mod.setColor(width, height, Color.BLACK);
			width++;
			TurnAround = false;
			return;
			}
			TurnAround = true;
		}
		else if(direction == Direction.WEST && width > 0)// && Mod.getColor(width - 1,  height) == Color.BLACK)
		{
			if(Mod.getColor(width - 1,  height) == Color.BLACK){
			Mod.setColor(width - 1, height, Color.WHITE);
			Mod.setColor(width, height, Color.BLACK);
			width--;
			TurnAround = false;
			return;
			}
			TurnAround = true;
		}
		checkZombieSpace();
		checkZombieToRun(direction);
	}
}
