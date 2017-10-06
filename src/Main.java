public class Main {

    public static void main(String[] args) {
        System.out.println("Fancy World");
        String nomeGiocatore = MyUtil.stringInput("Inserisci il tuo nome");
        switch(MyUtil.myMenu("Benvenuto, " + nomeGiocatore + "!", "Gioca", "Istruzioni", "Esci")) {
            case 1: Gioco gioco = new Gioco(nomeGiocatore); break;
            case 2: System.out.println("Istruzioni"); break;
            case 3: System.out.println("Esci!"); System.exit(1); break;
        }
    }
}


