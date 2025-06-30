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
     * @param citta Citta' in cui si trova il ristorante.
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
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /** 
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** 
     * @return String
     */
    public String getNazione() {
        return nazione;
    }

    /** 
     * @param nazione
     */
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    /** 
     * @return String
     */
    public String getCitta() {
        return citta;
    }

    /** 
     * @param citta
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }

    /** 
     * @return String
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /** 
     * @param indirizzo
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /** 
     * @return double
     */
    public double getLatitudine() {
        return latitudine;
    }

    /** 
     * @param latitudine
     */
    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    /** 
     * @return double
     */
    public double getLongitudine() {
        return longitudine;
    }

    /** 
     * @param longitudine
     */
    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    /** 
     * @return String
     */
    public String getTipoCucina() {
        return tipoCucina;
    }

    /** 
     * @param tipoCucina
     */
    public void setTipoCucina(String tipoCucina) {
        this.tipoCucina = tipoCucina;
    }

    /** 
     * @return String
     */
    public String getTelefono() {
        return telefono;
    }

    /** 
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /** 
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /** 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 
     * @return String
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /** 
     * @param websiteUrl
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /** 
     * @return String
     */
    public String getAward() {
        return award;
    }

    /** 
     * @param award
     */
    public void setAward(String award) {
        this.award = award;
    }

    /** 
     * @return double
     */
    public double getGreenStar() {
        return greenStar;
    }

    /** 
     * @param greenStar
     */
    public void setGreenStar(double greenStar) {
        this.greenStar = greenStar;
    }

    /** 
     * @return Boolean
     */
    public Boolean getServizi() {
        return servizi;
    }

    /** 
     * @param servizi
     */
    public void setServizi(Boolean servizi) {
        this.servizi = servizi;
    }

    /** 
     * @return String
     */
    public String getDescrizione() {
        return descrizione;
    }

    /** 
     * @param descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /** 
     * @return FasciaPrezzo
     */
    public FasciaPrezzo getFasciaPrezzo() {
        return fasciaPrezzo;
    }

    /** 
     * @param fasciaPrezzo
     */
    public void setFasciaPrezzo(FasciaPrezzo fasciaPrezzo) {
        this.fasciaPrezzo = fasciaPrezzo;
    }

    

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



    /** 
     * @return Utente
     */
    public Utente getGestore() {
       
        throw new UnsupportedOperationException("Unimplemented method 'getGestore'");
    }
}
