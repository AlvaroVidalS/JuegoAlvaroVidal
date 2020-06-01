package Juego;

import java.awt.Rectangle;

import javax.swing.JPanel;

public class Cosas {
	protected int x;
	protected int y;
	private JPanel MiOb;
	
	Cosas(JPanel MiO){
		this.x=(int)(Math.random()*700);
		this.y=(int)(Math.random()*500);
		this.MiOb=MiO;
	}
	
	public void setCoordX(int x) {
		this.x = x;
	}

	public void setCoordY(int y) {
		this.y = y;
	}

	public int CoordX() {
		return this.x;
	}
	public int CoordY() {
		return this.y;
	}
	public JPanel getPanel() {
		return this.MiOb;
	}
	public Rectangle hitbox() {
		return new Rectangle(x,y,60,60);
	}
	

}
