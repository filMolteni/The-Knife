package src;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe che rappresenta un Ristoratore, estendendo la classe Utente.
 * Implementa l'interfaccia CSVWritable per la serializzazione in formato CSV.
 */
public class Ristoratore extends Utente implements CSVWritable {

    private List<Ristorante> ristorantiGestiti;

    /**
     * Costruttore della classe Ristoratore.
     * Inizializza il ristoratore con i dati personali e imposta il ruolo come vero (ristoratore).
     *
     * @param nome          Nome del ristoratore.
     * @param cognome       Cognome del ristoratore.
     * @param username      Username del ristoratore.
     * @param passwordCifrata Password cifrata del ristoratore.
     * @param dataNascita   Data di nascita del ristoratore.
     * @param domicilio     Domicilio del ristoratore.
     */
    public Ristoratore(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
                       String domicilio) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio, true);
    }

    /**
     * Costruttore della classe Ristoratore con lista di ristoranti gestiti.
     *
     * @param nome              Nome del ristoratore.
     * @param cognome           Cognome del ristoratore.
     * @param username          Username del ristoratore.
     * @param passwordCifrata   Password cifrata del ristoratore.
     * @param dataNascita       Data di nascita del ristoratore.
     * @param domicilio         Domicilio del ristoratore.
     * @param ruolo             Ruolo del ristoratore (sempre true).
     * @param ristorantiGestiti Lista dei ristoranti gestiti dal ristoratore.
     */
    public Ristoratore(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
                       String domicilio, boolean ruolo, List<Ristorante> ristorantiGestiti) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio, ruolo);
        this.ristorantiGestiti = ristorantiGestiti;
    }

    /**
     * Restituisce la lista di ristoranti gestiti dal ristoratore.
     *
     * @return Lista di oggetti Ristorante gestiti dal ristoratore.
     */
    public List<Ristorante> getRistorantiGestiti() {
        return ristorantiGestiti;
    }

    /**
     * Imposta la lista di ristoranti gestiti dal ristoratore.
     *
     * @param ristorantiGestiti Lista di ristoranti gestiti.
     */
    public void setRistorantiGestiti(List<Ristorante> ristorantiGestiti) {
        this.ristorantiGestiti = ristorantiGestiti;
    }

    /**
     * Restituisce la rappresentazione dell'oggetto Ristoratore in formato CSV.
     *
     * @return Stringa contenente i dati del ristoratore separati da virgole.
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

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Ristoratore.
     *
     * @return Stringa contenente le informazioni del ristoratore.
     */
    @Override
    public String toString() {
        return "Ristoratore {" +
               "nome='" + (nome != null ? nome : "") + '\'' +
               ", cognome='" + (cognome != null ? cognome : "") + '\'' +
               ", username='" + (username != null ? username : "") + '\'' +
               ", data di nascita=" + (dataNascita != null ? dataNascita.toString() : "non disponibile") +
               ", domicilio='" + (domicilio != null ? domicilio : "") + '\'' +
               '}';
    }
}
