import java.nio.channels.Pipe;
import java.util.ArrayList;

public class Mondo {

    final static int NLUOGHI = 3;
    private ArrayList<Luogo> mondo;
    private int pianoCorrente;
    private String nomeMondo;

    public Mondo(String nome) {
        this.nomeMondo = nome;
        mondo = new ArrayList<Luogo>();
        for (int i = 1; i <= NLUOGHI; i++) {
            String nomeFile = "";
            nomeFile += "./src/Mappe/" + nome + "_luogo" + i;
            mondo.add(new Luogo(nomeFile, i));
        }
        pianoCorrente = 1;  // ATTENZIONE AGLI INDICI!!!!!
    }

    public void cambioLuogo(char input) {
        int nuovoPiano = Passaggio.pianoDestPassaggio(mondo.get(pianoCorrente-1).getLista_passaggi(), mondo.get(pianoCorrente-1).getPosCorrente());
        Coordinata coordinataPassaggio = mondo.get(pianoCorrente-1).getPosCorrente();

        if (((input == 'u' && nuovoPiano > pianoCorrente) || (input == 'd' && nuovoPiano < pianoCorrente)) && Passaggio.compareListaPassaggi(mondo.get(pianoCorrente-1).getLista_passaggi(), coordinataPassaggio)) {
            this.pianoCorrente = nuovoPiano;
            System.out.println("Aggiorna piano!");
            mondo.get(pianoCorrente-1).setPassaggioRaggiunto(true);
            mondo.get(pianoCorrente-1).resetPassaggi();
        }

        mondo.get(pianoCorrente-1).setPosCorrente(coordinataPassaggio);
        mondo.get(pianoCorrente-1).muovi(coordinataPassaggio);
    }

    /*public Luogo direzionePassaggio() {
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
    }*/

    public boolean obbiettivoRaggiunto() {
        return mondo.get(pianoCorrente-1).isGoalRaggiunto();
    }

    public String luogoGoal() {
        for (Luogo l : mondo) {
            if (l.isGoalPresente()) return l.getNomeLuogo();
        }
        return null;
    }

    public String stampaMappa() {
        return nomeMondo.toUpperCase() + "\n" +
                mondo.get(pianoCorrente-1).getNomeLuogo() + "\n" +
                "Il goal si trova in: " + luogoGoal() + "\n" +
                mondo.get(pianoCorrente-1).stampaMappa();
    }

    public ArrayList<Luogo> getMondo() {
        return mondo;
    }

    public int getPianoCorrente() {
        return pianoCorrente;
    }


    public static void main (String args[]) {
        /*int nuovoPiano = Passaggio.pianoDestPassaggio(mondo.get(pianoCorrente-1).getLista_passaggi(), mondo.get(pianoCorrente-1).getPosCorrente());
        System.out.println(nuovoPiano);*/
    }
}
