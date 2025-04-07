package src;
import java.io.Serializable;

public class Ristorante implements Serializable {
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
}
