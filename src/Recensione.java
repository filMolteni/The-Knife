package src;
import java.time.LocalDate;

public class Recensione implements CSVWritable{
    private String autore;
    private String ristorante;
    private int voto; // 1-5
    private String commento;
    private LocalDate data;
    private String rispostaRistoratore; // opzionale

    
    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getRistorante() {
        return ristorante;
    }

    public void setRistorante(String ristorante) {
        this.ristorante = ristorante;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getRispostaRistoratore() {
        return rispostaRistoratore;
    }

    public void setRispostaRistoratore(String rispostaRistoratore) {
        this.rispostaRistoratore = rispostaRistoratore;
    }

    public Recensione(String autore, String ristorante, int voto, String commento, LocalDate data,
            String rispostaRistoratore) {
        this.autore = autore;
        this.ristorante = ristorante;
        this.voto = voto;
        this.commento = commento;
        this.data = data;
        this.rispostaRistoratore = rispostaRistoratore;
    }

    // Metodo toCSV
    public String toCSV() {
        return (autore != null ? autore : "") + "," +
               (ristorante != null ? ristorante : "") + "," +
               voto + "," +
               (commento != null ? commento.replace(",", ";") : "") + "," +
               (data != null ? data.toString() : "1900-01-01") + "," +
               (rispostaRistoratore != null ? rispostaRistoratore.replace(",", ";") : "");
    }
    
    @Override
    public String toString() {
        return "Recensione{" +
               "autore=" + (autore != null ? autore : "") +
               ", ristorante=" + (ristorante != null ? ristorante : "") +
               ", voto=" + voto +
               ", commento='" + (commento != null ? commento : "") + '\'' +
               ", data=" + (data != null ? data.toString() : "non disponibile") +
               ", rispostaRistoratore='" + (rispostaRistoratore != null ? rispostaRistoratore : "") + '\'' +
               '}';
    }
}


