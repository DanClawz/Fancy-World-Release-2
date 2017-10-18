public class Chiave {
    private Coordinata posChiave;
    private String tipoChiave;

    public Chiave(String tipoChiave, Coordinata posChiave) {
        this.tipoChiave = tipoChiave;
        this.posChiave = posChiave;
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
