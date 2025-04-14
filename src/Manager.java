package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Manager {
    private List<Utente> utenti;
    private List<Ristorante> ristoranti;
    private List<Recensione> recensioni;
    private List<Preferito> preferiti;
    private List<Preferito> gestiti ;
    private List<Cliente> clienti ;
    private List<Ristoratore> ristoratori;
    private Utente Logged;
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
            if (utente.ruolo) { // true = Cliente
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
            if (!utente.ruolo) { // false = Ristoratore
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
                    utenteAutenticato = new Cliente(
                        utente.getNome(),
                        utente.getCognome(),
                        utente.getUsername(),
                        utente.getPasswordCifrata(),
                        utente.getDataNascita(),
                        utente.getDomicilio()
                    );
                } else {
                    utenteAutenticato = new Ristoratore(
                        utente.getNome(),
                        utente.getCognome(),
                        utente.getUsername(),
                        utente.getPasswordCifrata(),
                        utente.getDataNascita(),
                        utente.getDomicilio()
                    );
                }
    
                // Carica preferiti/recensioni o ristoranti gestiti
                completaUtenteLoggato(utenteAutenticato);
    
                this.Logged = utenteAutenticato;
                System.out.println("Accesso effettuato con successo! Benvenuto, " + utenteAutenticato.getNome());
                return;
            }
        }
    
        System.out.println("Credenziali non valide. Riprovare.");
    }
    
    
    private void completaUtenteLoggato(Utente utenteAutenticato) {
        if (utenteAutenticato instanceof Cliente) {
            for (Cliente c : clienti) {
                if (c.getUsername().equals(utenteAutenticato.getUsername())) {
                    ((Cliente) utenteAutenticato).setPreferiti(c.getPreferiti());
                    ((Cliente) utenteAutenticato).setRecensioni(c.getRecensioni());
                    break;
                }
            }
        } else if (utenteAutenticato instanceof Ristoratore) {
            for (Ristoratore r : ristoratori) {
                if (r.getUsername().equals(utenteAutenticato.getUsername())) {
                    ((Ristoratore) utenteAutenticato).setRistorantiGestiti(r.getRistorantiGestiti());
                    break;
                }
            }
        }
    }
    
    public void CaricaDinamicaRecensioni() {
        for (Cliente c : clienti) {
            List<Recensione> recensioniCliente = new ArrayList<>();
    
            for (Recensione r : recensioni) {
                if (r.getAutore() != null && r.getAutore().equals(c.getUsername())) {
                    recensioniCliente.add(r);
                }
            }
    
            c.setRecensioni(recensioniCliente);
        }
    }
     
    public void CaricaDinamicaPreferiti() {
        for (Cliente c : clienti) {
            List<Ristorante> preferitiCliente = new ArrayList<>();
    
            for (Preferito p : preferiti) {
                if (p.getUtente() != null && p.getUtente().equals(c.getUsername())) {
                    for (Ristorante r : ristoranti) {
                        if (p.getRistorante() != null && p.getRistorante().equals(r.getNome())) {
                            preferitiCliente.add(r);
                            break; // trovato, evitiamo cicli inutili
                        }
                    }
                }
            }
    
            c.setPreferiti(preferitiCliente);
        }
    }
    public void CaricaDinamicaGestiti() {
        for (Ristoratore ristoratore : ristoratori) {
            List<Ristorante> gestitiRistoratore = new ArrayList<>();
    
            for (Preferito g : gestiti) {
                if (g.getUtente() != null && g.getUtente().equals(ristoratore.getUsername())) {
                    for (Ristorante r : ristoranti) {
                        if (g.getRistorante() != null && g.getRistorante().equals(r.getNome())) {
                            gestitiRistoratore.add(r);
                            break;
                        }
                    }
                }
            }
    
            ristoratore.setRistorantiGestiti(gestitiRistoratore);
        }
    }
    
    
    public void CaricaDinamicaRistorantiGestiti() {
        for (Ristoratore ristoratore : ristoratori) {
            List<Ristorante> ristorantiGestiti = new ArrayList<>();
    
            for (Preferito p : gestiti) {
                if (p.getUtente() != null && p.getUtente().equals(ristoratore.getUsername())) {
                    Ristorante rTrovato = null;
    
                    for (Ristorante r : ristoranti) {
                        if (p.getRistorante() != null && p.getRistorante().equals(r.getNome())) {
                            rTrovato = r;
                            break;
                        }
                    }
    
                    // Se non trovato nella lista generale, crea un oggetto minimo fittizio
                    if (rTrovato == null) {
                        rTrovato = new Ristorante(
                            p.getRistorante(), "", "", "",
                            0.0, 0.0, "", "", "", "", "",
                            0.0, false, ""); // valori placeholder
                        ristoranti.add(rTrovato); // aggiunta alla lista globale
                    }
    
                    // Aggiunta alla lista personale
                    ristorantiGestiti.add(rTrovato);
                }
            }
    
            ristoratore.setRistorantiGestiti(ristorantiGestiti);
        }
    }
    

    public Utente getLogged() {
        return Logged;
    }
    public void setLogged(Utente logged) {
        Logged = logged;
    }
    
    public void modificaRecensione(Scanner sc) {
    if (!(Logged instanceof Cliente)) {
        System.out.println("Solo un cliente può modificare le proprie recensioni.");
        return;
    }

    Cliente cliente = (Cliente) Logged;
    List<Recensione> recensioniCliente = cliente.getRecensioni();

    if (recensioniCliente == null || recensioniCliente.isEmpty()) {
        System.out.println("Non hai recensioni da modificare.");
        return;
    }

    System.out.println("Le tue recensioni:");
    for (int i = 0; i < recensioniCliente.size(); i++) {
        Recensione r = recensioniCliente.get(i);
        System.out.printf("[%d] %s - Voto: %d - \"%s\"\n", i + 1, r.getRistorante(), r.getVoto(), r.getCommento());
    }

    System.out.print("Seleziona il numero della recensione da modificare: ");
    int scelta;
    try {
        scelta = Integer.parseInt(sc.nextLine()) - 1;
    } catch (NumberFormatException e) {
        System.out.println("Input non valido.");
        return;
    }

    if (scelta < 0 || scelta >= recensioniCliente.size()) {
        System.out.println("Numero non valido.");
        return;
    }

    Recensione daModificare = recensioniCliente.get(scelta);

    System.out.print("Inserisci il nuovo voto (1-5): ");
    int nuovoVoto;
    try {
        nuovoVoto = Integer.parseInt(sc.nextLine());
        if (nuovoVoto < 1 || nuovoVoto > 5) throw new NumberFormatException();
    } catch (NumberFormatException e) {
        System.out.println("Voto non valido.");
        return;
    }

    System.out.print("Inserisci il nuovo commento: ");
    String nuovoCommento = sc.nextLine();

    // Modifica in memoria
    daModificare.setVoto(nuovoVoto);
    daModificare.setCommento(nuovoCommento);
    daModificare.setData(LocalDate.now()); // aggiorna data ultima modifica

    // Salva tutto su file
    FileManager.salvaOggettiCSV(FileManager.getFileRecensioni(), recensioni);
    System.out.println("Recensione modificata con successo.");
}

    public void cancellaRecensione(Scanner sc) {
    if (!(Logged instanceof Cliente)) {
        System.out.println("Solo un cliente può cancellare le proprie recensioni.");
        return;
    }

    Cliente cliente = (Cliente) Logged;
    List<Recensione> recensioniCliente = cliente.getRecensioni();

    if (recensioniCliente == null || recensioniCliente.isEmpty()) {
        System.out.println("Non hai recensioni da cancellare.");
        return;
    }

    System.out.println("Le tue recensioni:");
    for (int i = 0; i < recensioniCliente.size(); i++) {
        Recensione r = recensioniCliente.get(i);
        System.out.printf("[%d] %s - Voto: %d - \"%s\"\n", i + 1, r.getRistorante(), r.getVoto(), r.getCommento());
    }

    System.out.print("Seleziona il numero della recensione da cancellare: ");
    int scelta;
    try {
        scelta = Integer.parseInt(sc.nextLine()) - 1;
    } catch (NumberFormatException e) {
        System.out.println("Input non valido.");
        return;
    }

    if (scelta < 0 || scelta >= recensioniCliente.size()) {
        System.out.println("Numero non valido.");
        return;
    }

    Recensione daRimuovere = recensioniCliente.get(scelta);

    // Rimuove dalla lista personale
    recensioniCliente.remove(scelta);

    // Rimuove anche dalla lista globale
    recensioni.removeIf(r ->
        r.getAutore().equals(cliente.getUsername()) &&
        r.getRistorante().equals(daRimuovere.getRistorante()) &&
        r.getCommento().equals(daRimuovere.getCommento())
    );

    // Salva il nuovo stato
    FileManager.salvaOggettiCSV(FileManager.getFileRecensioni(), recensioni);
    System.out.println("Recensione rimossa con successo.");
}

