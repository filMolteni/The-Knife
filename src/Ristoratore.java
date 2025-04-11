package src;
import java.time.LocalDate;
import java.util.List;

public class Ristoratore extends Utente implements CSVWritable{
    public Ristoratore(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
            String domicilio) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio, true);
        //TODO Auto-generated constructor stub
    }

    private List<Ristorante> ristorantiGestiti;

    public Ristoratore(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
            String domicilio, boolean ruolo, List<Ristorante> ristorantiGestiti) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio, ruolo);
        this.ristorantiGestiti = ristorantiGestiti;
    }

    public List<Ristorante> getRistorantiGestiti() {
        return ristorantiGestiti;
    }

    public void setRistorantiGestiti(List<Ristorante> ristorantiGestiti) {
        this.ristorantiGestiti = ristorantiGestiti;
    }

    @Override
    public String toCSV() {
        return (nome != null ? nome : "") + "," +
            (cognome != null ? cognome : "") + "," +
            (username != null ? username : "") + "," +
            (passwordCifrata != null ? passwordCifrata : "") + "," +
            (dataNascita != null ? dataNascita.toString() : "1900-01-01") + "," +
            (domicilio != null ? domicilio : ""+ "," + ruolo);
    }

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

