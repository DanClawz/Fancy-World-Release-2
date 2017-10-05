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
        int nuovoPiano = -1;

        if (input == 'u' && !(pianoCorrente + 1 > 2)) nuovoPiano = pianoCorrente+1;
        else if (input == 'd' && !(pianoCorrente - 1 < 0)) nuovoPiano = pianoCorrente-1;
        else nuovoPiano = pianoCorrente;




        for (Coordinata p : mondo.get(pianoCorrente).getPassaggi()) {
            for (Coordinata c : mondo.get(nuovoPiano).getPassaggi()) {

                if (!(pianoCorrente + 1 > 2) && p.equals(c) && mondo.get((pianoCorrente+1)).getPassaggi().contains(mondo.get(pianoCorrente).getPosCorrente()) && input == 'u') {
                    passaggioUguale = true;
                    System.out.println("u: " + passaggioUguale);
                    mondo.get(pianoCorrente+1).setPassaggioRaggiunto(true);
                    break;
                }

                if (!(pianoCorrente - 1 < 0) && p.equals(c) && mondo.get(pianoCorrente-1).getPassaggi().contains(mondo.get(pianoCorrente).getPosCorrente()) && input == 'd') {
                    passaggioUguale = true;
                    System.out.println("d: " + passaggioUguale);
                    mondo.get(pianoCorrente-1).setPassaggioRaggiunto(true);
                    break;
                }

            }

        }

        if (input == 'u' && mondo.get(pianoCorrente).isPassaggioRaggiunto() && this.pianoCorrente < mondo.size()-1 && passaggioUguale) {
            ++this.pianoCorrente;
            System.out.println("Up");

        }
        else if (input == 'd' && mondo.get(pianoCorrente).isPassaggioRaggiunto() && this.pianoCorrente >= 0 && passaggioUguale) {
            --this.pianoCorrente;
            System.out.println("Down");
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
