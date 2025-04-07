package src;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<Utente> utenti;
    private List<Ristorante> ristoranti;

    public void avviaApplicazione() {
        FileManager.inizializzaFile();
        List<Ristorante> ristoranti = FileManager.leggiRistorantiDaCSV();

        if (ristoranti.isEmpty()) {
            System.out.println("Nessun ristorante caricato.");
        } else {
            System.out.println("Ristorante caricato correttamente:");
            System.out.println(ristoranti.get(0));
        }

       Cliente c = new Cliente("Mario", "Rossi", "MRossi", "password", null, null);
       List<Cliente> L = new ArrayList<Cliente>();
       L.add(c);
       //FileManager.salvaOggetti(FileManager.getFileRecensioni(), L);
    }
        


    //public Utente login(String username, String password) {}
        
    

    //public void registraCliente() {  }
    //public void registraRistoratore() {  }
    //public List<Ristorante> cercaRistoranti() {  }

    // Altri metodi gestionali
}
