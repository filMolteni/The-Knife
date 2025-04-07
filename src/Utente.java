package src;
import java.time.LocalDate;

public abstract class Utente {
    protected String nome;
    protected String cognome;
    protected String username;
    protected String passwordCifrata;
    protected LocalDate dataNascita;
    protected String domicilio;
    
    public abstract String getRuolo();
}
