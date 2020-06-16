package Juego;

import javax.swing.JPanel;

public class Cosas {
	protected int x;
	protected int y;
	private JPanel MiOb;
	
	Cosas(JPanel MiO){
		this.x=(int)(Math.random()*600);
		this.y=(int)(Math.random()*300);
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
	
	

}
