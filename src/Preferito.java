package src;

/**
 * Classe che rappresenta un ristorante preferito da un utente.
 * Implementa l'interfaccia CSVWritable per la serializzazione in formato CSV.
 */
public class Preferito implements CSVWritable {
    private String utente;
    private String ristorante;

    /**
     * Restituisce il nome dell'utente che ha salvato il ristorante nei preferiti.
     *
     * @return Nome dell'utente.
     */
    public String getUtente() {
        return utente;
    }

    /**
     * Imposta il nome dell'utente che ha salvato il ristorante nei preferiti.
     *
     * @param utente Nome dell'utente.
     */
    public void setUtente(String utente) {
        this.utente = utente;
    }

    /**
     * Restituisce il nome del ristorante salvato nei preferiti.
     *
     * @return Nome del ristorante.
     */
    public String getRistorante() {
        return ristorante;
    }

    /**
     * Imposta il nome del ristorante salvato nei preferiti.
     *
     * @param ristorante Nome del ristorante.
     */
    public void setRistorante(String ristorante) {
        this.ristorante = ristorante;
    }

    /**
     * Costruttore della classe Preferito.
     *
     * @param utente Nome dell'utente che ha salvato il ristorante nei preferiti.
     * @param ristorante Nome del ristorante salvato nei preferiti.
     */
    public Preferito(String utente, String ristorante) {
        this.utente = utente;
        this.ristorante = ristorante;
    }

    /**
     * Restituisce una rappresentazione del ristorante preferito in formato CSV.
     *
     * @return Stringa contenente i dati del ristorante preferito separati da virgole.
     */
    @Override
    public String toCSV() {
        return (utente != null ? utente : "") + "," +
               (ristorante != null ? ristorante : "");
    }
}
