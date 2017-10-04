import java.util.ArrayList;
import java.util.Vector;

public class Mondo {

    private ArrayList<Luogo> mondo;
    //private Luogo luogoAttivo;
    private int pianoCorrente;

    public Mondo(String nome) {
        mondo = new ArrayList<Luogo>();
        for (int i = 1; i <= 3; i++) {
            String nomeFile = "";
            nomeFile += "./src/Mappe/" + nome + "_luogo" + i;
            mondo.add(new Luogo(nomeFile, i));
        }
        pianoCorrente = 0;
        //luogoAttivo = mondo.get(pianoCorrente);

    }

    public void cambioLuogo(char input) {
        Coordinata temp = mondo.get(pianoCorrente).getPosCorrente();
        boolean passaggioUguale = false;


        int l = 0;
        if (input == 'u' && pianoCorrente == 2) {
            //??
        }


        for (Coordinata p : mondo.get(pianoCorrente).getPassaggi()) {
            for (Coordinata c : mondo.get((input == 'u') ? pianoCorrente+1 : pianoCorrente-1).getPassaggi()) {
                if (p.equals(c)) {
                    passaggioUguale = true;
                    System.out.println(passaggioUguale);
                    break;
                }
            }


        }

        if (input == 'u' && mondo.get(pianoCorrente).isPassaggioRaggiunto() && this.pianoCorrente < mondo.size()-1 && passaggioUguale && mondo.get(pianoCorrente).getPosCorrente().equals()) {
            ++this.pianoCorrente;

        }
        else if (input == 'd' && mondo.get(pianoCorrente).isPassaggioRaggiunto() && this.pianoCorrente > 0 && passaggioUguale) {
            --this.pianoCorrente;

        }
        mondo.get(pianoCorrente).setPosCorrente(temp);
        //System.out.println(luogoAttivo.getPassaggi());
        mondo.get(pianoCorrente).muovi(temp);
    }

    public ArrayList<Luogo> getMondo() {
        return mondo;
    }

    public int getPianoCorrente() {
        return pianoCorrente;
    }
}
