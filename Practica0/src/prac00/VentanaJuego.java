package prac00;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;



public class VentanaJuego extends JFrame{

	private static final long serialVersionUID = 1L;
	JPanel pnlMain;
	CocheJuego coche1;
	MiHilo hilo = null ;
	
	public VentanaJuego() {
		
        this.setDefaultCloseOperation(2);
        this.pnlMain = new JPanel();
        JPanel pnlBotones= new JPanel();
        JButton btnAcelerar = new JButton("Acelera");
        JButton btnFrenar = new JButton("Frena");
        JButton btnGiraIzq = new JButton("Gira Izq.");
        JButton btnGiraDer = new JButton("Gira Der.");
        this.pnlMain.setLayout(null);
        this.pnlMain.setBackground(Color.WHITE);
        this.add((Component)this.pnlMain, "Center");
        pnlBotones.add(btnAcelerar);
        pnlBotones.add(btnFrenar);
        pnlBotones.add(btnGiraIzq);
        pnlBotones.add(btnGiraDer);
        this.add((Component)pnlBotones, "South");
        this.setSize(1000, 500);
		
        btnAcelerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	                VentanaJuego.this.coche1.acelera(5.0);
	                System.out.println("Velocidad: " + VentanaJuego.this.coche1.getMiVelocidad());
	            }
		});
        
        btnFrenar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	                 VentanaJuego.this.coche1.acelera(-5.0);
	                System.out.println("Velocidad: " + VentanaJuego.this.coche1.getMiVelocidad());
	            }
		});
        
        btnGiraDer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	                VentanaJuego.this.coche1.gira(-10);
	                System.out.println("Direccion: " + VentanaJuego.this.coche1.getMiDireccionActual());
	            }
		});
        
        btnGiraIzq.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	             VentanaJuego.this.coche1.gira(10);
	             System.out.println("Direccion: " + VentanaJuego.this.coche1.getMiDireccionActual());
	        }
		});
        
        this.pnlMain.setFocusable(true);
        this.pnlMain.requestFocus();
        this.pnlMain.addFocusListener(new FocusAdapter(){

            @Override
            public void focusLost(FocusEvent e) {
                VentanaJuego.this.pnlMain.requestFocus();
            }
        });
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                if (VentanaJuego.this.hilo != null) {
                    VentanaJuego.this.hilo.acaba();
                }
            }
        });
        
	}
	
	public void crearCoche(int x, int y) {
		this.coche1 = new CocheJuego();
		this.coche1.setPosicion(x, y);
		this.pnlMain.add(this.coche1.getGrafico());
		
	}
	
	 public static void main(String[] args) {
	        VentanaJuego miVentana = new VentanaJuego();
	        miVentana.crearCoche(150,100);
	        miVentana.setVisible(true);
	        miVentana.coche1.setPiloto("Carlos Sainz");
	        miVentana.hilo = miVentana.new MiHilo();
	        Thread nuevoHilo = new Thread(miVentana.hilo);
	        nuevoHilo.start();

	    }

	 class MiHilo implements Runnable {
	        boolean sigo = true;

	        MiHilo() {
	        }

	        @Override
	        public void run() {
	            while (this.sigo) {
	                double dir = 0;
	                VentanaJuego.this.coche1.mueve(0.01);
	                if (VentanaJuego.this.coche1.getPosX() < -50.0 || VentanaJuego.this.coche1.getPosX() > (double)(VentanaJuego.this.pnlMain.getWidth() - 50)) {
	                    System.out.println("Choca X");
	                    dir = VentanaJuego.this.coche1.getMiDireccionActual();
	                    dir = 180.0 - dir;
	                    if (dir < 0.0) {
	                        dir += 360.0;
	                    }
	                    VentanaJuego.this.coche1.setMiDireccionActual(dir);
	                }
	                if (VentanaJuego.this.coche1.getPosY() < -50.0 || VentanaJuego.this.coche1.getPosY() > (double)(VentanaJuego.this.pnlMain.getHeight() - 50)) {
	                    System.out.println("Choca Y");
	                    dir = VentanaJuego.this.coche1.getMiDireccionActual();
	                    dir = 360.0 - dir;
	                    VentanaJuego.this.coche1.setMiDireccionActual(dir);
	                }
	                try {
	                    Thread.sleep(10L);
	                }
	                catch (Exception exception) {
	       	        }
	            }
	        }

	        public void acaba() {
	            this.sigo = false;
	        }
	    }
	
	

}
