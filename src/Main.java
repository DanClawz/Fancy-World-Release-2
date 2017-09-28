public class Main {

    public static void main(String[] args) {
        Mappa m = new Mappa("./src/Mappe/mondo1_luogo1");
        System.out.println(m.stampaMappaIniziale());
        System.out.println(m.posizioneIniziale()[1]);
    }
}


