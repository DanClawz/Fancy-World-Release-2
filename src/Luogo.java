import java.util.ArrayList;

public class Luogo {
    private Coordinata start, goal, posCorrente;
    private ArrayList<Coordinata> passaggi, ostacoli;
    private char[][] mappa;
    private Mappa mappaIniziale;
    private boolean passaggioRaggiunto, goalRaggiunto;
    private int piano;

    public Luogo(String nomeFile, int piano) {
        this.piano = piano;
        mappaIniziale = new Mappa(nomeFile);
        mappa = mappaIniziale.getMap();
        passaggi = mappaIniziale.posizionePassaggi();
        ostacoli = mappaIniziale.posizioneOstacoli();
        start = mappaIniziale.posizioneIniziale();
        goal = mappaIniziale.posizioneGoal();
        posCorrente = start;
        passaggioRaggiunto = false;
        goalRaggiunto = false;
    }


    public String stampaMappaAggiornata() {

        String mappaContorno = "";

        mappaContorno += "╔";
        for (int i = 0; i < Mappa.NCOLONNE; i++) {
            mappaContorno += "═";
        }
        mappaContorno += "╗" + "\n";


        for (int i = 0; i < Mappa.NRIGHE; i++) {
            mappaContorno += "║";
            for (int j = 0; j < Mappa.NCOLONNE; j++) {
                mappaContorno += mappa[i][j];
            }
            mappaContorno += "║" + "\n";
        }

        mappaContorno += "╚";
        for (int i = 0; i < Mappa.NCOLONNE; i++) {
            mappaContorno += "═";
        }
        mappaContorno += "╝" + "\n";

        return mappaContorno.replace(".", " ");
    }

    public void muovi(Coordinata posNew) {
        if (passaggi.contains(posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = '○';
        else mappa[posCorrente.getX()][posCorrente.getY()] = '.';
        mappa[posNew.getX()][posNew.getY()] = '●';
        this.posCorrente = posNew;
        //System.out.println(posCorrente);
    }

    public void aggiornaMappa(char input) {

        Coordinata posNuova = posizioneNuova(input);
        System.out.println(input);

        if (posNuova.getX() < 0) posNuova.setX(0);
        if (posNuova.getY() < 0) posNuova.setY(0);
        if (posNuova.getX() > Mappa.NRIGHE) posNuova.setX(Mappa.NRIGHE-1);
        if (posNuova.getY() > Mappa.NCOLONNE) posNuova.setY(Mappa.NCOLONNE-1);

        /*
        Pseudocodice:
        ...
         */
        for (int i = (posCorrente.getX()-1 < 0 ? 0 : posCorrente.getX()-1); i <= (posCorrente.getX()+1 > Mappa.NRIGHE ? posCorrente.getX() : posCorrente.getX()+1); i++) {          //riscrivere eventualmente
            for (int j = (posCorrente.getY()-1 < 0 ? 0 : posCorrente.getY()-1); j <= (posCorrente.getY()+1 > Mappa.NCOLONNE ? posCorrente.getY() : posCorrente.getY()+1); j++) {    //riscrivere eventualmente


                if (!ostacoli.contains(posNuova)) {
                    muovi(posNuova);
                }

                if (passaggi.contains(posNuova)) {
                    passaggioRaggiunto = true;
                }
                else passaggioRaggiunto = false;

                if (goal.equals(posNuova)) {
                    goalRaggiunto = true;
                }
                else goalRaggiunto = false;
            }
        }
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
