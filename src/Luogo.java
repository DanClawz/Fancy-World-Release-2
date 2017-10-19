import java.util.ArrayList;

public class Luogo {
    private Coordinata start, goal, posCorrente;
    private ArrayList<Coordinata> ostacoli;
    private ArrayList<Passaggio> lista_passaggi;
    private char[][] mappa;
    private Mappa mappaIniziale;
    private boolean passaggioRaggiunto, goalRaggiunto, chiavePresente;
    private int piano;
    private String nomeLuogo;
    private Chiave chiave;

    public Luogo(String nomeFile, int piano) {
        this.piano = piano;
        this.nomeLuogo = nomeFile.split("_")[1];
        mappaIniziale = new Mappa(nomeFile);
        mappa = mappaIniziale.getMap();
        lista_passaggi = mappaIniziale.passaggi();
        ostacoli = mappaIniziale.posizioneOstacoli();
        start = mappaIniziale.posizioneIniziale();
        goal = mappaIniziale.posizioneGoal();
        chiave = mappaIniziale.posizioneChiave();

        posCorrente = start;
        passaggioRaggiunto = false;
        goalRaggiunto = false;
        chiavePresente = false;
    }


    public String stampaMappa() {
        String mappaContorno = "";

        mappaContorno += "╔";
        for (int i = 0; i < Mappa.NCOLONNE; i++)
            mappaContorno += "═";
        mappaContorno += "╗" + "\n";

        for (int i = 0; i < Mappa.NRIGHE; i++) {
            mappaContorno += "║";
            for (int j = 0; j < Mappa.NCOLONNE; j++)
                mappaContorno += mappa[i][j];
            mappaContorno += "║" + "\n";
        }

        mappaContorno += "╚";
        for (int i = 0; i < Mappa.NCOLONNE; i++)
            mappaContorno += "═";
        mappaContorno += "╝" + "\n";

        return mappaContorno.replace(".", " ");
    }

    public void muovi(Coordinata posNew) {
        if (Passaggio.compareListaPassaggi(lista_passaggi, posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = '○';
        else if (chiave != null && chiave.getPosChiave().equals(posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = '¶';
        else if (goal.equals(posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = 'X';
        else mappa[posCorrente.getX()][posCorrente.getY()] = '.';
        mappa[posNew.getX()][posNew.getY()] = '●';
        this.posCorrente = posNew;
    }       // il metodo sostituisce il pallino del giocatore con uno spazio vuoto, e lo spazio vuoto con il pallino del giocatore

    public void resetPassaggi() {
        for (Passaggio p : lista_passaggi) {
            mappa[p.getCoordinata().getX()][p.getCoordinata().getY()] = '○';
        }
    }


    public void aggiornaMappa(char input) {
        boolean mossaPossibile = false, bordoToccato = false;
        Coordinata posNuova = posizioneNuova(input);

        if (posNuova.getX() < 0) posNuova.setX(0);
        if (posNuova.getY() < 0) posNuova.setY(0);
        if (posNuova.getX() >= Mappa.NRIGHE) posNuova.setX(Mappa.NRIGHE-1);
        if (posNuova.getY() >= Mappa.NCOLONNE) posNuova.setY(Mappa.NCOLONNE-1);

        if (posNuova.equals(posCorrente)) bordoToccato = true;

        /*
        Pseudocodice:
        ...
         */
        for (int i = (posCorrente.getX()-1 < 0 ? 0 : posCorrente.getX()-1); i <= (posCorrente.getX()+1 > Mappa.NRIGHE ? posCorrente.getX() : posCorrente.getX()+1); i++) {          //riscrivere eventualmente
            for (int j = (posCorrente.getY()-1 < 0 ? 0 : posCorrente.getY()-1); j <= (posCorrente.getY()+1 > Mappa.NCOLONNE ? posCorrente.getY() : posCorrente.getY()+1); j++) {    //riscrivere eventualmente

                if (!ostacoli.contains(posNuova)) {
                    muovi(posNuova);
                    mossaPossibile = true;
                }

                if (chiave != null && chiave.getPosChiave().equals(posCorrente)) {
                    chiavePresente = true;
                }

                if (Passaggio.compareListaPassaggi(lista_passaggi, posNuova))
                    passaggioRaggiunto = true;
                else passaggioRaggiunto = false;

                if (goal.equals(posNuova))
                    goalRaggiunto = true;
                else goalRaggiunto = false;
            }
        }

        if (!mossaPossibile || bordoToccato) System.out.println("Mossa non possibile!");
        else System.out.println("Mossa possibile!");

        if (passaggioRaggiunto) System.out.println("Ti trovi su un passaggio!");

    }

    private Coordinata posizioneNuova(char input) {
        if (input == 'n') return new Coordinata(posCorrente.getX()-1, posCorrente.getY());
        if (input == 's') return new Coordinata(posCorrente.getX()+1, posCorrente.getY());
        if (input == 'e') return new Coordinata(posCorrente.getX(), posCorrente.getY()+1);
        if (input == 'w') return new Coordinata(posCorrente.getX(), posCorrente.getY()-1);
        return null;
    }


    public int getPiano() {
        return piano;
    }

    public boolean isGoalRaggiunto() {
        return goalRaggiunto;
    }

    public void setGoalRaggiunto(boolean goalRaggiunto) {
        this.goalRaggiunto = goalRaggiunto;
    }

    public boolean isPassaggioRaggiunto() {
        return passaggioRaggiunto;
    }

    public void setPassaggioRaggiunto(boolean passaggioRaggiunto) {
        this.passaggioRaggiunto = passaggioRaggiunto;
    }

    public Coordinata getPosCorrente() {
        return posCorrente;
    }

    public void setPosCorrente(Coordinata nuovaPosizione) {
        this.posCorrente = nuovaPosizione;
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

    public void setStart(Coordinata start) {
        this.start = start;
    }

    public Coordinata getGoal() {
        return goal;
    }

    public boolean isGoalPresente() {
        if (goal.getX() != -1 && goal.getY() != -1) return true;
        return false;
    }

    public ArrayList<Passaggio> getLista_passaggi() {
        return lista_passaggi;
    }

    public ArrayList<Coordinata> getOstacoli() {
        return ostacoli;
    }

    public Mappa getMappaIniziale() {
        return mappaIniziale;
    }

    public String getNomeLuogo() {
        return nomeLuogo;
    }

    public boolean isChiavePresente() {
        return chiavePresente;
    }

    public void setChiavePresente(boolean chiavePresente) {
        this.chiavePresente = chiavePresente;
    }

    public Chiave getChiave() {
        return chiave;
    }

    public void setChiave(Chiave chiave) {
        this.chiave = chiave;
    }
}
