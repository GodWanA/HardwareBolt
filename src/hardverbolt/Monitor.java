package hardverbolt;

public class Monitor extends Hardware {
    
    private int meret;
    private int xfelbontas;
    private int yfelbontas;
    private boolean led;
    
    public Monitor(int cikkszam, String megnevezes, int beszerzesiar) {
        super(cikkszam, megnevezes, beszerzesiar);
    }

    public Monitor(int meret, int xfelbontas, int yfelbontas, boolean led, int cikkszam, String megnevezes, int beszerzesiar) {
        super(cikkszam, megnevezes, beszerzesiar);
        this.meret = meret;
        this.xfelbontas = xfelbontas;
        this.yfelbontas = yfelbontas;
        this.led = led;
    }

    public int getMeret() {
        return meret;
    }

    public void setMeret(int meret) {
        this.meret = meret;
    }

    public int getXfelbontas() {
        return xfelbontas;
    }

    public void setXfelbontas(int xfelbontas) {
        this.xfelbontas = xfelbontas;
    }

    public int getYfelbontas() {
        return yfelbontas;
    }

    public void setYfelbontas(int yfelbontas) {
        this.yfelbontas = yfelbontas;
    }

    public boolean isLed() {
        return led;
    }

    public void setLed(boolean led) {
        this.led = led;
    }

    @Override
    public String toString() {
        return "Monitor{" + "meret=" + meret + ", xfelbontas=" + xfelbontas + ", yfelbontas=" + yfelbontas + ", led=" + led + '}';
    }
    
}
