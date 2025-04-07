package src;
import java.io.Serializable;

public class Ristorante implements CSVWritable{
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
    private String greenStar;
    private String servizi;
    private String descrizione;

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

    public String getGreenStar() {
        return greenStar;
    }

    public void setGreenStar(String greenStar) {
        this.greenStar = greenStar;
    }

    public String getServizi() {
        return servizi;
    }

    public void setServizi(String servizi) {
        this.servizi = servizi;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Ristorante(String nome, String nazione, String citta, String indirizzo,
                      double latitudine, double longitudine, String tipoCucina,
                      String telefono, String url, String websiteUrl, String award,
                      String greenStar, String servizi, String descrizione) {
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
    }

    @Override
    public String toString() {
        return String.format("🍽️ %s\n📍 %s, %s (%s)\n🧭 [%.5f, %.5f]\n🍝 Cucina: %s\n📞 Telefono: %s\n🌐 URL: %s\n🏅 Award: %s\n🌱 Green Star: %s\n⚙ Servizi: %s\n📝 Descrizione: %s\n",
            nome, indirizzo, citta, nazione, latitudine, longitudine, tipoCucina, telefono, url, websiteUrl, award, greenStar, servizi, descrizione);
    }

    // Implementazione del metodo toCSV per serializzare l'oggetto in formato CSV
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
            (greenStar != null ? greenStar : "") + "," +
            (servizi != null ? servizi : "") + "," +
            (descrizione != null ? descrizione : "");
    }
}

