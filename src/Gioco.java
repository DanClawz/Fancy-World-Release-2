public class Gioco {
    private String nomeGiocatore;
    private Mondo m;
    private char input;
   // private int n;

    public Gioco(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
        m = new Mondo("mondo1");
        gioca();
    }

    private void gioca() {
        while(true) {

            System.out.println(m.stampaMappa());
            /*char in = MyUtil.controlledCharInput("Dove vuoi muoverti? [n-s-e-w-u-d][q per uscire] ", 'n', 's', 'e', 'w', 'u', 'd', 'q');
            this.input = in;*/
            String in = MyUtil.stringInput("Dove vuoi muoverti? [n-s-e-w-u-d][q per uscire]: ");
            checkInput(in);

            //if (input ==)
            if(input == 'n' || input == 's' || input == 'e' || input == 'w') {
                //for (int i = 0; i < n; i++) {
                    m.getMondo().get(m.getPianoCorrente()).aggiornaMappa(input);
                //}
            }
            else if (input == 'u' || input == 'd') {
                m.cambioLuogo(input);
            }
            else if (input == 'q') System.exit(1);


            System.out.print("\n\n\n\n\n\n");

            if (m.obbiettivoRaggiunto()) {
                System.out.println(m.stampaMappa());
                System.out.println("Hai vinto!");
                break;
            }
        }
    }

    private void checkInput(String input) {
        if (input.length() != 0) {
            this.input = input.charAt(0);
            /*if (input.length() == 3) this.n = Integer.parseInt(String.valueOf(input.charAt(2)));
            else if (input.length() == 4)  {
                String t = String.valueOf(input.charAt(2));
                t += String.valueOf(input.charAt(3));
                this.n = Integer.parseInt(String.valueOf(t));*/

            //}
            /*else {
                n = 1;
            }*/
        }
    }


}
