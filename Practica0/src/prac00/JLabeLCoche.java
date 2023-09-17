package prac00;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabeLCoche extends JLabel{

	private static final long serialVersionUID = 1L;
    public static final int tamayo = 100;
	private double miGiro = 1.5707963267948966;
	
	public JLabeLCoche() {
        try {
            this.setIcon(new ImageIcon(JLabeLCoche.class.getResource("coche.png").toURI().toURL()));
        }
        catch (Exception e) {
            System.err.println("Error en carga de recurso: coche.png no encontrado");
            e.printStackTrace();
        }
        this.setBounds(0, 0, 100, 100);
	}

	public void setGiro(double gradosGiro) {
        this.miGiro = gradosGiro / 180.0 * Math.PI;
        this.miGiro = -this.miGiro;
        this.miGiro += 1.5707963267948966;
    }
	
    @Override
    protected void paintComponent(Graphics g) {
        Image img = ((ImageIcon)this.getIcon()).getImage();
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.rotate(this.miGiro, 50.0, 50.0);
        g2.drawImage(img, 0, 0, 100, 100, null);
    }
}
