import java.io.*;

public class Mappa {
    private static final int NRIGHE = 10;
    private static final int NCOLONNE = 15;
    private BufferedReader b;
    private String mappa = "";
    private char[][] map = {};


    public Mappa(String nomeFile) {
        try {
            String t;
            b = new BufferedReader(new FileReader(new File(nomeFile)));
            while(true) {
                t = b.readLine();
                if (t==null) break;
                mappa += t + "\n";

            }
            map = grid();

        } catch (FileNotFoundException e) {
            System.err.println("File non trovato!");
        } catch (IOException e) {
            System.err.println("Errore lettura file!");
        }
    }

    public String stampaMappa(char[][] grigliaAggiornata) {
        String mappaContorno = "";
        mappaContorno += "╔═══════════════╗" + "\n";

        for (int i = 0; i < NRIGHE; i++) {
            mappaContorno += "║";
            for (int j = 0; j < NCOLONNE; j++) {
                mappaContorno += grigliaAggiornata[i][j];
            }
            mappaContorno += "║" + "\n";
        }
        mappaContorno += "╚═══════════════╝" + "\n";
        return mappaContorno.replace(".", " ");
    }

    public String stampaMappaIniziale() {
        String mappaContorno = "";
        mappaContorno += "╔═══════════════╗" + "\n";

        for (int i = 0; i < NRIGHE; i++) {
            mappaContorno += "║";
            for (int j = 0; j < NCOLONNE; j++) {
                mappaContorno += map[i][j];
            }
            mappaContorno += "║" + "\n";
        }
        mappaContorno += "╚═══════════════╝" + "\n";
        return mappaContorno.replace(".", " ");
    }

    public String getMappa() {
        return mappa;
    }

    public void setMappa(String mappa) {
        this.mappa = mappa;
    }

    private char[][] grid() {
        char[][] griglia = new char[NRIGHE][NCOLONNE];
        int j = 0;
        int k = 0;
        for(int i = 0; i < mappa.length(); i++) {
            if (mappa.charAt(i) == '\n') {
                j = 0;
                k++;
            }
            else
                griglia[k][j++] = mappa.charAt(i);
        }

        return griglia;
    }

    public int[] posizioneIniziale() {
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (grid()[i][j] == '●') {
                    return new int[] {i, j};
                }
            }
        }
        return new int[0];
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
