package src;
import java.time.LocalDate;

public abstract class Utente {
    protected String nome;
    protected String cognome;
    protected String username;
    protected String passwordCifrata;
    protected LocalDate dataNascita;
    protected String domicilio;
    protected boolean ruolo;
    
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

    public abstract String getRuolo();
}
