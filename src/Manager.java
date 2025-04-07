package src;
import java.util.List;

public class Manager {
    private List<Utente> utenti;
    private List<Ristorante> ristoranti;

    public void avviaApplicazione() {
        FileManager.inizializzaFile();
        List<Ristorante> ristoranti = FileManager.leggiRistorantiDaCSV();

        if (ristoranti.isEmpty()) {
            System.out.println("❌ Nessun ristorante caricato.");
        } else {
            System.out.println("✅ Ristorante caricato correttamente:");
            System.out.println(ristoranti.get(0));
        }
    }
        


    //public Utente login(String username, String password) {}
        // Login utente
    

    //public void registraCliente() {  }
    //public void registraRistoratore() {  }
    //public List<Ristorante> cercaRistoranti() {  }

    // Altri metodi gestionali
}
