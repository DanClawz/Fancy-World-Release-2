import java.util.ArrayList;

public class Luogo {
    private Coordinata start, goal, posCorrente;
    private ArrayList<Coordinata> passaggi, ostacoli;
    private char[][] mappa;
    private Mappa mappaIniziale;
    private boolean passaggioRaggiunto, goalRaggiunto;

    public Luogo(String nomeFile) {
        mappaIniziale = new Mappa(nomeFile);
        mappa = mappaIniziale.getMap();
        passaggi = mappaIniziale.posizionePassaggi();
        ostacoli = mappaIniziale.posizioneOstacoli();
        start = mappaIniziale.posizioneIniziale();
        goal = mappaIniziale.posizioneGoal();
        posCorrente = start;
        passaggioRaggiunto = false;
        goalRaggiunto = false;
        System.out.println(":" + posCorrente);
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


    public void muovi(Coordinata posNew) {
        mappa[posCorrente.getX()][posCorrente.getY()] = '.';
        mappa[posNew.getX()][posNew.getY()] = '●';
        this.posCorrente = posNew;
    }

    public void aggiornaMappa(char input) {

        Coordinata posNuova = posizioneNuova(input);
        if (posNuova.getX() < 0) posNuova.setX(0);
        if (posNuova.getY() < 0) posNuova.setY(0);
        if (posNuova.getX() > Mappa.NRIGHE) posNuova.setX(Mappa.NRIGHE-1);
        if (posNuova.getY() > Mappa.NCOLONNE) posNuova.setY(Mappa.NCOLONNE-1);
        System.out.println(posNuova);

        for (int i = (posCorrente.getX()-1 < 0 ? 0 : posCorrente.getX()-1); i <= (posCorrente.getX()+1 > Mappa.NRIGHE ? posCorrente.getX() : posCorrente.getX()+1); i++) {
            for (int j = (posCorrente.getY()-1 < 0 ? 0 : posCorrente.getY()-1); j <= (posCorrente.getY()+1 > Mappa.NCOLONNE ? posCorrente.getY() : posCorrente.getY()+1); j++) {

                System.out.println("[" + i + ", " + j + "]");

                if (!ostacoli.contains(posNuova)) {
                    System.out.println("Mossa possibile");
                    muovi(posNuova);
                }

                /*if (mossaPossibile(input)) {

                    if (passaggi.contains(posNuova)) {
                        passaggioRaggiunto = true;
                        System.out.println("Ti trovi su un passaggio.");

                    }

                    if (goal.equals(posNuova)) {
                        goalRaggiunto = true;
                        System.out.println("Goal raggiunto");

                    }

                    if (mappa[i][j] == '●') {
                        mappa[i][j] = '.';
                    }
                    if (i == posNuova.getX() && j == posNuova.getY()) {
                        mappa[i][j] = '●';
                        break;
                    }

                }

                else System.out.println("Mossa non possibile");*/
            }
        }

    }

    /*public boolean mossaPossibile(char input) {
        if (input == 'n' && !ostacoli.contains(new Coordinata(posCorrente.getX()-1, posCorrente.getY()))) return true;
        if (input == 's' && !ostacoli.contains(new Coordinata(posCorrente.getX()+1, posCorrente.getY()))) return true;
        if (input == 'e' && !ostacoli.contains(new Coordinata(posCorrente.getX(), posCorrente.getY()+1))) return true;
        if (input == 'w' && !ostacoli.contains(new Coordinata(posCorrente.getX(), posCorrente.getY()-1))) return true;
        return false;
    }*/

    private Coordinata posizioneNuova(char input) {
        if (input == 'n') return new Coordinata(posCorrente.getX()-1, posCorrente.getY());
        if (input == 's') return new Coordinata(posCorrente.getX()+1, posCorrente.getY());
        if (input == 'e') return new Coordinata(posCorrente.getX(), posCorrente.getY()+1);
        if (input == 'w') return new Coordinata(posCorrente.getX(), posCorrente.getY()-1);
        return null;
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
