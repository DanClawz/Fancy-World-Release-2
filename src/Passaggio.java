import java.util.ArrayList;

public class Passaggio {
    private Coordinata coordinata;
    private boolean aperto;

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
    }

    @Override
    public boolean equals(Object o) {
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
}
