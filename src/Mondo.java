import java.util.ArrayList;
import java.util.Vector;

public class Mondo {

    private ArrayList<Luogo> mondo;
    private Luogo luogoAttivo;
    private int pianoCorrente;

    public Mondo(String nome) {
        mondo = new ArrayList<Luogo>();
        for (int i = 1; i <= 3; i++) {
            String nomeFile = "";
            nomeFile += "./src/Mappe/" + nome + "_luogo" + i;
            mondo.add(new Luogo(nomeFile, i));
        }
        pianoCorrente = 1;
        luogoAttivo = mondo.get(pianoCorrente-1);

    }

    public void cambioLuogo(char input) {
        if (input == 'u' && luogoAttivo.isPassaggioRaggiunto() && this.pianoCorrente < mondo.size()) luogoAttivo = mondo.get(++this.pianoCorrente);
        else if (input == 'd' && luogoAttivo.isPassaggioRaggiunto() && this.pianoCorrente > 0) luogoAttivo = mondo.get(--this.pianoCorrente);
    }
}
