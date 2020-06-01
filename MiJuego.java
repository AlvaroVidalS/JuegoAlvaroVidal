package Juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MiJuego extends JFrame {

	private JPanel contentPane;
	Vida pocion;
	Escudo escudo; 
	Arma pistola;
	Link player;
	Broncas broncas;
	Topo topo;
	
	boolean collisionV() {
		if(player.hitbox().intersects(pocion.hitbox())) {
			return true;
		}else {
			return false;
		}	
	}
	boolean collisionB() {
		if(broncas.hitbox().intersects(player.hitbox())) {
			return true;
		}else {
			return false;
		}	
	}
	boolean collisionT() {
		if(topo.hitbox().intersects(player.hitbox())) {
			return true;
		}else {
			return false;
		}	
	}

	/**
	 * Create the frame.
	 */
	
	public MiJuego() {
		setTitle("Mini Juego");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		
		player = new Link(panel,100,0,0);
		Broncas broncas = new Broncas(panel,80,130);
		Topo topo = new Topo(panel,100,100);
		//Ghost fant = new Ghost(panel,150,150);
		pocion = new Vida(panel);
		escudo = new Escudo(panel);
		pistola = new Arma(panel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MiJuego.class.getResource("/Juego/FondoGrande.png")));
		panel.add(lblNewLabel_3);
		
		
		
		
		Timer reloj = new Timer(100, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				topo.getPanel().update(panel.getGraphics());
				broncas.getPanel().update(panel.getGraphics());
				player.getPanel().update(panel.getGraphics());
				pocion.getPanel().update(panel.getGraphics());
				escudo.getPanel().update(panel.getGraphics());
				pistola.getPanel().update(panel.getGraphics());
				
				ImageIcon MiImagen=new ImageIcon(player.getDireccion());
				panel.getGraphics().drawImage(MiImagen.getImage(), player.CoordX(), player.CoordY(), panel);
				
				broncas.movimientoBroncas();
				ImageIcon ImagenBroncas = new ImageIcon(broncas.getDireccion());
				panel.getGraphics().drawImage(ImagenBroncas.getImage(), broncas.CoordX(), broncas.CoordY(), panel);
				
				topo.MovimientoTopo();
				ImageIcon ImagenTopo = new ImageIcon(topo.getDireccion());
				panel.getGraphics().drawImage(ImagenTopo.getImage(), topo.CoordX(), topo.CoordY(), panel);
				
				
				ImageIcon poti=new ImageIcon("src/Juego/poti.jpg");
				panel.getGraphics().drawImage(poti.getImage(), pocion.CoordX(), pocion.CoordY(), panel);
				panel.getGraphics().drawImage(null, 10, 80,null);
				
				
				ImageIcon escud=new ImageIcon("src/Juego/escudo.jpg");
				panel.getGraphics().drawImage(escud.getImage(), escudo.CoordX(), escudo.CoordY(), panel);
				panel.getGraphics().drawImage(null, 10, 80,null);
				
				
				ImageIcon pistol=new ImageIcon("src/Juego/pistol.jpg");
				panel.getGraphics().drawImage(pistol.getImage(), pistola.CoordX(), pistola.CoordY(), panel);
				panel.getGraphics().drawImage(null, 10, 80,null);
				
				if(collisionV()==true) {
					
					player.setSalud(player.getSalud()+10);
					System.out.println(player.getSalud());
				}
			}
		});
		reloj.start();
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("Vida: "+player.getSalud());
		
		JLabel lblNewLabel_1 = new JLabel("Escudo: "+player.getEscudo());
		
		JLabel lblNewLabel_2 = new JLabel("Arma: "+player.getArma());
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addContainerGap(162, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				
				System.out.println("x-->"+player.CoordX()+",y-->"+player.CoordY());
				System.out.println(panel.getWidth());
				System.out.println(panel.getHeight());
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(player.CoordX()>0) {
						player.setCoordX(player.CoordX()-10);
						player.setDireccion("src/juego/izquierda.png");
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(player.CoordX()<panel.getWidth()-100) {
						player.setCoordX(player.CoordX()+10);
						player.setDireccion("src/juego/derecha.png");
					}
					break;
				case KeyEvent.VK_UP:
					if(player.CoordY()>0) {
						player.setCoordY(player.CoordY()-10);
						player.setDireccion("src/juego/Arriba.png");
					}
						
					break;
				case KeyEvent.VK_DOWN:
					if(player.CoordY()<(panel.getWidth()-200)) {
						player.setCoordY(player.CoordY()+10);
						player.setDireccion("src/juego/Abajo.png");
					}
					
					break;
				}
				
			}
			
		});
		
		
	}
	
}
