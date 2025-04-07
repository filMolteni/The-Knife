import java.util.List;

public class Ristorante {
    private String nome;
    private String nazione;
    private String citta;
    private String indirizzo;
    private double latitudine;
    private double longitudine;
    private String tipoCucina;
    private FasciaPrezzo fasciaPrezzo;
    private boolean prenotazioneDisponibile;
    private boolean asportoDisponibile;
    private List<Recensione> recensioni;
    private Ristoratore proprietario;
    
    public Ristorante(String nome, String nazione, String citta, String indirizzo, double latitudine,
            double longitudine, String tipoCucina, FasciaPrezzo fasciaPrezzo, boolean prenotazioneDisponibile,
            boolean asportoDisponibile, List<Recensione> recensioni, Ristoratore proprietario) {
        this.nome = nome;
        this.nazione = nazione;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.tipoCucina = tipoCucina;
        this.fasciaPrezzo = fasciaPrezzo;
        this.prenotazioneDisponibile = prenotazioneDisponibile;
        this.asportoDisponibile = asportoDisponibile;
        this.recensioni = recensioni;
        this.proprietario = proprietario;
    }

    // double getMediaValutazioni() {} // Calcola media da recensioni
       
    
}
