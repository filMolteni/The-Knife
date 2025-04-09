package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Manager {
    private List<Utente> utenti;
    private List<Ristorante> ristoranti;

    public void avviaApplicazione() {
        FileManager.inizializzaFile();
        List<Ristorante> ristoranti = FileManager.leggiRistorantiDaCSV();
        Map<String, List<Utente>> utenti = FileManager.leggiUtentiDaCSV();
        // TO-DO parsare gli oggetti da utente a cliente e ristoratore
        List<Utente> C = utenti.get("Clienti");
        List<Utente> R = utenti.get("Ristoratore");
        List<Recensione> recensioni = FileManager.leggiRecensioniDaCSV();
        List<Preferito> preferiti = FileManager.leggiPreferitiDaCSV();

        //una volta convertito utente in cliente scommentare ("unione tabelle" utenti con preferiti e recensioni)
        //AggiungiPreferiti(C, preferiti, ristoranti);
        //AggiungiRecensioni(recensioni, C);

        if (ristoranti.isEmpty()) {
            System.out.println("Nessun ristorante caricato.");
        } else {
            System.out.println("Ristorante caricato correttamente:");
            System.out.println(ristoranti.get(0));
        }

        Cliente c = new Cliente("Mario", "Rossi", "MRossi", "password", LocalDate.of(1910, 1, 1), "Milano");

        C.add(c);
        // FileManager.salvaOggettiCSV(FileManager.getFileUtenti(), C);

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
                            campi[5]);

                });
        System.out.println(clienti.get(0).toString());

    }

    // aggiungo i preferiti di ogni cliente al suo oggetti
    public void AggiungiPreferiti(List<Preferito> preferiti, List<Cliente> C, List<Ristorante> ristoranti) {
        // per ogni cliente
        for (Cliente c : C) {
            List<Ristorante> temp = new ArrayList<Ristorante>();
            // scorro tutti i preferiti e se il nome utente coincide
            for (Preferito p : preferiti) {
                if (c.username == p.getUtente()) {
                    // scorro tutti i ristoranti e inserisco in temp il ristorante con lo stesso
                    // nome
                    for (Ristorante r : ristoranti) {
                        if (p.getRistorante() == r.getNome()) {
                            temp.add(r);
                        }

                    }
                }
            }
            // una volta che ho passato tutti i preferiti e ho aggiunto tutti i ristoranti a
            // temp li assegno all'utente
            c.setPreferiti(temp);
        }
    }

    public void AggiungiRecensioni(List<Recensione> recensione, List<Cliente> C) {
        // per ogni cliente
        for (Cliente c : C) {
            List<Recensione> temp = new ArrayList<Recensione>();
            // scorro tutte le recensioni e se il nome utente coincide lo aggiungo alla lista delle sue recensioni
            for (Recensione r : recensione) {
                if (c.username == r.getAutore()) {
                        temp.add(r);
                }
            }
            // una volta che ho passato tutte le recensioni e le ho aggiunte a temp li assegno all'utente
            c.setRecensioni(temp);
        }
    }

    // public Utente login(String username, String password) {}

    // public void registraCliente() { }
    // public void registraRistoratore() { }
    // public List<Ristorante> cercaRistoranti() { }

    // Altri metodi gestionali
}
