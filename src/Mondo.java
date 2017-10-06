import java.util.ArrayList;

public class Mondo {

    final static int NLUOGHI = 3;
    private ArrayList<Luogo> mondo;
    private int pianoCorrente;

    public Mondo(String nome) {
        mondo = new ArrayList<Luogo>();
        for (int i = 1; i <= NLUOGHI; i++) {
            String nomeFile = "";
            nomeFile += "./src/Mappe/" + nome + "_luogo" + i;
            mondo.add(new Luogo(nomeFile, i));
        }
        pianoCorrente = 0;
    }

    public void cambioLuogo(char input) {
        Coordinata coordinataPassaggio = mondo.get(pianoCorrente).getPosCorrente();     // salviamo la posizione corrente del giocatore, ovvero la posizione del passaggio, che verrà utilizzata qualora è possibile cambiare luogo
        boolean passaggioUguale = false;
        int nuovoPiano;     // il piano in cui ci porta il passaggio

        if (input == 'u' && !(pianoCorrente + 1 > mondo.size()-1)) nuovoPiano = pianoCorrente+1;         // si imposta il nuovo piano (se possibile) in base all'input (u / d)
        else if (input == 'd' && !(pianoCorrente - 1 < 0)) nuovoPiano = pianoCorrente-1;
        else nuovoPiano = pianoCorrente;

        for (Coordinata p : mondo.get(pianoCorrente).getPassaggi()) {               // controllo coordinate dei passaggi dei due luogi adiacenti -> se il cambio luogo è possibile
            for (Coordinata c : mondo.get(nuovoPiano).getPassaggi()) {
                if (!(pianoCorrente + 1 > mondo.size()-1) && p.equals(c) && mondo.get((pianoCorrente+1)).getPassaggi().contains(mondo.get(pianoCorrente).getPosCorrente()) && input == 'u') {
                    passaggioUguale = true;
                    mondo.get(pianoCorrente+1).setPassaggioRaggiunto(true);
                    break;
                }
                if (!(pianoCorrente - 1 < 0) && p.equals(c) && mondo.get(pianoCorrente-1).getPassaggi().contains(mondo.get(pianoCorrente).getPosCorrente()) && input == 'd') {
                    passaggioUguale = true;
                    mondo.get(pianoCorrente-1).setPassaggioRaggiunto(true);
                    break;
                }
            }
        }

        if (input == 'u' && mondo.get(pianoCorrente).isPassaggioRaggiunto() && this.pianoCorrente < mondo.size()-1 && passaggioUguale)         //il passaggio viene effettuato se possibile
            ++this.pianoCorrente;

        else if (input == 'd' && mondo.get(pianoCorrente).isPassaggioRaggiunto() && this.pianoCorrente >= 0 && passaggioUguale)
            --this.pianoCorrente;

        mondo.get(pianoCorrente).setPosCorrente(coordinataPassaggio);
        mondo.get(pianoCorrente).muovi(coordinataPassaggio);
    }

    public boolean obbiettivoRaggiunto() {
        return mondo.get(pianoCorrente).isGoalRaggiunto();
    }

    public String stampaMappa() {
        return mondo.get(pianoCorrente).stampaMappaAggiornata();
    }


    public ArrayList<Luogo> getMondo() {
        return mondo;
    }

    public int getPianoCorrente() {
        return pianoCorrente;
    }
}
