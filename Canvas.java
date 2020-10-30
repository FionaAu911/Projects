//Fiona Au
//
//Email fau@u.rochester.edu
//NetID fau
//CSC 172 Lab T/R 2:00-3:15
//Project #2

package Project2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Canvas extends JComponent implements MouseListener, MouseMotionListener, KeyListener {
	
	int x; //x value of the point where you click
	int y; //y value of the point where you click
	int z;
	int a;
	String string = "";
	Color color = Color.BLACK;
	int x1;
	int x2;
	int y1;
	int y2;
	
	public Canvas() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//	public static void linePoints(int p1, int p2, int q1, int q2) {
//		x1(p1);
//	}
//	
//	public void paint(Graphics g) {
//		g.setColor(Color.BLUE);
//		int x = x1();
//		g.drawLine(x1(), y1(), x2(), y2());
//	}
//	
//	public int x1() {
//		linePoints()
//		return p1;
//	}
//	
	public int getx1() {
		return x1;
	}
	
	public int getx2() {
		return x2;
	}
	
	public int gety1() {
		return y1;
	}
	
	public int gety2() {
		return y2;
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked");
		x = e.getX();
		y = e.getY();
		Graphics g = getGraphics();
		g.setColor(color);
		g.fillOval(x - 5, y - 5, 10, 10);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
