package Juego;

import javax.swing.JPanel;

public class Topo extends Personaje{
	private String Sentido;
	
	Topo(JPanel MiJ, int x, int y){
		super(MiJ);
		this.x=x;
		this.y=y;
		this.Sentido="ABAJO";
	}
	public void MovimientoTopo() {
		if(this.y<0) {
			this.Sentido="ABAJO";
			this.y=this.y+20;
			this.setDireccion("src/juego/Broncas_abajo.png");
		}else if(this.y>this.getPanel().getHeight()-10) {
			this.Sentido="ARRIBA";
			this.y=this.y-10;
			this.setDireccion("src/juego/Broncas_Arriba.png");
		}
		else if(this.Sentido.equals("ABAJO")) {
			this.y=this.y+20;
			this.setDireccion("src/juego/Broncas_abajo.png");
		}
		else if(this.Sentido.equals("ARRIBA")) {
			this.y=this.y-10;
			this.setDireccion("src/juego/Broncas_Arriba.png");
		}
	}

}
