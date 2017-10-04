public class Main {

    public static void main(String[] args) {
        /*Mappa m = new Mappa("./src/Mappe/mondo1_luogo1");
        System.out.println(m.stampaMappaIniziale());
        //System.out.println(m.posizioneIniziale()[1]);*/
        /*
        Luogo l = new Luogo("./src/Mappe/mondo1_luogo3", 3);
         */
        //System.out.println(l.stampaMappaAggiornata());


        /*l.aggiornaMappa('s');
        System.out.println(l.stampaMappaAggiornata());
        l.aggiornaMappa('s');
        System.out.println(l.stampaMappaAggiornata());
        l.aggiornaMappa('e');
        System.out.println(l.stampaMappaAggiornata());*/

        Mondo m = new Mondo("mondo1");
        m.getMondo().get(m.getPianoCorrente()).muovi(new Coordinata(8, 24));
        m.getMondo().get(m.getPianoCorrente()).aggiornaMappa('e');
        m.cambioLuogo('u');
        System.out.println(m.getMondo().get(m.getPianoCorrente()).stampaMappaAggiornata());
        m.getMondo().get(m.getPianoCorrente()).aggiornaMappa('s');
        m.getMondo().get(m.getPianoCorrente()).aggiornaMappa('n');
        m.cambioLuogo('d');
        System.out.println(m.getMondo().get(m.getPianoCorrente()).stampaMappaAggiornata());

    }
}


