import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import javax.swing.*;

/*	public class GameWindow
 * 	@version 1.0
 * 	@author Erik, Joel
 * 
 */
public class GameWindow extends JPanel implements ActionListener{
	private ColorPoint pointOld;
	public int mcSize = 3;
	private boolean runOnce = true;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;
	public int direction = 3;
	private HashSet<ColorPoint> pointList;
	private Timer timer = new Timer(50, this);

	public GameWindow(){
		super(true);
		pointList = new HashSet<ColorPoint>();


	}
	
	public void init(){
		pointOld = new ColorPoint(30,200,Color.BLUE);
		pointList.clear();
		addKeyListener(kl);
		direction = RIGHT;
		timer.start();
	}
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(ColorPoint point : pointList){
			g.setColor(point.color);
			g.fillRect(point.x, point.y, mcSize, mcSize);
			System.out.println(point);
		}


	}

	public void actionPerformed(ActionEvent e){
		Point point = new Point();
		if(direction == UP){
			point.y = pointOld.y - mcSize;
			point.x = pointOld.x;
		}else if(direction == DOWN){
			point.y = pointOld.y + mcSize;
			point.x = pointOld.x;
		}else if(direction == RIGHT){
			point.x = pointOld.x + mcSize;
			point.y = pointOld.y;
		}else if(direction == LEFT){
			point.x = pointOld.x - mcSize;
			point.y = pointOld.y;
		}
		if(pointList.contains(point)){
			timer.stop();
			JOptionPane.showMessageDialog(this,
					"You died.", "Game Over", JOptionPane.ERROR_MESSAGE);
			Toolkit.getDefaultToolkit().beep();
		}
		
		ColorPoint pointNew = new ColorPoint(point.x, point.y, pointOld.color);
		pointList.add(pointNew);
		pointOld = pointNew;
		repaint();

	}


	KeyListener kl = new KeyAdapter(){
		@Override
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_UP){
				if(direction != DOWN)
					direction=UP;
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
				if(direction != UP)
					direction=DOWN;
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				if(direction != RIGHT)
					direction=LEFT;
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				if(direction != LEFT)
					direction=RIGHT;
			}
		}
	};



}


