package hardverbolt;

public class Winchester extends Hardware{
    
    private int kapacitas;
    private int fordulatszam;
    private double eleresiido;
    
    public Winchester(int cikkszam, String megnevezes, int beszerzesiar) {
        super(cikkszam, megnevezes, beszerzesiar);
    }

    public Winchester(int kapacitas, int fordulatszam, double eleresiido, int cikkszam, String megnevezes, int beszerzesiar) {
        super(cikkszam, megnevezes, beszerzesiar);
        this.kapacitas = kapacitas;
        this.fordulatszam = fordulatszam;
        this.eleresiido = eleresiido;
    }

    public int getKapacitas() {
        return kapacitas;
    }

    public void setKapacitas(int kapacitas) {
        this.kapacitas = kapacitas;
    }

    public int getFordulatszam() {
        return fordulatszam;
    }

    public void setFordulatszam(int fordulatszam) {
        this.fordulatszam = fordulatszam;
    }

    public double getEleresiido() {
        return eleresiido;
    }

    public void setEleresiido(double eleresiido) {
        this.eleresiido = eleresiido;
    }

    @Override
    public String toString() {
        return "Winchester{" + "kapacitas=" + kapacitas + ", fordulatszam=" + fordulatszam + ", eleresiido=" + eleresiido + '}';
    }
    
    
}
