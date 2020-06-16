package Juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

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
	Timer reloj;
	Fantasma fantasma;
	int contTiempo;
	boolean broncasD=false;
	boolean topoD=false;
	PantallaGameOver End = new PantallaGameOver();
	
	
	/**
	 * Create the frame.
	 */
	
	public MiJuego(Clip sonido) {
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
		Fantasma fantasma = new Fantasma(panel,150,150);
		pocion = new Vida(panel);
		escudo = new Escudo(panel);
		pistola = new Arma(panel);
		
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
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MiJuego.class.getResource("/Juego/FondoGrande.png")));
		panel.add(lblNewLabel_3);
		
		
		
		
		/**************************************************************/
        //Parte del codigo que cada 100 milisegundo movera a los enemigos

        reloj = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		contTiempo=(contTiempo+reloj.getDelay())%5000;
                    //Actualizamos las posiciones de los personajes
                	fantasma.getPanel().update(panel.getGraphics());
                	topo.getPanel().update(panel.getGraphics());
                    broncas.getPanel().update(panel.getGraphics());
                    player.getPanel().update(panel.getGraphics());

                    //Actualizamos al personaje player
                    ImageIcon MiImagen=new ImageIcon(player.getDireccion());
                    panel.getGraphics().drawImage(MiImagen.getImage(), player.CoordX(), player.CoordY(), panel);

                    //Actualizamos al personaje Broncas
                    broncas.movimientoBroncas();
                    ImageIcon ImagenBroncas=new ImageIcon(broncas.getDireccion());
                    panel.getGraphics().drawImage(ImagenBroncas.getImage(), broncas.CoordX(), broncas.CoordY(), panel);
                    
                    //Actualizamos al personaje Topo
                    topo.MovimientoTopo();
                    ImageIcon ImagenTopo=new ImageIcon(broncas.getDireccion());
                    panel.getGraphics().drawImage(ImagenTopo.getImage(), topo.CoordX(), topo.CoordY(), panel);
                    
                    //Actualizamos al personaje Fantasma
                    if (contTiempo==0) {
    					fantasma.setVisible();
    					Random r = new Random();
    					int valorX = r.nextInt(650);
    					int valorY = r.nextInt(460);
    					fantasma.setCoordX(valorX);
    					fantasma.setCoordY(valorY);
    					System.out.println("Cambio");
    				}
                    
                    //Actualizamos las pociones
                    ImageIcon ImgPocion= new ImageIcon("src/Juego/poti.png");
                    panel.getGraphics().drawImage(ImgPocion.getImage(), pocion.CoordX(), pocion.CoordY(), panel);
                    
                    //Actualizamos los escudos
                    ImageIcon ImgEscudo= new ImageIcon("src/Juego/escudo.png");
                    panel.getGraphics().drawImage(ImgEscudo.getImage(), escudo.CoordX(), escudo.CoordY(), panel);
                    
                    //Actualizamos las armas
                    ImageIcon ImgArma= new ImageIcon("src/Juego/espada.png");
                    panel.getGraphics().drawImage(ImgArma.getImage(), pistola.CoordX(), pistola.CoordY(), panel);
                    
                    //Linea para evitar parpadeos
                    panel.getGraphics().drawImage(null, 10, 80, null);
                    
                    
                    //Colisión con broncas
                    int hiddenBox=85;    //Variable creada para regular el tamaño de la caja oculta
                    if ((player.CoordX() >= broncas.CoordX()-hiddenBox && player.CoordX() <= broncas.CoordX()+hiddenBox) 
                            && ((player.CoordY() >= broncas.CoordY()-hiddenBox && player.CoordY() <= broncas.CoordY()+hiddenBox))){
                            
                        System.out.println("¡Player está tocando a broncas!");
                        
                        if(player.getArma()==0) {
                         	if(player.getEscudo() <= 0) {
                                player.setSalud(player.getSalud()-10);
                                lblNewLabel.setText("Salud: " + player.getSalud());
                            }
                            if(player.getEscudo()>0) {
                            	player.setEscudo(player.getEscudo()-1);
                            	lblNewLabel_1.setText("Escudo: "+player.getEscudo());
                            }
                            if(player.getSalud() <= 0) {
                            	PantallaGameOver frame = new PantallaGameOver();
            					frame.setVisible(true);
                                reloj.stop();
                                sonido.stop();
                                dispose();
                            }                    
                        }else {
                        	broncas.setCoordY(-500);
                        	broncasD=true;
                        }
                    }
                    
                    int hiddenBoxTopo=85;
                    //Colision con topo
                    if ((player.CoordX() >= topo.CoordX()-hiddenBoxTopo && player.CoordX() <= topo.CoordX()+hiddenBoxTopo) 
                            && ((player.CoordY() >= topo.CoordY()-hiddenBoxTopo && player.CoordY() <= topo.CoordY()+hiddenBoxTopo))){
                            
                        System.out.println("¡Player está tocando a topo!");
                        if(player.getArma()==0) {
                            if(player.getEscudo() <= 0) {
                                player.setSalud(player.getSalud()-10);
                                lblNewLabel.setText("Salud: " + player.getSalud());
                            }
                            if(player.getEscudo()>0) {
                            	player.setEscudo(player.getEscudo()-1);
                            	lblNewLabel_1.setText("Escudo: "+player.getEscudo());
                            }
                            if(player.getSalud() <= 0) {
                            	PantallaGameOver frame = new PantallaGameOver();
            					frame.setVisible(true);
                                reloj.stop();
                                sonido.stop();
                                dispose();
                            }
                        }else {
                        	topo.setCoordX(-500);
                        	topoD=true;
                        }
                     }
                    
                    //Colision con ghost 
                    if (fantasma.getVisible()==1) {
    					fantasma.movimientoFantasma();
    					ImageIcon ImagenFantasma = new ImageIcon(fantasma.getDireccion());
    					panel.getGraphics().drawImage(ImagenFantasma.getImage(), fantasma.CoordX(),fantasma.CoordY(), panel);
    					 if ((player.CoordX() >= fantasma.CoordX()-hiddenBoxTopo && player.CoordX() <= fantasma.CoordX()+hiddenBoxTopo) 
    		                        && ((player.CoordY() >= fantasma.CoordY()-hiddenBoxTopo && player.CoordY() <= fantasma.CoordY()+hiddenBoxTopo))){
    		                        
    		                    System.out.println("¡Player está tocando a fantasma!");
    		            
    		                        if(player.getEscudo() <= 0) {
    		                            player.setSalud(player.getSalud()-10);
    		                            lblNewLabel.setText("Salud: " + player.getSalud());
    		                        }
    		                        if(player.getEscudo()>0) {
    		                        	player.setEscudo(player.getEscudo()-1);
    		                        	lblNewLabel_1.setText("Escudo: "+player.getEscudo());
    		                        }
    		                        if(player.getSalud() <= 0) {
    		                        	PantallaGameOver frame = new PantallaGameOver();
    		        					frame.setVisible(true);
    		                            reloj.stop();
    		                            sonido.stop();
    		                            dispose();
    		                        }
    		                   
    					 }
                    }
    				
                    
                    //Colision con pocion
                    if ((player.CoordX() >= pocion.CoordX()-hiddenBox && player.CoordX() <= pocion.CoordX()+hiddenBox) 
                            && ((player.CoordY() >= pocion.CoordY()-hiddenBox && player.CoordY() <= pocion.CoordY()+hiddenBox))){
                            
                        System.out.println("¡Player está tocando a pocion!");
                        
                            if(player.getSalud() < 100) {
                                player.setSalud(player.getSalud()+10);
                                lblNewLabel.setText("Salud: " + player.getSalud());
                            }
                            if(player.getSalud()>=100) {
                            	player.setSalud(100);
            				}
                            pocion.setCoordX(-100);
                    }
                    //Colision con Escudo
                    if ((player.CoordX() >= escudo.CoordX()-hiddenBox && player.CoordX() <= escudo.CoordX()+hiddenBox) 
                            && ((player.CoordY() >= escudo.CoordY()-hiddenBox && player.CoordY() <= escudo.CoordY()+hiddenBox))){
                            
                        System.out.println("¡Player está tocando a escudo!");
                        
                            if(player.getEscudo() < 50) {
                                player.setEscudo(player.getEscudo()+50);
                                lblNewLabel_1.setText("Escudo: " + player.getEscudo());
                            }
                            
                            escudo.setCoordX(-100);
                    }
                    
                    //Colision con Pistola
                    if ((player.CoordX() >= pistola.CoordX()-hiddenBox && player.CoordX() <= pistola.CoordX()+hiddenBox) 
                            && ((player.CoordY() >= pistola.CoordY()-hiddenBox && player.CoordY() <= pistola.CoordY()+hiddenBox))){
                            
                        
                    	System.out.println("¡Player está tocando a pistola!");
                        if(player.getArma()==0) {
                        	player.setArma(1);
                            lblNewLabel_2.setText("Arma: " + player.getArma());
                            pistola.setCoordX(-100);
                        }
                    }
                    if(broncasD==true && topoD==true) {
                    	dispose();
                    	sonido.stop();
                    	Victoria frame = new Victoria();
    					frame.setVisible(true);
                    }
            	}catch(Exception ex) {
            		
            	}
            	
            }
                	
        });
        reloj.start();

		
        
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			try {
				
			
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
				//Easter egg para ganar la partida 
				case KeyEvent.VK_T:
					PantallaGameOver frame = new PantallaGameOver();
					frame.setVisible(true);
					pistola.setCoordX(-100);
					broncas.setCoordY(-500);
					topo.setCoordX(-500);
					escudo.setCoordX(-100);
					pocion.setCoordX(-100);
					sonido.stop();
					dispose();
					
					
				}
			}catch(Exception ex) {
				
			}
				
			}
			
		});
		
		
	}
	
}
