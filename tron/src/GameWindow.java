import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**	
 * 	@author Group 2
 *  
 *  Updates the active gamewindow
 */
public class GameWindow extends JPanel implements Observer{
	public int mcSize = 3;
	private HashSet<ColorPoint> pointList;
	private LinkedList<ColorPoint> tempList;

	/**
	 * Default constructor for GameWindow
	 * 
	 * @param KeyReader key
	 */
	public GameWindow(KeyReader key){
		super(true);
		pointList = new HashSet<ColorPoint>();
		tempList = new LinkedList<ColorPoint>();
		addKeyListener(key);
	}
	
	/**
	 * Clears the pointlist
	 */
	public void init(){
		pointList.clear();

	}

	/**
	 * Draw all points from pointlist
	 * 
	 * @param Graphics g
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(ColorPoint point : pointList){
			g.setColor(point.color);
			g.fillRect(point.x - 1, point.y - 1, mcSize, mcSize);
			//		System.out.println(point);
		}while(!tempList.isEmpty()){
			pointList.add(tempList.get(0));
			tempList.remove(0);

		}
	}



	/**
	 * Handles new point from NetworkClientReceiverUDP
	 * 
	 * @param Observable o
	 * @param Object arg
	 */
	public void update(Observable o, Object arg)
	{
			if(o instanceof NetworkClientReceiverUDP && arg instanceof ColorPoint)
			{
				tempList.add((ColorPoint) arg);
				repaint();
			}
			else if(o instanceof NetworkClientReceiverUDP && arg instanceof String)
			{
				pointList.clear();
			}
	}
}


