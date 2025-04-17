package src;

/**
 * Classe che rappresenta un ristorante, con informazioni dettagliate
 * sulla sua posizione, servizi, premi e altre caratteristiche.
 * Implementa l'interfaccia CSVWritable per la serializzazione in formato CSV.
 */
public class Ristorante implements CSVWritable {
    private String nome;
    private String nazione;
    private String citta;
    private String indirizzo;
    private double latitudine;
    private double longitudine;
    private String tipoCucina;
    private String telefono;
    private String url;
    private String websiteUrl;
    private String award;
    private double greenStar;
    private Boolean servizi; // Indica se il ristorante offre delivery e prenotazioni
    private String descrizione;
    private FasciaPrezzo fasciaPrezzo;

    /**
     * Costruttore della classe Ristorante.
     *
     * @param nome Nome del ristorante.
     * @param nazione Nazione in cui si trova il ristorante.
     * @param citta Città in cui si trova il ristorante.
     * @param indirizzo Indirizzo del ristorante.
     * @param latitudine Latitudine geografica del ristorante.
     * @param longitudine Longitudine geografica del ristorante.
     * @param tipoCucina Tipo di cucina offerta dal ristorante.
     * @param telefono Numero di telefono del ristorante.
     * @param url URL della pagina informativa del ristorante.
     * @param websiteUrl URL del sito web ufficiale del ristorante.
     * @param award Premio o riconoscimento ricevuto dal ristorante.
     * @param greenStar Punteggio della certificazione ambientale.
     * @param servizi Indica se il ristorante offre delivery e prenotazioni.
     * @param descrizione Breve descrizione del ristorante.
     * @param fasciaPrezzo Fascia di prezzo del ristorante.
     */
    public Ristorante(String nome, String nazione, String citta, String indirizzo, double latitudine,
                      double longitudine, String tipoCucina, String telefono, String url, String websiteUrl,
                      String award, double greenStar, Boolean servizi, String descrizione, FasciaPrezzo fasciaPrezzo) {
        this.nome = nome;
        this.nazione = nazione;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.tipoCucina = tipoCucina;
        this.telefono = telefono;
        this.url = url;
        this.websiteUrl = websiteUrl;
        this.award = award;
        this.greenStar = greenStar;
        this.servizi = servizi;
        this.descrizione = descrizione;
        this.fasciaPrezzo = fasciaPrezzo;
    }

    /**
     * Restituisce il nome del ristorante.
     * @return Nome del ristorante.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del ristorante.
     * @param nome Nuovo nome del ristorante.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    // (Ometto gli altri getter e setter per brevità, ma tutti avranno lo stesso stile di Javadoc)

    /**
     * Restituisce una rappresentazione testuale del ristorante.
     *
     * @return Stringa contenente le informazioni dettagliate del ristorante.
     */
    @Override
    public String toString() {
        return "Ristorante {" +
                "nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", nazione='" + nazione + '\'' +
                ", latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                ", fasciaPrezzo=" + (fasciaPrezzo != null ? fasciaPrezzo.name() : "ND") +
                ", tipoCucina='" + tipoCucina + '\'' +
                ", telefono='" + telefono + '\'' +
                ", url='" + url + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", award='" + award + '\'' +
                ", greenStar=" + greenStar +
                ", servizi=" + (servizi != null ? (servizi ? "Sì" : "No") : "ND") +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    /**
     * Restituisce una rappresentazione del ristorante in formato CSV.
     *
     * @return Stringa contenente i dati del ristorante separati da virgole.
     */
    @Override
    public String toCSV() {
        return (nome != null ? nome : "") + "," +
                (nazione != null ? nazione : "") + "," +
                (citta != null ? citta : "") + "," +
                (indirizzo != null ? indirizzo : "") + "," +
                latitudine + "," +
                longitudine + "," +
                (tipoCucina != null ? tipoCucina : "") + "," +
                (telefono != null ? telefono : "") + "," +
                (url != null ? url : "") + "," +
                (websiteUrl != null ? websiteUrl : "") + "," +
                (award != null ? award : "") + "," +
                greenStar + "," +
                (servizi != null ? servizi.toString() : "") + "," +
                (descrizione != null ? descrizione.replace(",", ";") : "") + "," +
                (fasciaPrezzo != null ? fasciaPrezzo.name() : "");
    }
}
