public class Chiave {
    private Coordinata posChiave;
    private String tipoChiave;
    private int passaggioDaAprire;

    public Chiave(Coordinata posChiave) {
        this.posChiave = posChiave;
    }

    public int getPassaggioDaAprire() {
        return passaggioDaAprire;
    }

    public void setPassaggioDaAprire(char tipo) {
        switch(tipo) {
            case 'l': this.tipoChiave = "legno"; this.passaggioDaAprire = 3; break;
            case 'f': this.tipoChiave = "ferro"; this.passaggioDaAprire = 4; break;
            case 'b': this.tipoChiave = "bronzo"; this.passaggioDaAprire = 5; break;
            case 'a': this.tipoChiave = "argento"; this.passaggioDaAprire = 6; break;
            case 'o': this.tipoChiave = "oro"; this.passaggioDaAprire = 7; break;
            case 't': this.tipoChiave = "titanite"; this.passaggioDaAprire = 8; break;
            case 'c': this.tipoChiave = "cristallo"; this.passaggioDaAprire = 9; break;
            case 'd': this.tipoChiave = "diamante"; this.passaggioDaAprire = 10; break;
            case 'v': this.tipoChiave = "vibranio"; this.passaggioDaAprire = 11; break;
            case 'm': this.tipoChiave = "misteriosa"; this.passaggioDaAprire = 12; break;
        }
    }



    public Coordinata getPosChiave() {
        return posChiave;
    }

    public void setPosChiave(Coordinata posChiave) {
        this.posChiave = posChiave;
    }

    public String getTipoChiave() {
        return tipoChiave;
    }

    public void setTipoChiave(String tipoChiave) {
        this.tipoChiave = tipoChiave;
    }
}
