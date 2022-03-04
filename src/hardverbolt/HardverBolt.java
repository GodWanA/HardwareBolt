
package hardverbolt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HardverBolt {
    
    private static String megnevezes;

    //Kulcsra való keresés esetén nagyon gyors
    private Map<Integer, Hardware> arukeszlet;
    private int forgotoke;
    public static int cikkszam = 0; //osztályszintű

    //forgotőke konstruktora
    public HardverBolt(int forgotoke) {
        this.forgotoke = forgotoke;
        this.arukeszlet = new HashMap<Integer, Hardware>();
    }
    
    public HardverBolt(Map<Integer, Hardware> arukeszlet, int forgotoke) {
        this.arukeszlet = arukeszlet;
        this.forgotoke = forgotoke;
    }
    
    public Map<Integer, Hardware> getArukeszlet() {
        return arukeszlet;
    }

    public void setArukeszlet(Map<Integer, Hardware> arukeszlet) {
        this.arukeszlet = arukeszlet;
    }

    public int getForgotoke() {
        return forgotoke;
    }

    public void setForgotoke(int forgotoke) {
        this.forgotoke = forgotoke;
    }

    public static int getCikkszam() {
        return cikkszam;
    }

    public static void setCikkszam(int cikkszam) {
        HardverBolt.cikkszam = cikkszam;
    }
    
    /**Hozzd létre a vesz(Hardware ezt) (boolean) függvényt, amely beolvas 
    * a billentyűzetről egy megvett hardwer elem adatait. A map-hez hozzáadja az új
    * hardware elemet, a forgótőkéből levonja az eszköz beszerzési árát. 
    */
    
    public boolean vesz(Hardware ezt){
        BufferedReader be = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Forgótőkénk: "+this.getForgotoke());
        System.out.println("Típus? Monitor - m, Winchester - w");
        try {
            String valasz = be.readLine();
            System.out.println("Megnevezés: ");
            String eln = be.readLine();
            System.out.println("Ár: ");
            int ar = Integer.parseInt(be.readLine());
            int cikksz = cikkszam++;
            
            if (valasz.equals("m")) {
                System.out.println("Méret: ");
                int meret = Integer.parseInt(be.readLine());
                System.out.println("X felbontás: ");
                int x = Integer.parseInt(be.readLine());
                System.out.println("Y felbontás: ");
                int y = Integer.parseInt(be.readLine());
                System.out.println("led/lcd: ");
                boolean ledes = be.readLine().equals("led");
                Monitor mon = new Monitor(meret, x, y, ledes, cikksz, eln, ar);
                arukeszlet.put(mon.getCikkszam(), mon);
                this.setForgotoke(this.getForgotoke()-mon.getBeszerzesiar());
            } else {
                System.out.println("Kapacitás: ");
                int kap = Integer.parseInt(be.readLine());
                System.out.println("Fordulatszám: ");
                int ford = Integer.parseInt(be.readLine());
                System.out.println("Elérési idő: ");
                double eleres = Double.parseDouble(be.readLine());
                Winchester win = new Winchester(kap, ford, eleres, cikksz, eln, ar);
                arukeszlet.put(win.getCikkszam(), win);
                this.setForgotoke(this.getForgotoke()-win.getBeszerzesiar());
            }
            
        } catch (IOException ex) {
            System.out.println("Hiba az IO alatt");
            return false;
        }
          catch(Exception ex){
                  System.out.println("Hiba!");
            return false;      
          } 
          return true;
        }
    public boolean elad(int ezt){
        Hardware eladva = arukeszlet.remove(ezt);
        if(eladva != null){
            this.setForgotoke(this.getForgotoke()+eladva.geteladasiar());
            return true;
    }
        return false;
    }
    
    public Hardware kerescikkszam(int ezt){
        return arukeszlet.get(ezt);
    }
    
    public int keresmegnevezes(String ezt){
        for (Integer kulcs : arukeszlet.keySet()) {
            Hardware seged = arukeszlet.get(kulcs);
            if(seged.getMegnevezes().equals(ezt))
            {
                return kulcs;
            }
        }
        return -1;
    }
    
    //Állománykezelő metódus - beolvasás fileból
    public boolean feltolt(String innen){
        BufferedReader beallomany = null;
        try {
            beallomany = new BufferedReader(new FileReader(innen));
            String sor = null;
            while ((sor = beallomany.readLine()) != null) {                
                System.out.println(sor);
                StringTokenizer st = new StringTokenizer(sor,"#");
                if (st.nextToken().equals("MONITOR")) {
                    String eln = st.nextToken();
                    int ar = Integer.parseInt(st.nextToken());
                    int meret = Integer.parseInt(st.nextToken());
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    boolean led = st.nextToken().equals("led");
                    Monitor mon = new Monitor(meret, x, x, led, (cikkszam++), eln, ar);
                    arukeszlet.put(mon.getCikkszam(), mon);
                } else {
                    String eln = st.nextToken();
                    int ar = Integer.parseInt(st.nextToken());
                    int kapacitas = Integer.parseInt(st.nextToken());
                    int fordulatszam = Integer.parseInt(st.nextToken());
                    Double eleres = Double.parseDouble(st.nextToken());
                    Winchester win = new Winchester(kapacitas, fordulatszam, eleres, (cikkszam++), eln, ar);
                    arukeszlet.put(win.getCikkszam(),win);
                }
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            return false;
        } finally {
            try {
                beallomany.close();
            } catch (IOException ex) {
                Logger.getLogger(HardverBolt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        HardverBolt sajatbolt = new HardverBolt(1000000);
        //sajatbolt.vesz(new Hardware(cikkszam, megnevezes, cikkszam));
        //sajatbolt.elad(cikkszam);
        sajatbolt.feltolt("arukeszlet.txt");
        System.out.println(sajatbolt.kerescikkszam(3));
        System.out.println(sajatbolt.keresmegnevezes("LG 25UM58-P"));
        
    }
}
