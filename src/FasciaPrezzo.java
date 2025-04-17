package src;

/**
 * Enumerazione che rappresenta le fasce di prezzo dei ristoranti.
 * Ogni fascia è associata a un simbolo e a un nome descrittivo per l'utente.
 */
public enum FasciaPrezzo {
    ECONOMICA("€", "economica"),
    MEDIA("€€", "media"),
    COSTOSA("€€€", "costosa"),
    LUSSO("€€€€", "lusso");

    private final String simbolo;
    private final String nomeUtente;

    /**
     * Costruttore della fascia di prezzo.
     *
     * @param simbolo Il simbolo rappresentativo della fascia di prezzo.
     * @param nomeUtente Il nome descrittivo della fascia di prezzo.
     */
    FasciaPrezzo(String simbolo, String nomeUtente) {
        this.simbolo = simbolo;
        this.nomeUtente = nomeUtente;
    }

    /**
     * Restituisce la fascia di prezzo corrispondente al simbolo fornito.
     *
     * @param s Simbolo della fascia di prezzo.
     * @return FasciaPrezzo corrispondente o null se non trovata.
     */
    public static FasciaPrezzo fromString(String s) {
        for (FasciaPrezzo f : values()) {
            if (f.simbolo.equals(s)) {
                return f;
            }
        }
        return null; // oppure un default, es. ECONOMICA
    }
    
    /**
     * Restituisce la fascia di prezzo corrispondente all'input fornito.
     * L'input può essere il nome, il simbolo o la costante dell'enumerazione.
     *
     * @param input Valore da confrontare con le fasce di prezzo.
     * @return FasciaPrezzo corrispondente o null se non trovata.
     */
    public static FasciaPrezzo fromInput(String input) {
        for (FasciaPrezzo f : values()) {
            if (f.name().equalsIgnoreCase(input) || f.nomeUtente.equalsIgnoreCase(input) || f.simbolo.equals(input)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Restituisce il simbolo della fascia di prezzo.
     *
     * @return Simbolo della fascia di prezzo.
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Restituisce il nome descrittivo della fascia di prezzo.
     *
     * @return Nome descrittivo della fascia di prezzo.
     */
    public String getNomeUtente() {
        return nomeUtente;
    }
}
