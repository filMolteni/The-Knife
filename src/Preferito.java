package src;

public class Preferito {
    private String utente;
    private String ristorante;
    
    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getRistorante() {
        return ristorante;
    }

    public void setRistorante(String ristorante) {
        this.ristorante = ristorante;
    }

    public Preferito(String utente, String ristorante) {
        this.utente = utente;
        this.ristorante = ristorante;
    }
}
