package zombie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class ZombieController implements MouseListener {

	private final ZombieModel model;
	private final ZombieView view;
	private final int delay;
	private Human[] Guy = new Human[31];
	public ZombieController(ZombieModel modelArg, ZombieView viewArg, int sleepTimeArg) {
		model = modelArg;
		view = viewArg;
		delay = sleepTimeArg;
		model.initialize();
		for(int i = 0; i < 31; i++){
/*		Human Dood */ Guy[i] = new Human(model);}
		//Guy[0] = Dood;
		Guy[0].setAlphaZombie();
	}

	public void beginSimulation() {
		
		view.draw();
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.update();
				view.draw();
				for(int i = 0; i < 30; i ++)
				{
				Guy[i].update();
				}
			}
		};
		new Timer(delay, taskPerformer).start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	    if(e.getY() < 175) Guy[0].setDirection(Direction.NORTH);
		if(e.getY() > 175 && e.getY() < 425 && e.getX() > 600) Guy[0].setDirection(Direction.EAST);
	    if(e.getY() > 175 && e.getY() < 425 && e.getX() < 200) Guy[0].setDirection(Direction.WEST);
		if(e.getY() > 425) Guy[0].setDirection(Direction.SOUTH);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}