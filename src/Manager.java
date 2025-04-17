package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe Manager che gestisce l'interazione tra utenti, ristoranti, recensioni e preferiti.
 * Contiene liste per la gestione dei vari elementi del sistema.
 */
public class Manager {
    private List<Utente> utenti;
    private List<Ristorante> ristoranti;
    private List<Recensione> recensioni;
    private List<Preferito> preferiti;
    private List<Preferito> gestiti;
    private List<Cliente> clienti;
    private List<Ristoratore> ristoratori;
    private Utente logged;

    /**
     * Restituisce la lista degli utenti.
     * 
     * @return Lista di oggetti {@link Utente}.
     */
    public List<Utente> getUtenti() {
        return utenti;
    }

    /**
     * Imposta la lista degli utenti.
     * 
     * @param utenti Lista di utenti da impostare.
     */
    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    /**
     * Restituisce la lista dei ristoranti.
     * 
     * @return Lista di oggetti {@link Ristorante}.
     */
    public List<Ristorante> getRistoranti() {
        return ristoranti;
    }

    /**
     * Imposta la lista dei ristoranti.
     * 
     * @param ristoranti Lista di ristoranti da impostare.
     */
    public void setRistoranti(List<Ristorante> ristoranti) {
        this.ristoranti = ristoranti;
    }

    /**
     * Restituisce la lista delle recensioni.
     * 
     * @return Lista di oggetti {@link Recensione}.
     */
    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    /**
     * Imposta la lista delle recensioni.
     * 
     * @param recensioni Lista di recensioni da impostare.
     */
    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    /**
     * Restituisce la lista dei ristoranti preferiti.
     * 
     * @return Lista di oggetti {@link Preferito}.
     */
    public List<Preferito> getPreferiti() {
        return preferiti;
    }

    /**
     * Imposta la lista dei ristoranti preferiti.
     * 
     * @param preferiti Lista di preferiti da impostare.
     */
    public void setPreferiti(List<Preferito> preferiti) {
        this.preferiti = preferiti;
    }

    /**
     * Restituisce la lista dei ristoranti gestiti.
     * 
     * @return Lista di oggetti {@link Preferito}.
     */
    public List<Preferito> getGestiti() {
        return gestiti;
    }

    /**
     * Imposta la lista dei ristoranti gestiti.
     * 
     * @param gestiti Lista di ristoranti gestiti da impostare.
     */
    public void setGestiti(List<Preferito> gestiti) {
        this.gestiti = gestiti;
    }

    /**
     * Restituisce la lista dei clienti.
     * 
     * @return Lista di oggetti {@link Cliente}.
     */
    public List<Cliente> getClienti() {
        return clienti;
    }

    /**
     * Imposta la lista dei clienti.
     * 
     * @param clienti Lista di clienti da impostare.
     */
    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    /**
     * Restituisce la lista dei ristoratori.
     * 
     * @return Lista di oggetti {@link Ristoratore}.
     */
    public List<Ristoratore> getRistoratori() {
        return ristoratori;
    }

    /**
     * Imposta la lista dei ristoratori.
     * 
     * @param ristoratori Lista di ristoratori da impostare.
     */
    public void setRistoratori(List<Ristoratore> ristoratori) {
        this.ristoratori = ristoratori;
    }
}

    
/**
 * Avvia l'applicazione inizializzando i file, caricando i dati e filtrando utenti e ristoranti.
 * Questo metodo legge i dati dai file CSV, inizializza le liste e associa i ristoratori e clienti correttamente.
 */
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

/**
 * Filtra gli utenti per identificare i clienti.
 * 
 * @return Una lista di oggetti {@link Cliente} estratti dagli utenti registrati.
 */
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

/**
 * Filtra gli utenti per identificare i ristoratori.
 * 
 * @return Una lista di oggetti {@link Ristoratore} estratti dagli utenti registrati.
 */
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

/**
 * Registra un nuovo utente e lo salva nel file CSV.
 * 
 * @param utente L'oggetto {@link Utente} da registrare.
 */
