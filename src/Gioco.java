public class Gioco {
    private String nomeGiocatore;
    private Giocatore giocatore;
    private Mondo m;
    private char input;

    public Gioco(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
        m = new Mondo("mondo1");
        this.giocatore = new Giocatore(nomeGiocatore);
        gioca();
    }

    private void gioca() {
        while(true) {
            System.out.println(m.stampaMappa());

            if (m.getMondo().get(m.getPianoCorrente()-1).isChiavePresente() && !m.getMondo().get(m.getPianoCorrente()-1).isChiaveDepositata()) {    // controllo: se la chiave e' presente e se e' depositata
                System.out.println(m.getMondo().get(m.getPianoCorrente()-1).getChiave(m.getMondo().get(m.getPianoCorrente()-1).getPosCorrente()));
                if (MyUtil.controlledCharInput("Vuoi raccogliere la chiave? [s-n]", 's', 'n') == 's') {
                    Chiave c = m.raccogliChiave();
                    giocatore.aggiungiChiave(c);
                    System.out.println("Chiave raccolta!");
                }
                m.getMondo().get(m.getPianoCorrente()-1).setChiavePresente(false);
            }

            String in = MyUtil.stringInput("Dove vuoi muoverti? [n-s-e-w-u-d][x per depositare una chiave][q per uscire]: ");
            checkInput(in);

            if(input == 'n' || input == 's' || input == 'e' || input == 'w')
                m.getMondo().get(m.getPianoCorrente()-1).aggiornaMappa(input);
            else if (input == 'u' || input == 'd')
                m.cambioLuogo(input, giocatore.getChiavi());
            else if (input == 'x') {
                if (!giocatore.getChiavi().isEmpty()) {
                    int i = MyUtil.myMenu("Scegli la chiave da depositare: ", giocatore.getChiavi().toString());
                    Chiave chiaveNuova = giocatore.getChiavi().get(i-1);
                    if(m.getMondo().get(m.getPianoCorrente()-1).posLibera(chiaveNuova)) {
                        m.depositaChiave(chiaveNuova); // aggiunge la chiave dalla mappa
                        giocatore.rimuoviChiave(chiaveNuova);    // rimuove la chiave dalla lista chiavi del giocatore
                        System.out.println("Chiave depositata!");
                    }
                    else System.out.println("La chiave non può essere depositata qui!");
                }
                else System.out.println("Non hai nessuna chiave!");
            }
            else if (input == 'q') System.exit(1);

            if (m.getMondo().get(m.getPianoCorrente()-1).isPassaggioRaggiunto())
                System.out.println("Il passaggio ti porta verso: luogo" + Passaggio.pianoDestPassaggio(m.getMondo().get(m.getPianoCorrente()-1).getLista_passaggi(), m.getMondo().get(m.getPianoCorrente()-1).getPosCorrente()));

            System.out.print("\n\n\n\n\n\n");

            if (m.obbiettivoRaggiunto()) {
                System.out.println(m.stampaMappa());
                System.out.println("Hai vinto!");
                break;
            }
        }
    }

    private void checkInput(String input) {
        if (input.length() != 0)
            this.input = input.charAt(0);
    }


}
