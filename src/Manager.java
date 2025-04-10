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
        List<Utente> utenti = FileManager.leggiUtentiDaCSV();
       
       
        List<Recensione> recensioni = FileManager.leggiRecensioniDaCSV();
        List<Preferito> preferiti = FileManager.leggiPreferitiDaCSV();

        //una volta convertito utente in cliente scommentare ("unione tabelle" utenti con preferiti e recensioni)
        //AggiungiPreferiti(C, preferiti, ristoranti);
        //AggiungiRecensioni(recensioni, C);
        System.out.println(utenti.get(0).toString());
        //List<Cliente> clienti = filtraClienti();
        //List<Ristoratore> ristoratori = filtraRistoratori();


       

        
        //Utente utenteProva = new Utente("FIL", "MOLTEN", "FilMolteni", "password123", LocalDate.of(1990, 6, 15), "cantu", false);

        // Registra l'utente utilizzando la funzione del Manager
        //registraUtente(utenteProva);
        

    }  
    
    public void registraUtente(Utente utente) {
        // Carica la lista esistente di utenti dal file CSV
        List<Utente> utenti = FileManager.caricaOggettiCSV(
                FileManager.getFileUtenti(),
                campi -> {
                    LocalDate dataNascita = (!campi[4].isEmpty())
                            ? LocalDate.parse(campi[4])
                            : LocalDate.of(1900, 1, 1);
    
                    // Crea un oggetto generico di tipo Utente
                    return new Utente(
                            campi[0], // Nome
                            campi[1], // Cognome
                            campi[2], // Username
                            campi[3], // Password cifrata
                            dataNascita,
                            campi[5], // Domicilio
                            Boolean.parseBoolean(campi[6]) // Ruolo
                    );
                });
    
        // Aggiunge il nuovo utente alla lista
        utenti.add(utente);
    
        // Salva la lista aggiornata nel file CSV
        FileManager.salvaOggettiCSV(FileManager.getFileUtenti(), utenti);
    
        // Conferma il successo stampando i dettagli dell'utente registrato
        System.out.println("Utente registrato con i seguenti dati: " + utente.toString());
    }
    
    
    
    public List<Ristorante> ricercaRistoranti(String citta, String tipoCucina, FasciaPrezzo f, String greenStar, boolean servizi) {
        List<Ristorante> temp = new ArrayList<>();
    
        for (Ristorante r : ristoranti) {
            boolean matchCitta = (citta == null || citta.isEmpty() || r.getCitta().equalsIgnoreCase(citta));
            boolean matchCucina = (tipoCucina == null || tipoCucina.isEmpty() || 
                                   r.getTipoCucina() != null && r.getTipoCucina().toLowerCase().contains(tipoCucina.toLowerCase()));
            boolean matchFascia = (f == null || r.getFasciaPrezzo() == f);
    
            boolean matchGreenStar = true;
            if (greenStar != null && !greenStar.isEmpty()) {
                boolean richiesta = greenStar.equalsIgnoreCase("true");
                matchGreenStar = richiesta ? r.getGreenStar() > 0 : r.getGreenStar() == 0;
            }
    
            boolean matchServizi = r.getServizi() != null && r.getServizi() == servizi;
    
            if (matchCitta && matchCucina && matchFascia && matchGreenStar && matchServizi) {
                temp.add(r);
            }
        }
    
        return temp;
    }
    public Utente login(String username, String password) {
        for (Utente utente : utenti) {
            // Confronto username e password decifrata
            if (utente.getUsername().equals(username) && utente.getPasswordCifrata().equals(password)) {
                System.out.println("Accesso effettuato con successo! Benvenuto, " + utente.getNome());
                return utente; // Ritorna l'utente autenticato
            }
        }
        System.out.println("Credenziali non valide. Riprovare.");
        return null; // Nessun utente trovato con le credenziali fornite
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
    public List<Cliente> filtraClienti() {
        List<Cliente> clienti = new ArrayList<>();
        for (Utente utente : this.utenti) {
            if (!utente.ruolo) { // Controlla il ruolo: false per Cliente
                clienti.add((Cliente) utente);
            }
        }
        return clienti;
    }
    
    
    public List<Ristoratore> filtraRistoratori() {
        List<Ristoratore> ristoratori = new ArrayList<>();
        for (Utente utente : this.utenti) {
            if (utente.ruolo) { // Controlla il ruolo: true per Ristoratore
                ristoratori.add((Ristoratore) utente);
            }
        }
        return ristoratori;
    }
    
    
    // public Utente login(String username, String password) {}

    // public void registraCliente() { }
    // public void registraRistoratore() { }
    // public List<Ristorante> cercaRistoranti() { }

    // Altri metodi gestionali
}
