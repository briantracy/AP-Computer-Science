/*
 * Written by Brian Tracy
 * AP Computer Science
 * 
 * Graphical content for application. Renders the cow along with everything else in the background.
 */

import java.awt.*;			/* used for Color, Point, Graphics */
import javax.swing.JFrame;	/* used for JFrame, the superclass of CowFrame */

public class CowFrame extends JFrame {
	
	private final Color BISQUE = new Color(0xcdb79e);
	private final Color SKY_COLOR = new Color(0x7ec0ee);
	private final Color GROUND_COLOR = new Color(0x754c24);
	private final int SCENE_WIDTH  = 750;
	private final int SCENE_HEIGHT = 400;
	
	
	public CowFrame () {
		init();
	}
	
	public void init() {
		setSize(SCENE_WIDTH,SCENE_HEIGHT);
		setBackground(Color.WHITE);
		repaint();
	}
	
	public void paint(Graphics g) {
		drawBackGround(g);
		drawBody(g);
		
		for (int i = 175; i < 550; i += 100)		/* loop over the width of the cow, insert legs */
		{
			drawLeg(g, new Point(i, 225), BISQUE);
		}
		
		drawHead(g);
		drawSun(g);	
	}
	
	
	/**
	 * <summary> This method draws the sun </summary>
	 * @param g : The graphics on which to be drawn
	 */
	private void drawSun(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillOval(SCENE_WIDTH - 60, 0, 100, 100);	/* set sun to the top right corner */
	}
	
	
	/**
	 * <summary>This method draws the head and eyes of the cow </summary>
	 * @param g : The graphics on which to be drawn
	 */
	private void drawHead(Graphics g)
	{
		g.setColor(BISQUE);				/* Draw the head ellipse */
		g.fillOval(475, 30, 150, 100);
		g.setColor(Color.BLACK);
		g.fillOval(520, 60, 30, 40);	/* Draw the two eye ellipses */
		g.fillOval(560, 60, 30, 40);	
	}
	
	/**
	 * <summary>Draw the sky and the ground, everything but the cow </summary>
	 * @param g : the graphics on which to be drawn
	 */
	private void drawBackGround(Graphics g)
	{
		g.setColor(SKY_COLOR);
		g.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
		
		final int groundHeight = SCENE_HEIGHT / 5;  /* How far up the ground reaches, one fifth of the screen */
		
		g.setColor(GROUND_COLOR);
		g.fillRect(0, SCENE_HEIGHT - groundHeight, SCENE_WIDTH, groundHeight);	/* stroke the bottom fifth of the screen as ground */
	}
	
	
	/**
	 * <summary> This method draws the body of the cow, as a rounded rect </summary>
	 * @param g : The graphics on which to be drawn
	 */
	private void drawBody(Graphics g)
	{
		g.setColor(BISQUE);
		g.fillRoundRect(150, 75, 400, 150, 20, 20);		/* draw the body of the cow as a rounded rectangle */
	}
	
	
	/**
	 * <summary> Instead of redrawing multiple legs, encapsulate the process into one method. </summary>
	 * @param g : the graphics on which to be drawn
	 * @param position : the origin of the leg, relative to g
	 * @param color : the color of the leg 
	 */
	private void drawLeg(Graphics g, Point position, Color color)
	{
		final int width  = 20;		/* The height and width of the leg to be drawn, also the length of the hoof */
		final int length = 80;
		final int hoofLength = 15;	
		
		g.setColor(color);										/* Draw the leg */
		g.fillRect(position.x, position.y, width, length);
		
		g.setColor(Color.BLACK);											/* Draw the hoof */
		g.fillRect(position.x, position.y + length, width, hoofLength);
		
	}
}