public void inserisciRistorante(Scanner sc) {
    if (!(Logged instanceof Ristoratore)) {
        System.out.println("Solo un ristoratore può inserire un ristorante.");
        return;
    }

    Ristoratore ristoratore = (Ristoratore) Logged;

    try {
        System.out.print("Nome del ristorante: ");
        String nome = sc.nextLine();

        System.out.print("Indirizzo: ");
        String indirizzo = sc.nextLine();

        System.out.print("Città: ");
        String citta = sc.nextLine();

        System.out.print("Nazione: ");
        String nazione = sc.nextLine();

        System.out.print("Latitudine (es. 45.4642): ");
        double lat = Double.parseDouble(sc.nextLine());

        System.out.print("Longitudine (es. 9.1900): ");
        double lon = Double.parseDouble(sc.nextLine());

        System.out.print("Tipo di cucina: ");
        String tipoCucina = sc.nextLine();

        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        System.out.print("URL guida (opzionale): ");
        String url = sc.nextLine();

        System.out.print("Sito ufficiale (opzionale): ");
        String website = sc.nextLine();

        System.out.print("Premio (es. '1 Star', 'Selected Restaurants', ecc.): ");
        String award = sc.nextLine();

        System.out.print("Green Star (0 se non presente): ");
        double greenStar = Double.parseDouble(sc.nextLine());

        System.out.print("Servizi disponibili? (true/false): ");
        boolean servizi = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Descrizione: ");
        String descrizione = sc.nextLine();

        

        System.out.print("Fascia di prezzo (economica, media, costosa, lusso oppure €, €€, €€€, €€€€): ");
        String fasciaInput = sc.nextLine().trim();

        FasciaPrezzo fasciaPrezzo = FasciaPrezzo.fromInput(fasciaInput);
        if (fasciaPrezzo == null) {
            System.out.println("Fascia non valida. Inserire: economica, media, costosa, lusso oppure simboli da € a €€€€");
            return;
        }


        // Crea ristorante
        Ristorante nuovo = new Ristorante(
            nome, nazione, citta, indirizzo,
            lat, lon, tipoCucina, telefono,
            url, website, award, greenStar,
            servizi, descrizione
        );
        nuovo.setFasciaPrezzo(fasciaPrezzo);

        // Aggiungi a lista globale
        ristoranti.add(nuovo);

        // Aggiungi alla lista gestita dal ristoratore
        if (ristoratore.getRistorantiGestiti() == null)
            ristoratore.setRistorantiGestiti(new ArrayList<>());
        ristoratore.getRistorantiGestiti().add(nuovo);

        // Aggiungi anche alla lista gestiti globale
        gestiti.add(new Preferito(ristoratore.getUsername(), nuovo.getNome()));

        // Salva tutto su file
        FileManager.salvaOggettiCSV(FileManager.getFileRistoranti(), ristoranti);
        FileManager.salvaOggettiCSV(FileManager.getFileRistorantiGestiti(), gestiti);

        System.out.println("Ristorante inserito con successo!");

    } catch (Exception e) {
        System.out.println("Errore: " + e.getMessage());
    }
}


   
}
