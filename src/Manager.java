package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Manager {
    private List<Utente> utenti;
    private List<Ristorante> ristoranti;
    private List<Recensione> recensioni;
    private List<Preferito> preferiti;
    private List<Preferito> gestiti ;
    private List<Cliente> clienti ;
    private List<Ristoratore> ristoratori;
    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    public List<Ristorante> getRistoranti() {
        return ristoranti;
    }

    public void setRistoranti(List<Ristorante> ristoranti) {
        this.ristoranti = ristoranti;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public List<Preferito> getPreferiti() {
        return preferiti;
    }

    public void setPreferiti(List<Preferito> preferiti) {
        this.preferiti = preferiti;
    }

    public List<Preferito> getGestiti() {
        return gestiti;
    }

    public void setGestiti(List<Preferito> gestiti) {
        this.gestiti = gestiti;
    }

    public List<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public List<Ristoratore> getRistoratori() {
        return ristoratori;
    }

    public void setRistoratori(List<Ristoratore> ristoratori) {
        this.ristoratori = ristoratori;
    }
    private Utente Logged;

    public void avviaApplicazione() {
        FileManager.inizializzaFile();
        this.ristoranti = FileManager.leggiRistorantiDaCSV();
        this.utenti = FileManager.leggiUtentiDaCSV();
       
        this.recensioni = FileManager.leggiRecensioniDaCSV();
        this.preferiti = FileManager.leggiPreferitiDaCSV();
        this.gestiti = FileManager.leggiGestitiDaCSV();
        this.clienti = filtraClienti();
        CaricaDinamicaPreferiti();
        CaricaDinamicaRecensioni();
        this.ristoratori = filtraRistoratori();
        CaricaDinamicaRistorantiGestiti();
        
    }  

    public List<Cliente> filtraClienti() {
        List<Cliente> c = new ArrayList<>();
        for (Utente utente : this.utenti) {
            if (!utente.ruolo) { // false = Cliente
                Cliente cliente = new Cliente(
                    utente.nome,
                    utente.cognome,
                    utente.username,
                    utente.passwordCifrata,
                    utente.dataNascita,
                    utente.domicilio
                );
                c.add(cliente);
            }
        }
        return c;
    }
    
    public List<Ristoratore> filtraRistoratori() {
        List<Ristoratore> r = new ArrayList<>();
        for (Utente utente : this.utenti) {
            if (utente.ruolo) { // true = Ristoratore
                Ristoratore ristoratore = new Ristoratore(
                    utente.nome,
                    utente.cognome,
                    utente.username,
                    utente.passwordCifrata,
                    utente.dataNascita,
                    utente.domicilio
                );
                r.add(ristoratore);
            }
        }
        return r;
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

    public void aggiungiPreferitoAlClienteLoggato(Ristorante ristorante) {
        if (!(Logged instanceof Cliente)) {
            System.out.println("Solo un cliente può avere preferiti.");
            return;
        }
        Cliente cliente = (Cliente) Logged;
        
        // Inizializza la lista se è null
        if (cliente.getPreferiti() == null) {
            cliente.setPreferiti(new ArrayList<>());
        }
    
        List<Ristorante> preferitiCliente = cliente.getPreferiti();
    
        boolean giàPresente = preferitiCliente.stream()
            .anyMatch(r -> r.getNome().equalsIgnoreCase(ristorante.getNome()) &&
                           r.getIndirizzo().equalsIgnoreCase(ristorante.getIndirizzo()));
    
        if (!giàPresente) {
            preferitiCliente.add(ristorante);
            this.preferiti.add(new Preferito(cliente.getUsername(), ristorante.getNome())); // aggiorna lista globale
            FileManager.salvaOggettiCSV(FileManager.getFileRistoranti(), preferiti); // salva preferiti su file
            System.out.println("Ristorante aggiunto ai preferiti.");
        } else {
            System.out.println("Il ristorante è già nei preferiti.");
        }
    }
    
    public void aggiungiRecensione(Ristorante ristorante, int voto, String commento) {
    if (!(Logged instanceof Cliente)) {
        System.out.println("Solo i clienti possono aggiungere recensioni.");
        return;
    }

    Cliente cliente = (Cliente) Logged;

    // Crea nuova recensione
    Recensione recensione = new Recensione(
        cliente.getUsername(),
        ristorante.getNome(),
        voto,
        commento,
        LocalDate.now(),     // data attuale
        null                 // rispostaRistoratore inizialmente nulla
    );

    // Aggiunge alla lista personale del cliente
    if (cliente.getRecensioni() == null) {
        cliente.setRecensioni(new ArrayList<>());
    }
    cliente.getRecensioni().add(recensione);

    // Aggiunge alla lista globale
    this.recensioni.add(recensione);

    // Salva su file
    FileManager.salvaOggettiCSV(FileManager.getFileRecensioni(), recensioni);

    System.out.println("Recensione aggiunta con successo per " + ristorante.getNome());
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
    
    public void login(String username, String password) {
        for (Utente utente : utenti) {
            if (utente.getUsername().equals(username) && utente.getPasswordCifrata().equals(password)) {
                Utente utenteAutenticato;
    
                if (utente.isRuolo()) {
                    // true = Cliente
                    utenteAutenticato = new Cliente(
                        utente.getNome(),
                        utente.getCognome(),
                        utente.getUsername(),
                        utente.getPasswordCifrata(),
                        utente.getDataNascita(),
                        utente.getDomicilio()
                    );
                } else {
                    // false = Ristoratore
                    utenteAutenticato = new Ristoratore(
                        utente.getNome(),
                        utente.getCognome(),
                        utente.getUsername(),
                        utente.getPasswordCifrata(),
                        utente.getDataNascita(),
                        utente.getDomicilio()
                    );
                }
    
                this.Logged = utenteAutenticato;
                System.out.println("Accesso effettuato con successo! Benvenuto, " + utenteAutenticato.getNome());
                return;
            }
        }
    
        System.out.println("Credenziali non valide. Riprovare.");
        return ;
    }
    
    
    public void CaricaDinamicaPreferiti() {
        
        // per ogni cliente
        for (Cliente c : clienti) {
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
    
    public void CaricaDinamicaRecensioni() {
        // per ogni cliente
        for (Cliente c : clienti) {
            List<Recensione> temp = new ArrayList<Recensione>();
            // scorro tutte le recensioni e se il nome utente coincide lo aggiungo alla lista delle sue recensioni
            for (Recensione r : recensioni) {
                if (c.username == r.getAutore()) {
                        temp.add(r);
                }
            }
            // una volta che ho passato tutte le recensioni e le ho aggiunte a temp li assegno all'utente
            c.setRecensioni(temp);
        }
    }
    
    public void CaricaDinamicaRistorantiGestiti() {
        
        // per ogni cliente
        for (Ristoratore r : ristoratori) {
            List<Ristorante> temp = new ArrayList<Ristorante>();
            // scorro tutti i preferiti e se il nome utente coincide
            for (Preferito p : gestiti) {
                if (r.username == p.getUtente()) {
                    // scorro tutti i ristoranti e inserisco in temp il ristorante con lo stesso
                    // nome
                    for (Ristorante ristorante : ristoranti) {
                        if (p.getRistorante() == ristorante.getNome()) {
                            temp.add(ristorante);
                        }

                    }
                }
            }
            // una volta che ho passato tutti i preferiti e ho aggiunto tutti i ristoranti a
            // temp li assegno all'utente
            r.setRistorantiGestiti(temp);
        }
    }

    public Utente getLogged() {
        return Logged;
    }
    public void setLogged(Utente logged) {
        Logged = logged;
    }
    
    
   
}
