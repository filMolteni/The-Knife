package src;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe che rappresenta un Cliente, estendendo la classe Utente.
 * Implementa l'interfaccia CSVWritable per la serializzazione in formato CSV.
 * Contiene informazioni sui ristoranti preferiti e sulle recensioni effettuate.
 */
public class Cliente extends Utente implements CSVWritable {
    private List<Ristorante> preferiti;
    private List<Recensione> recensioni;

    /**
     * Costruttore della classe Cliente con elenco di preferiti e recensioni.
     *
     * @param nome Nome del cliente.
     * @param cognome Cognome del cliente.
     * @param username Username del cliente.
     * @param passwordCifrata Password cifrata del cliente.
     * @param dataNascita Data di nascita del cliente.
     * @param domicilio Domicilio del cliente.
     * @param ruolo Ruolo del cliente (false, perché è un cliente).
     * @param preferiti Lista dei ristoranti preferiti.
     * @param recensioni Lista delle recensioni fatte dal cliente.
     */
    public Cliente(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
                   String domicilio, boolean ruolo, List<Ristorante> preferiti, List<Recensione> recensioni) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio, ruolo);
        this.preferiti = preferiti;
        this.recensioni = recensioni;
    }

    /**
     * Costruttore della classe Cliente senza elenco di preferiti e recensioni.
     *
     * @param nome Nome del cliente.
     * @param cognome Cognome del cliente.
     * @param username Username del cliente.
     * @param passwordCifrata Password cifrata del cliente.
     * @param dataNascita Data di nascita del cliente.
     * @param domicilio Domicilio del cliente.
     */
    public Cliente(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
                   String domicilio) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio, false);
    }

    /**
     * Restituisce la lista dei ristoranti preferiti del cliente.
     *
     * @return Lista dei ristoranti preferiti.
     */
    public List<Ristorante> getPreferiti() {
        return preferiti;
    }

    /**
     * Imposta la lista dei ristoranti preferiti del cliente.
     *
     * @param preferiti Lista dei ristoranti preferiti.
     */
    public void setPreferiti(List<Ristorante> preferiti) {
        this.preferiti = preferiti;
    }

    /**
     * Restituisce la lista delle recensioni fatte dal cliente.
     *
     * @return Lista delle recensioni.
     */
    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    /**
     * Imposta la lista delle recensioni fatte dal cliente.
     *
     * @param recensioni Lista delle recensioni.
     */
    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    /**
     * Restituisce una rappresentazione testuale del cliente.
     *
     * @return Stringa contenente le informazioni del cliente.
     */
    @Override
    public String toString() {
        return "Cliente {" +
                "nome='" + (nome != null ? nome : "") + '\'' +
                ", cognome='" + (cognome != null ? cognome : "") + '\'' +
                ", username='" + (username != null ? username : "") + '\'' +
                ", data di nascita=" + (dataNascita != null ? dataNascita.toString() : "non disponibile") +
                ", domicilio='" + (domicilio != null ? domicilio : "") + '\'' +
                '}';
    }

    /**
     * Restituisce la rappresentazione del cliente in formato CSV.
     *
     * @return Stringa contenente i dati del cliente separati da virgole.
     */
    @Override
    public String toCSV() {
        return (nome != null ? nome : "") + "," +
               (cognome != null ? cognome : "") + "," +
               (username != null ? username : "") + "," +
               (passwordCifrata != null ? passwordCifrata : "") + "," +
               (dataNascita != null ? dataNascita.toString() : "1900-01-01") + "," +
               (domicilio != null ? domicilio : "") + "," + ruolo;
    }
}
