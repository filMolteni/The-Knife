package src;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Utente implements CSVWritable {
    // Campi privati
    private List<Ristorante> preferiti;
    private List<Recensione> recensioni;

    // Costruttore
    public Cliente(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
                   String domicilio) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio);
    }

    // Getter e Setter per preferiti
    public List<Ristorante> getPreferiti() {
        return preferiti;
    }

    public void setPreferiti(List<Ristorante> preferiti) {
        this.preferiti = preferiti;
    }

    // Getter e Setter per recensioni
    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    // Getter e Setter per nome, cognome, username, passwordCifrata, dataNascita, domicilio (ereditati dalla classe Utente)
    
    // Getter e Setter per nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter per cognome
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    // Getter e Setter per username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter e Setter per passwordCifrata
    public String getPasswordCifrata() {
        return passwordCifrata;
    }

    public void setPasswordCifrata(String passwordCifrata) {
        this.passwordCifrata = passwordCifrata;
    }

    // Getter e Setter per dataNascita
    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    // Getter e Setter per domicilio
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    // Metodo toString
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

    // Metodo getRuolo
    @Override
    public String getRuolo() {
        return "cliente";
    }

    // Metodo toCSV per serializzare l'oggetto Cliente in formato CSV
    @Override
    public String toCSV() {
        return (nome != null ? nome : "") + "," +
            (cognome != null ? cognome : "") + "," +
            (username != null ? username : "") + "," +
            (passwordCifrata != null ? passwordCifrata : "") + "," +
            (dataNascita != null ? dataNascita.toString() : "1900-01-01") + "," +
            (domicilio != null ? domicilio : "");
    }
}


