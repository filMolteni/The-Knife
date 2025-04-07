package src;
import java.time.LocalDate;

public class Recensione implements CSVWritable{
    private Cliente autore;
    private Ristorante ristorante;
    private int voto; // 1-5
    private String commento;
    private LocalDate data;
    private String rispostaRistoratore; // opzionale

    // Metodo toCSV
    public String toCSV() {
        return (autore != null ? autore.getUsername() : "") + "," +
               (ristorante != null ? ristorante.getNome() : "") + "," +
               voto + "," +
               (commento != null ? commento.replace(",", ";") : "") + "," +
               (data != null ? data.toString() : "1900-01-01") + "," +
               (rispostaRistoratore != null ? rispostaRistoratore.replace(",", ";") : "");
    }
    
    @Override
    public String toString() {
        return "Recensione{" +
               "autore=" + (autore != null ? autore.getUsername() : "") +
               ", ristorante=" + (ristorante != null ? ristorante.getNome() : "") +
               ", voto=" + voto +
               ", commento='" + (commento != null ? commento : "") + '\'' +
               ", data=" + (data != null ? data.toString() : "non disponibile") +
               ", rispostaRistoratore='" + (rispostaRistoratore != null ? rispostaRistoratore : "") + '\'' +
               '}';
    }
}


