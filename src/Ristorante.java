package src;

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
    private Boolean servizi;// delivery e prenotazioni
    private String descrizione;
    private FasciaPrezzo fasciaPrezzo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public String getTipoCucina() {
        return tipoCucina;
    }

    public void setTipoCucina(String tipoCucina) {
        this.tipoCucina = tipoCucina;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public double getGreenStar() {
        return greenStar;
    }

    public void setGreenStar(double greenStar) {
        this.greenStar = greenStar;
    }

    public Boolean getServizi() {
        return servizi;
    }

    public void setServizi(Boolean servizi) {
        this.servizi = servizi;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public FasciaPrezzo getFasciaPrezzo() {
        return fasciaPrezzo;
    }

    public void setFasciaPrezzo(FasciaPrezzo fasciaPrezzo) {
        this.fasciaPrezzo = fasciaPrezzo;
    }

    // Costruttore aggiornato
    public Ristorante(String nome, String nazione, String citta, String indirizzo,
                      double latitudine, double longitudine, String tipoCucina,
                      String telefono, String url, String websiteUrl, String award,
                      double greenStar, Boolean servizi, String descrizione,
                      FasciaPrezzo fasciaPrezzo) {
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

    // Getters e Setters già presenti (omessi qui per brevità)

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
