package src;
import java.time.LocalDate;

/**
 * Classe che rappresenta un utente all'interno dell'applicazione.
 * Implementa l'interfaccia CSVWritable per la serializzazione in formato CSV.
 */
public class Utente implements CSVWritable {
    protected String nome;
    protected String cognome;
    protected String username;
    protected String passwordCifrata;
    protected LocalDate dataNascita;
    protected String domicilio;
    protected boolean ruolo;

    /**
     * Restituisce il ruolo dell'utente.
     * @return true se l'utente e' un ristoratore, false se e' un cliente.
     */
    public boolean isRuolo() {
        return ruolo;
    }

    /**
     * Imposta il ruolo dell'utente.
     * @param ruolo true se l'utente e' un ristoratore, false se e' un cliente.
     */
    public void setRuolo(boolean ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Restituisce il nome dell'utente.
     * @return Il nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente.
     * @param nome Il nuovo nome dell'utente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     * @return Il cognome dell'utente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'utente.
     * @param cognome Il nuovo cognome dell'utente.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce l'username dell'utente.
     * @return L'username dell'utente.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Imposta l'username dell'utente.
     * @param username Il nuovo username dell'utente.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Restituisce la password cifrata dell'utente.
     * @return La password cifrata dell'utente.
     */
    public String getPasswordCifrata() {
        return passwordCifrata;
    }

    /**
     * Imposta la password cifrata dell'utente.
     * @param passwordCifrata La nuova password cifrata.
     */
    public void setPasswordCifrata(String passwordCifrata) {
        this.passwordCifrata = passwordCifrata;
    }

    /**
     * Restituisce la data di nascita dell'utente.
     * @return La data di nascita dell'utente.
     */
    public LocalDate getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta la data di nascita dell'utente.
     * @param dataNascita La nuova data di nascita.
     */
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Restituisce il domicilio dell'utente.
     * @return Il domicilio dell'utente.
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Imposta il domicilio dell'utente.
     * @param domicilio Il nuovo domicilio dell'utente.
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Costruttore della classe Utente.
     * @param nome Nome dell'utente.
     * @param cognome Cognome dell'utente.
     * @param username Username dell'utente.
     * @param passwordCifrata Password cifrata dell'utente.
     * @param dataNascita Data di nascita dell'utente.
     * @param domicilio Domicilio dell'utente.
     * @param ruolo Ruolo dell'utente (true se ristoratore, false se cliente).
     */
    public Utente(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
                  String domicilio, boolean ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.passwordCifrata = passwordCifrata;
        this.dataNascita = dataNascita;
        this.domicilio = domicilio;
        this.ruolo = ruolo;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Utente.
     * @return Stringa con i dati dell'utente.
     */
    @Override
    public String toString() {
        return "Utente {" +
                "nome='" + (nome != null ? nome : "") + '\'' +
                ", cognome='" + (cognome != null ? cognome : "") + '\'' +
                ", username='" + (username != null ? username : "") + '\'' +
                ", passwordCifrata='" + (passwordCifrata != null ? "******" : "non disponibile") + '\'' +
                ", data di nascita=" + (dataNascita != null ? dataNascita.toString() : "non disponibile") +
                ", domicilio='" + (domicilio != null ? domicilio : "") + '\'' +
                ", ruolo=" + (ruolo ? "Ristoratore" : "Cliente") +
                '}';
    }

    /**
     * Restituisce una rappresentazione dell'oggetto Utente in formato CSV.
     * @return Stringa contenente i dati dell'utente separati da virgole.
     */
    @Override
    public String toCSV() {
        return (nome != null ? nome : "") + "," +
                (cognome != null ? cognome : "") + "," +
                (username != null ? username : "") + "," +
                (passwordCifrata != null ? passwordCifrata : "") + "," +
                (dataNascita != null ? dataNascita.toString() : "1900-01-01") + "," +
                (domicilio != null ? domicilio : "") + "," +
                ruolo;
    }
}
