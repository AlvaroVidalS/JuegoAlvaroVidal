package Juego;

import java.awt.Rectangle;

import javax.swing.JPanel;

public class Personaje {
	protected int x;
	protected int y;
	private String Direccion;
	private JPanel MiJP;
	
	Personaje(JPanel MiJ){
		this.x=0;
		this.y=0;
		this.MiJP=MiJ;
	}
	
	public String getDireccion() {
		return this.Direccion;
	}
	public void setDireccion(String Direccion) {
		this.Direccion =Direccion;
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
		return this.MiJP;
	}
	public Rectangle hitbox() {
		return new Rectangle(x,y,60,60);
	}
}
