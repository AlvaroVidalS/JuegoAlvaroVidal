package Juego;

import javax.swing.JPanel;

public class Ghost extends Personaje{
	Ghost(JPanel MiJ, int x, int y){
		super(MiJ);
		this.x=x;
		this.y=y;
	}
	
	public void MovimientoGhost() {
		int posX=(int)(Math.random()*700);
		this.x=posX;
		int posY=(int)(Math.random()*500);
		this.y=posY;
	}
	
	
}
