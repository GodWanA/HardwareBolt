
package hardverbolt;

import java.util.Objects;

public class Hardware implements HardwareInterface{
    private int cikkszam;
    private String megnevezes;
    private int beszerzesiar;
    private static double haszonkulcs = 20.0;

    public Hardware(int cikkszam, String megnevezes, int beszerzesiar) {
        this.cikkszam = cikkszam;
        this.megnevezes = megnevezes;
        this.beszerzesiar = beszerzesiar;
    }

    public int getCikkszam() {
        return cikkszam;
    }

    public void setCikkszam(int cikkszam) {
        this.cikkszam = cikkszam;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public int getBeszerzesiar() {
        return beszerzesiar;
    }

    public void setBeszerzesiar(int beszerzesiar) {
        this.beszerzesiar = beszerzesiar;
    }

    public static double getHaszonkulcs() {
        return haszonkulcs;
    }

    public static void setHaszonkulcs(double haszonkulcs) {
        Hardware.haszonkulcs = haszonkulcs;
    }
    @Override
    public int geteladasiar(){
        return (int) (this.getBeszerzesiar()*(1+(haszonkulcs/100)));
    }
    
    @Override
    public int haszon(){
        return this.geteladasiar()-this.getBeszerzesiar();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hardware other = (Hardware) obj;
        if (this.cikkszam != other.cikkszam) {
            return false;
        }
        if (!Objects.equals(this.megnevezes, other.megnevezes)) {
            return false;
        }
        return true;
    }
}
