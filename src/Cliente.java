package src;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Utente {
    public Cliente(String nome, String cognome, String username, String passwordCifrata, LocalDate dataNascita,
            String domicilio) {
        super(nome, cognome, username, passwordCifrata, dataNascita, domicilio);
        //TODO Auto-generated constructor stub
    }

    private List<Ristorante> preferiti;
    private List<Recensione> recensioni;

    @Override
    public String getRuolo() {
        return "cliente";
    }
}