public void registraUtente(Utente utente) {
    List<Utente> utenti = FileManager.caricaOggettiCSV(
            FileManager.getFileUtenti(),
            campi -> {
                LocalDate dataNascita = (!campi[4].isEmpty())
                        ? LocalDate.parse(campi[4])
                        : LocalDate.of(1900, 1, 1);

                return new Utente(
                        campi[0], campi[1], campi[2], campi[3],
                        dataNascita, campi[5], Boolean.parseBoolean(campi[6])
                );
            });

    utenti.add(utente);
    FileManager.salvaOggettiCSV(FileManager.getFileUtenti(), utenti);
    System.out.println("Utente registrato con i seguenti dati: " + utente.toString());
}

/**
 * Aggiunge un ristorante ai preferiti del cliente attualmente loggato.
 * 
 * @param ristorante L'oggetto {@link Ristorante} da aggiungere ai preferiti.
 */
public void aggiungiPreferitoAlClienteLoggato(Ristorante ristorante) {
    if (!(Logged instanceof Cliente)) {
        System.out.println("Solo un cliente può avere preferiti.");
        return;
    }
    Cliente cliente = (Cliente) Logged;

    if (cliente.getPreferiti() == null) {
        cliente.setPreferiti(new ArrayList<>());
    }

    List<Ristorante> preferitiCliente = cliente.getPreferiti();

    boolean giàPresente = preferitiCliente.stream()
        .anyMatch(r -> r.getNome().equalsIgnoreCase(ristorante.getNome()) &&
                       r.getIndirizzo().equalsIgnoreCase(ristorante.getIndirizzo()));

    if (!giàPresente) {
        preferitiCliente.add(ristorante);
        this.preferiti.add(new Preferito(cliente.getUsername(), ristorante.getNome())); 
        FileManager.salvaOggettiCSV(FileManager.getFileRistoranti(), preferiti);
        System.out.println("Ristorante aggiunto ai preferiti.");
    } else {
        System.out.println("Il ristorante è già nei preferiti.");
    }
}

/**
 * Aggiunge una recensione a un ristorante.
 * 
 * @param ristorante L'oggetto {@link Ristorante} a cui aggiungere la recensione.
 * @param voto Il voto assegnato al ristorante (1-5).
 * @param commento Il commento della recensione.
 */
public void aggiungiRecensione(Ristorante ristorante, int voto, String commento) {
    if (!(Logged instanceof Cliente)) {
        System.out.println("Solo i clienti possono aggiungere recensioni.");
        return;
    }

    Cliente cliente = (Cliente) Logged;
}


/**
 * Cerca ristoranti in base a vari criteri, tra cui città, tipo di cucina, fascia di prezzo, stelle verdi e servizi.
 * 
 * @param citta La città in cui cercare ristoranti (può essere null o vuota).
 * @param tipoCucina Il tipo di cucina desiderato (può essere null o vuoto).
 * @param f La fascia di prezzo desiderata (può essere null).
 * @param greenStar Specifica se si vogliono solo ristoranti con stelle verdi (può essere "true" o "false").
 * @param servizi Indica se i ristoranti devono offrire servizi particolari.
 * @return Una lista di ristoranti che corrispondono ai criteri di ricerca.
 */
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

/**
 * Esegue il login dell'utente confrontando username e password con i dati salvati.
 * Se l'utente esiste, viene autenticato e associato al ruolo corretto.
 * 
 * @param username Il nome utente per l'accesso.
 * @param password La password per l'autenticazione.
 */
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

            // Carica preferiti, recensioni o ristoranti gestiti
            completaUtenteLoggato(utenteAutenticato);

            this.Logged = utenteAutenticato;
            System.out.println("Accesso effettuato con successo! Benvenuto, " + utenteAutenticato.getNome());
            return;
        }
    }

    System.out.println("Credenziali non valide. Riprovare.");
}

/**
 * Completa il profilo dell'utente loggato associandogli i suoi preferiti, recensioni o ristoranti gestiti.
 * 
 * @param utenteAutenticato L'utente autenticato al login.
 */
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

/**
 * Carica dinamicamente le recensioni per ciascun cliente in base ai dati presenti nel sistema.
 */
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

/**
 * Carica dinamicamente i ristoranti preferiti per ciascun cliente in base ai dati presenti nel sistema.
 */
