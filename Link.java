package Juego;

import javax.swing.JPanel;

public class Link extends Personaje{
	
	protected int vida;
	protected int escudo;
	protected int arma;
	
	Link(JPanel MiJ, int s, int e, int a){
		super(MiJ);
		this.vida=s;
		this.escudo=e;
		this.arma=a;
	}
	

	public int getSalud() {
		return vida;
	}

	public void setSalud(int vida) {
		this.vida = vida;
	}

	public int getEscudo() {
		return escudo;
	}

	public void setEscudo(int escudo) {
		this.escudo = escudo;
	}

	public int getArma() {
		return arma;
	}

	public void setArma(int arma) {
		this.arma = arma;
	}
	

}
