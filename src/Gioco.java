public class Gioco {
    private String nomeGiocatore;
    private Mondo m;

    public Gioco(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
        m = new Mondo("mondo1");
        gioca();
    }

    private void gioca() {
        while(true) {
            System.out.println(m.stampaMappa());;
            char input = MyUtil.controlledCharInput("Dove vuoi muoverti? [n-s-e-w-u-d] ", 'n', 's', 'e', 'w', 'u', 'd');

            if(input == 'n' || input == 's' || input == 'e' || input == 'w') {
                m.getMondo().get(m.getPianoCorrente()).aggiornaMappa(input);
            }
            else if (input == 'u' || input == 'd') {
                m.cambioLuogo(input);
            }

            System.out.print("\n\n\n\n\n\n");

        }
    }
}
