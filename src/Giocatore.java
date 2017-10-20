import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private ArrayList<Chiave> chiavi;

    public Giocatore(String nome) {
        this.nome = nome;
        chiavi = new ArrayList<Chiave>();
    }

    public void aggiungiChiave(Chiave c) {
        chiavi.add(c);
        System.out.println("Chiave raccolta: " + c.getTipoChiave());
    }

    public void rimuoviChiave(Chiave c) {
        chiavi.remove(c);
    }




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Chiave> getChiavi() {
        return chiavi;
    }

    public void setChiavi(ArrayList<Chiave> chiavi) {
        this.chiavi = chiavi;
    }
}
