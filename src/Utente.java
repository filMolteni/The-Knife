package src;
import java.time.LocalDate;

public class Utente implements CSVWritable{
    protected String nome;
    protected String cognome;
    protected String username;
    protected String passwordCifrata;
    protected LocalDate dataNascita;
    protected String domicilio;
    protected boolean ruolo;
    
    public boolean isRuolo() {
        return ruolo;
    }

    public void setRuolo(boolean ruolo) {
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordCifrata() {
        return passwordCifrata;
    }

    public void setPasswordCifrata(String passwordCifrata) {
        this.passwordCifrata = passwordCifrata;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

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
