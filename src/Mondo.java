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

    public void cambioLuogo(char input, ArrayList<Chiave> chiavi) {
        int nuovoPiano = Passaggio.pianoDestPassaggio(mondo.get(pianoCorrente-1).getLista_passaggi(), mondo.get(pianoCorrente-1).getPosCorrente());
        Coordinata coordinataPassaggio = mondo.get(pianoCorrente-1).getPosCorrente();


        if (((input == 'u' && nuovoPiano > pianoCorrente) || (input == 'd' && nuovoPiano < pianoCorrente))
                && Passaggio.compareListaPassaggi(mondo.get(pianoCorrente-1).getLista_passaggi(), coordinataPassaggio)
                && (mondo.get(pianoCorrente-1).getChiave() == null && mondo.get(pianoCorrente-1).passaggioSuCoordinata(mondo.get(pianoCorrente-1).getPosCorrente()).isAperto()
                || (mondo.get(pianoCorrente-1).getChiave() != null && chiavi.contains(mondo.get(pianoCorrente-1).getChiave())))) {

            this.pianoCorrente = nuovoPiano;
            Luogo l = mondo.get(pianoCorrente-1);
            Coordinata posizione = l.getPosCorrente();
            l.apriPassaggio(posizione, true);
            //mondo.get(pianoCorrente-1).passaggioSuCoordinata(mondo.get(pianoCorrente-1).getPosCorrente()).setAperto(true);
            mondo.get(pianoCorrente-1).setPassaggioRaggiunto(true);
            mondo.get(pianoCorrente-1).resetPassaggi();
        }


        else System.out.println("Passaggio non possibile! Chiave richiesta: " + mondo.get(pianoCorrente-1).passaggioSuCoordinata(mondo.get(pianoCorrente-1).getPosCorrente()).getTipoPassaggio());

        mondo.get(pianoCorrente-1).setPosCorrente(coordinataPassaggio);
        mondo.get(pianoCorrente-1).muovi(coordinataPassaggio);
    }

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
