import java.util.ArrayList;

public class Passaggio {
    private Coordinata coordinata;
    private boolean aperto;
    private Luogo dest;


    public Passaggio(Coordinata coordinata, boolean aperto) {
        this.coordinata = coordinata;
        this.aperto = aperto;
    }

    public boolean comparePassaggio(Coordinata c) {
        if (this.coordinata.equals(c)) return true;
        return false;
    }

    public Passaggio comparePassaggio1(Coordinata c) {
        if (this.coordinata.equals(c)) return this;
        return null;
    }

    public static boolean compareListaPassaggi(ArrayList<Passaggio> passaggi, Coordinata c) {
        for (Passaggio p : passaggi) {
            if (p.getCoordinata().equals(c)) return true;
        }
        return false;
    }       // controlla che in una lista di passaggi ci sia la coordinata di una di questi passaggi

    public static Passaggio compareListaPass(ArrayList<Passaggio> passaggi, Coordinata c) {
        for (Passaggio p : passaggi) {
            if (p.getCoordinata().equals(c)) return p;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {       //override equals per oggetti Passaggio
        if (o instanceof Passaggio) {
            Passaggio p = (Passaggio)o;
            if (this.coordinata.equals(p.getCoordinata())) return true;
            else return false;
        }
        return false;
    }


    public Coordinata getCoordinata() {
        return coordinata;
    }

    public void setCoordinata(Coordinata coordinata) {
        this.coordinata = coordinata;
    }

    public boolean isAperto() {
        return aperto;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }

    public void setDest(Luogo dest) {
        this.dest = dest;
    }

    public Luogo getDest() {
        return dest;
    }

}
