package src;
import java.time.LocalDate;
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

       Cliente c = new Cliente("Mario", "Rossi", "MRossi", "password",LocalDate.of(1910, 1, 1), "Milano");
       List<Cliente> L = new ArrayList<Cliente>();
       L.add(c);
       FileManager.salvaOggettiCSV(FileManager.getFileUtenti(), L);

       List<Cliente> clienti = FileManager.caricaOggettiCSV(
            FileManager.getFileUtenti(),
            campi -> {
                LocalDate dataNascita = (!campi[4].isEmpty())
                    ? LocalDate.parse(campi[4])
                    : LocalDate.of(1900, 1, 1);

                return new Cliente(
                    campi[0],
                    campi[1],
                    campi[2],
                    campi[3],
                    dataNascita,
                    campi[5]
                );
        
            }   
        );
        System.out.println(clienti.get(0).toString());


    }
        


    //public Utente login(String username, String password) {}
        
    

    //public void registraCliente() {  }
    //public void registraRistoratore() {  }
    //public List<Ristorante> cercaRistoranti() {  }

    // Altri metodi gestionali
}
