public class Main {

    public static void main(String[] args) {
        /*Mappa m = new Mappa("./src/Mappe/mondo1_luogo1");
        System.out.println(m.stampaMappaIniziale());
        //System.out.println(m.posizioneIniziale()[1]);*/
        Luogo l = new Luogo("./src/Mappe/mondo1_luogo1");
        System.out.println(l.stampaMappaAggiornata());

        l.aggiornaMappa('n');
        System.out.println(l.stampaMappaAggiornata());
        l.aggiornaMappa('e');
        System.out.println(l.stampaMappaAggiornata());
        l.aggiornaMappa('s');
        System.out.println(l.stampaMappaAggiornata());
        l.aggiornaMappa('w');
        System.out.println(l.stampaMappaAggiornata());

    }
}


