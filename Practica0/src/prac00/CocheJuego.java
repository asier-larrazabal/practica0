package prac00;

public class CocheJuego extends Coche{
	protected JLabeLCoche coche = new JLabeLCoche();
	
    public JLabeLCoche getGrafico() {
        return this.coche;
    }

    @Override
    public void setPosX(double posX) {
        super.setPosX(posX);
        this.coche.setLocation((int)posX, (int)this.posY);
    }

    @Override
    public void setPosY(double posY) {
        super.setPosY(posY);
        this.coche.setLocation((int)this.posX, (int)posY);
    }

    @Override
    public void setMiDireccionActual(double dir) {
        super.setMiDireccionActual(dir);
        this.coche.setGiro(this.miDireccionActual);
        this.coche.repaint();
    }
}