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
        int indice = pianoCorrente - 1;
        int nuovoPiano = Passaggio.pianoDestPassaggio(mondo.get(indice).getLista_passaggi(), mondo.get(indice).getPosCorrente());
        Coordinata coordinataPassaggio = mondo.get(indice).getPosCorrente();

        if (((input == 'u' && nuovoPiano > pianoCorrente) || (input == 'd' && nuovoPiano < pianoCorrente))
                && (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio))
                && (((mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente()).isAperto())) ||
                Passaggio.matchChiavi(chiavi, mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente())))) {

            this.pianoCorrente = nuovoPiano;
            mondo.get(pianoCorrente-1).apriPassaggio(mondo.get(pianoCorrente-1).getPosCorrente(), true);
            mondo.get(pianoCorrente-1).setPassaggioRaggiunto(true);
            mondo.get(pianoCorrente-1).resetPassaggi();
        }

        else if (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio))
            System.out.println("Passaggio non possibile! Chiave richiesta: " + mondo.get(pianoCorrente-1).passaggioSuCoordinata(mondo.get(pianoCorrente-1).getPosCorrente()).getTipoPassaggio());

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