public void CaricaDinamicaPreferiti() {
    for (Cliente c : clienti) {
        List<Ristorante> preferitiCliente = new ArrayList<>();

        for (Preferito p : preferiti) {
            if (p.getUtente().equals(c.getUsername())) {
                for (Ristorante r : ristoranti) {
                    if (r.getNome().equals(p.getRistorante())) {
                        preferitiCliente.add(r);
                    }
                }
            }
        }

        c.setPreferiti(preferitiCliente);
    }
}

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
        
        FileManager.salvaOggettiCSV(FileManager.getFileRistorantiGestiti(), gestiti);
        FileManager.salvaOggettiCSV(FileManager.getCsvRistorantiIniziali(), ristoranti);

        System.out.println("Ristorante inserito con successo!");

    } catch (Exception e) {
        System.out.println("Errore: " + e.getMessage());
    }
}

public void visualizzaERispondiRecensioni(Scanner sc) {
    if (!(Logged instanceof Ristoratore)) {
        System.out.println(" Solo un ristoratore può accedere a questa funzione.");
        return;
    }

    Ristoratore ristoratore = (Ristoratore) Logged;
    List<Ristorante> gestiti = ristoratore.getRistorantiGestiti();

    if (gestiti == null || gestiti.isEmpty()) {
        System.out.println(" Non gestisci ancora nessun ristorante.");
        return;
    }

    List<Recensione> recensioniGestite = new ArrayList<>();
    for (Ristorante r : gestiti) {
        for (Recensione rec : recensioni) {
            if (rec.getRistorante().equalsIgnoreCase(r.getNome())) {
                recensioniGestite.add(rec);
            }
        }
    }

    if (recensioniGestite.isEmpty()) {
        System.out.println(" Nessuna recensione trovata per i tuoi ristoranti.");
        return;
    }

    System.out.println("Recensioni ricevute:");
    for (int i = 0; i < recensioniGestite.size(); i++) {
        Recensione r = recensioniGestite.get(i);
        System.out.printf("[%d] Ristorante: %s | Autore: %s | Voto: %d\n   \"%s\"\n",
            i + 1, r.getRistorante(), r.getAutore(), r.getVoto(), r.getCommento());
        if (r.getRispostaRistoratore() != null && !r.getRispostaRistoratore().isEmpty()) {
            System.out.println("    Risposta già data: \"" + r.getRispostaRistoratore() + "\"\n");
        } else {
            System.out.println("    Nessuna risposta ancora.\n");
        }
    }

    System.out.print("Inserisci il numero della recensione a cui vuoi rispondere (0 per uscire): ");
    int scelta;
    try {
        scelta = Integer.parseInt(sc.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Input non valido.");
        return;
    }

    if (scelta == 0) return;
    if (scelta < 1 || scelta > recensioniGestite.size()) {
        System.out.println("Numero fuori range.");
        return;
    }

    Recensione selezionata = recensioniGestite.get(scelta - 1);

    if (selezionata.getRispostaRistoratore() != null && !selezionata.getRispostaRistoratore().isEmpty()) {
        System.out.println("Hai già risposto a questa recensione.");
        return;
    }

    System.out.print("Scrivi la tua risposta: ");
    String risposta = sc.nextLine();
    selezionata.setRispostaRistoratore(risposta);

    // Salva aggiornamenti su file
    FileManager.salvaOggettiCSV(FileManager.getFileRecensioni(), recensioni);

    System.out.println("Risposta salvata correttamente.");
}

public void statisticheRistoranti() {
    if (!(Logged instanceof Ristoratore)) {
        System.out.println("Solo un ristoratore può accedere a questa funzione.");
        return;
    }

    Ristoratore ristoratore = (Ristoratore) Logged;
    List<Ristorante> gestiti = ristoratore.getRistorantiGestiti();

    if (gestiti == null || gestiti.isEmpty()) {
        System.out.println("Non gestisci ancora alcun ristorante.");
        return;
    }

    System.out.println("Statistiche dei tuoi ristoranti:");

    for (Ristorante ristorante : gestiti) {
        String nome = ristorante.getNome();

        List<Recensione> recensioniRistorante = recensioni.stream()
            .filter(r -> r.getRistorante().equalsIgnoreCase(nome))
            .toList();

        int numero = recensioniRistorante.size();
        double media = numero > 0
            ? recensioniRistorante.stream().mapToInt(Recensione::getVoto).average().orElse(0.0)
            : 0.0;

        System.out.printf("%s\n", nome);
        System.out.printf(" Media voti: %.2f\n", media);
        System.out.printf(" Numero recensioni: %d\n", numero);
        System.out.println();
    }
}


   
}
