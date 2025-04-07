package src;
import java.util.List;

public class Ristoratore extends Utente {
    private List<Ristorante> ristorantiGestiti;

    @Override
    public String getRuolo() {
        return "ristoratore";
    }
}

