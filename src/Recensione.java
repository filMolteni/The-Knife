package src;
import java.time.LocalDate;

/**
 * Classe che rappresenta una recensione di un ristorante.
 * Contiene informazioni sull'autore, il ristorante, il voto, il commento, la data e l'eventuale risposta del ristoratore.
 * Implementa l'interfaccia CSVWritable per la serializzazione in formato CSV.
 */
public class Recensione implements CSVWritable {
    private String autore;
    private String ristorante;
    private int voto; // Valore compreso tra 1 e 5
    private String commento;
    private LocalDate data;
    private String rispostaRistoratore; // Campo opzionale per la risposta del ristoratore

    /**
     * Restituisce il nome dell'autore della recensione.
     * @return Nome dell'autore.
     */
    public String getAutore() {
        return autore;
    }

    /**
     * Imposta il nome dell'autore della recensione.
     * @param autore Nome dell'autore.
     */
    public void setAutore(String autore) {
        this.autore = autore;
    }

    /**
     * Restituisce il nome del ristorante recensito.
     * @return Nome del ristorante.
     */
    public String getRistorante() {
        return ristorante;
    }

    /**
     * Imposta il nome del ristorante recensito.
     * @param ristorante Nome del ristorante.
     */
    public void setRistorante(String ristorante) {
        this.ristorante = ristorante;
    }

    /**
     * Restituisce il voto assegnato nella recensione.
     * @return Voto da 1 a 5.
     */
    public int getVoto() {
        return voto;
    }

    /**
     * Imposta il voto assegnato nella recensione.
     * @param voto Valore da 1 a 5.
     */
    public void setVoto(int voto) {
        this.voto = voto;
    }

    /**
     * Restituisce il commento dell'utente sulla recensione.
     * @return Testo della recensione.
     */
    public String getCommento() {
        return commento;
    }

    /**
     * Imposta il commento dell'utente sulla recensione.
     * @param commento Testo della recensione.
     */
    public void setCommento(String commento) {
        this.commento = commento;
    }

    /**
     * Restituisce la data della recensione.
     * @return Data della recensione.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Imposta la data della recensione.
     * @param data Data della recensione.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Restituisce la risposta del ristoratore alla recensione.
     * @return Testo della risposta, se presente.
     */
    public String getRispostaRistoratore() {
        return rispostaRistoratore;
    }

    /**
     * Imposta la risposta del ristoratore alla recensione.
     * @param rispostaRistoratore Testo della risposta.
     */
    public void setRispostaRistoratore(String rispostaRistoratore) {
        this.rispostaRistoratore = rispostaRistoratore;
    }

    /**
     * Costruttore della classe Recensione.
     *
     * @param autore Nome dell'autore della recensione.
     * @param ristorante Nome del ristorante recensito.
     * @param voto Valutazione da 1 a 5.
     * @param commento Testo della recensione.
     * @param data Data della recensione.
     * @param rispostaRistoratore Risposta del ristoratore (opzionale).
     */
    public Recensione(String autore, String ristorante, int voto, String commento, LocalDate data,
                      String rispostaRistoratore) {
        this.autore = autore;
        this.ristorante = ristorante;
        this.voto = voto;
        this.commento = commento;
        this.data = data;
        this.rispostaRistoratore = rispostaRistoratore;
    }

    /**
     * Restituisce una rappresentazione testuale della recensione.
     *
     * @return Stringa contenente le informazioni della recensione.
     */
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

    /**
     * Restituisce la rappresentazione della recensione in formato CSV.
     *
     * @return Stringa contenente i dati della recensione separati da virgole.
     */
    @Override
    public String toCSV() {
        return (autore != null ? autore : "") + "," +
                (ristorante != null ? ristorante : "") + "," +
                voto + "," +
                (commento != null ? commento.replace(",", ";") : "") + "," +
                (data != null ? data.toString() : "1900-01-01") + "," +
                (rispostaRistoratore != null ? rispostaRistoratore.replace(",", ";") : "");
    }
}
