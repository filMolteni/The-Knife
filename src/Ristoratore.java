package src;
import java.time.LocalDate;
import java.util.List;

public class Ristoratore extends Utente {
    public Ristoratore(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
            String domicilio) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio);
        //TODO Auto-generated constructor stub
    }

    private List<Ristorante> ristorantiGestiti;

    @Override
    public String getRuolo() {
        return "ristoratore";
    }
}

