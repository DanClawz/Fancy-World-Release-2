import java.io.*;

public class Mappa {
    private BufferedReader b;
    private String mappa = "";
    private String mappaContorno = "";

    public Mappa(String nomeFile) {
        try {
            String t = "";
            b = new BufferedReader(new FileReader(new File(nomeFile)));
            mappaContorno += " _______________" + "\n";

            while(true) {
                t = b.readLine();
                if (t==null) break;
                mappa += t + "\n";
                mappaContorno += "|" + t + "|" + "\n";
            }
            mappaContorno += " ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻";

        } catch (FileNotFoundException e) {
            System.err.println("File non trovato!");
        } catch (IOException e) {
            System.err.println("Errore lettura file!");
        }
    }

    public String toString() {
        return mappaContorno.replace(".", "░");
    }

    public String toStringModificato() {
        return mappa.replace(".", " ");
    }

}
