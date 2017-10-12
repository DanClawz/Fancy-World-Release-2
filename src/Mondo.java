import java.util.ArrayList;

public class Mondo {

    final static int NLUOGHI = 3;
    private ArrayList<Luogo> mondo;
    private int pianoCorrente, nuovoPiano;
    private String nomeMondo;

    public Mondo(String nome) {
        this.nomeMondo = nome;
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
        boolean passaggioEffettuato = false;
        //int nuovoPiano;     // il piano in cui ci porta il passaggio

        if (input == 'u' && !(pianoCorrente + 1 > mondo.size()-1)) this.nuovoPiano = pianoCorrente+1;         // si imposta il nuovo piano (se possibile) in base all'input (u / d)
        else if (input == 'd' && !(pianoCorrente - 1 < 0)) this.nuovoPiano = pianoCorrente-1;
        else nuovoPiano = pianoCorrente;

        for (Passaggio p1 : mondo.get(pianoCorrente).getLista_passaggi()) {         // controllo coordinate dei passaggi dei due luogi adiacenti -> se il cambio luogo è possibile
            for (Passaggio p2 : mondo.get(nuovoPiano).getLista_passaggi()) {
                if (((input == 'u' && !(pianoCorrente + 1 > mondo.size()-1)) || (input == 'd' && !(pianoCorrente - 1 < 0))) && p1.equals(p2) && mondo.get(pianoCorrente).getPosCorrente().equals(p2.getCoordinata())) {

                    pianoCorrente = nuovoPiano;         //il passaggio viene effettuato se possibile
                    mondo.get(pianoCorrente).setPassaggioRaggiunto(true);
                    passaggioEffettuato = true;
                    mondo.get(pianoCorrente).resetPassaggi();
                    break;
                }
            }
        }

        if (!passaggioEffettuato) System.out.println("Passaggio non possibile!");
        else System.out.println("Passaggio effettuato!");

        mondo.get(pianoCorrente).setPosCorrente(coordinataPassaggio);
        mondo.get(pianoCorrente).muovi(coordinataPassaggio);
    }

    public Luogo direzionePassaggio() {
        if (pianoCorrente + 1 < mondo.size())
            for (Passaggio p1 : mondo.get(pianoCorrente).getLista_passaggi())
                for (Passaggio p2 : mondo.get(pianoCorrente+1).getLista_passaggi())
                    if (p1.getCoordinata().equals(mondo.get(pianoCorrente).getPosCorrente()) && p1.equals(p2))
                        return mondo.get(pianoCorrente+1);

        if (pianoCorrente - 1 > -1)
            for (Passaggio p1 : mondo.get(pianoCorrente).getLista_passaggi())
                for (Passaggio p2 : mondo.get(pianoCorrente-1).getLista_passaggi())
                    if (p1.getCoordinata().equals(mondo.get(pianoCorrente).getPosCorrente()) && p1.equals(p2))
                        return mondo.get(pianoCorrente-1);

        return mondo.get(pianoCorrente);
    }

    public boolean obbiettivoRaggiunto() {
        return mondo.get(pianoCorrente).isGoalRaggiunto();
    }

    public String luogoGoal() {
        for (Luogo l : mondo) {
            if (l.isGoalPresente()) return l.getNomeLuogo();
        }
        return null;
    }

    public String stampaMappa() {
        return nomeMondo.toUpperCase() + "\n" +
                mondo.get(pianoCorrente).getNomeLuogo() + "\n" +
                "Il goal si trova in: " + luogoGoal() + "\n" +
                mondo.get(pianoCorrente).stampaMappa();
    }

    public ArrayList<Luogo> getMondo() {
        return mondo;
    }

    public int getPianoCorrente() {
        return pianoCorrente;
    }

}
