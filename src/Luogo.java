import java.util.ArrayList;

public class Luogo {
    private Coordinata start, goal, posCorrente;
    private ArrayList<Coordinata> passaggi, ostacoli;
    private char[][] mappa;
    private Mappa mappaIniziale;

    public Luogo(String nomeFile) {
        mappaIniziale = new Mappa(nomeFile);
        mappa = mappaIniziale.getMap();
        passaggi = mappaIniziale.posizionePassaggi();
        ostacoli = mappaIniziale.posizioneOstacoli();
        start = mappaIniziale.posizioneIniziale();
        goal = mappaIniziale.posizioneGoal();
        posCorrente = start;
    }

    /*public void stampaMappa() {


        // settare la stringa mappa

        System.out.println(mappa);
    }*/

    public String stampaMappaAggiornata() {
        String mappaContorno = "";
        mappaContorno += "╔═══════════════╗" + "\n";

        for (int i = 0; i < Mappa.NRIGHE; i++) {
            mappaContorno += "║";
            for (int j = 0; j < Mappa.NCOLONNE; j++) {
                mappaContorno += mappa[i][j];
            }
            mappaContorno += "║" + "\n";
        }
        mappaContorno += "╚═══════════════╝" + "\n";
        return mappaContorno.replace(".", " ");
    }


    private void aggiornaMappa(Coordinata posNuova) {
        System.out.println("Posizione nuova:" + posNuova);
        System.out.println("Ostacoli: " + ostacoli.toString());
        for (int i = 0; i < Mappa.NRIGHE; i++) {
            for (int j = 0; j < Mappa.NCOLONNE; j++) {

                if (!ostacoli.contains(posNuova)) {
                    
                    if (mappa[i][j] == '●') {
                        mappa[i][j] = '.';
                    }
                    if (i == posNuova.getX() && j == posNuova.getY()) {
                        mappa[i][j] = '●';
                        break;
                    }
                }
                else System.out.println("Mossa non possibile");
            }
        }
    }



    public void swapPosizioni(Coordinata posNuova) {
        Coordinata t;
        t = posCorrente;
        posCorrente = posNuova;
        posNuova = t;
    }


    public Coordinata getPosCorrente() {
        return posCorrente;
    }

    public void setPosCorrente(Coordinata nuovaPosizione) {
        this.posCorrente = nuovaPosizione;
        aggiornaMappa(nuovaPosizione);
    }

    public char[][] getMappa() {
        return mappa;
    }

    public void setMappa(char[][] mappa) {
        this.mappa = mappa;
    }

    public Coordinata getStart() {
        return start;
    }

    public Coordinata getGoal() {
        return goal;
    }

    public ArrayList<Coordinata> getPassaggi() {
        return passaggi;
    }

    public ArrayList<Coordinata> getOstacoli() {
        return ostacoli;
    }

    public Mappa getMappaIniziale() {
        return mappaIniziale;
    }
}
