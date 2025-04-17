package src;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe che gestisce la lettura e la scrittura dei file relativi ai ristoranti, utenti e recensioni.
 * Fornisce metodi per accedere ai percorsi dei file e caricare dati iniziali.
 */
public class FileManager {

    private static final String FILE_RISTORANTI = "data/ristorantiPreferiti.dat";
    private static final String FILE_RISTORANTI_GESTITI = "data/ristorantiGestiti.dat";
    private static final String FILE_UTENTI = "data/utenti.dat";
    private static final String FILE_RECENSIONI = "data/recensioni.dat";

    // CSV iniziale (fornito)
    private static final String CSV_RISTORANTI_INIZIALI = "data/michelin_my_maps.csv";

    /**
     * Restituisce il percorso del file CSV contenente i ristoranti iniziali.
     * 
     * @return Il percorso del file CSV contenente i dati dei ristoranti iniziali.
     */
    public static String getCsvRistorantiIniziali() {
        return CSV_RISTORANTI_INIZIALI;
    }
}


   /**
 * Inizializza la cartella dati e crea i file di base se non esistono.
 * I file includono ristoranti preferiti, ristoranti gestiti, utenti e recensioni.
 * 
 * Se i file non sono presenti, vengono creati automaticamente.
 * 
 * In caso di errore durante la creazione dei file, viene stampato un messaggio di errore.
 */
public static void inizializzaFile() {
    try {
        if (!Files.exists(Paths.get(FILE_RISTORANTI))) {
            Files.createFile(Paths.get(FILE_RISTORANTI));
        }
        if (!Files.exists(Paths.get(FILE_RISTORANTI_GESTITI))) {
            Files.createFile(Paths.get(FILE_RISTORANTI_GESTITI));
        }
        if (!Files.exists(Paths.get(FILE_UTENTI))) {
            Files.createFile(Paths.get(FILE_UTENTI));
        }
        if (!Files.exists(Paths.get(FILE_RECENSIONI))) {
            Files.createFile(Paths.get(FILE_RECENSIONI));
        }
    } catch (IOException e) {
        System.err.println("Errore nella creazione dei file iniziali: " + e.getMessage());
    }
}


    /**
 * Legge i dati dei ristoranti da un file CSV e li converte in una lista di oggetti {@link Ristorante}.
 * Il file deve essere nel formato corretto con almeno 14 campi per riga.
 * 
 * Ogni riga del CSV rappresenta un ristorante, con informazioni su nome, indirizzo, città, nazione,
 * fascia di prezzo, tipo di cucina, coordinate geografiche, contatti, premi ricevuti, e servizi disponibili.
 * 
 * Se la riga ha meno di 14 campi, viene ignorata e segnalata nel log.
 * In caso di errori di parsing, la riga viene saltata e segnalata.
 * 
 * @return Una lista di oggetti {@link Ristorante} caricati dal CSV.
 */
public static List<Ristorante> leggiRistorantiDaCSV() {
    List<Ristorante> ristoranti = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(CSV_RISTORANTI_INIZIALI))) {
        String line;
        int lineNumber = 0;

        // Salta intestazione
        br.readLine();

        while ((line = br.readLine()) != null) {
            lineNumber++;
            List<String> campi = parseCSVLine(line);

            if (campi.size() < 14) {
                System.err.println("Riga " + lineNumber + " ignorata: campi insufficienti (" + campi.size() + ").");
                continue;
            }

            try {
                String nome = campi.get(0);
                String indirizzo = campi.get(1);
                String location = campi.get(2);
                String[] loc = location.split(",");
                String citta = loc.length > 0 ? loc[0].trim() : "";
                String nazione = loc.length > 1 ? loc[1].trim() : "";

                String fasciaRaw = campi.get(3).trim();
                FasciaPrezzo fasciaPrezzo = FasciaPrezzo.fromString(fasciaRaw);

                String tipoCucina = campi.get(4);
                double lon = Double.parseDouble(campi.get(5).replace(",", "."));
                double lat = Double.parseDouble(campi.get(6).replace(",", "."));

                String telefono = campi.get(7);
                String url = campi.get(8);
                String website = campi.get(9);
                String award = campi.get(10);
                double greenStar = Double.parseDouble(campi.get(11).replace(",", "."));

                Boolean servizi = campi.get(12).toLowerCase().contains("yes") || campi.get(12).equalsIgnoreCase("true");
                String descrizione = campi.get(13);

                Ristorante r = new Ristorante(
                        nome, nazione, citta, indirizzo,
                        lat, lon, tipoCucina,
                        telefono, url, website, award,
                        greenStar, servizi, descrizione, fasciaPrezzo
                );

                ristoranti.add(r);

            } catch (Exception e) {
                System.err.println("Riga " + lineNumber + " saltata: " + e.getMessage());
            }
        }
    } catch (IOException e) {
        System.err.println("Errore lettura CSV: " + e.getMessage());
    }

    return ristoranti;
}


  /**
 * Legge i dati degli utenti da un file CSV e li converte in una lista di oggetti {@link Utente}.
 * Il file deve contenere almeno 7 campi per riga per essere valido.
 * 
 * Ogni riga rappresenta un utente con informazioni su nome, cognome, username, password cifrata,
 * data di nascita, domicilio e ruolo.
 * 
 * Se la riga ha meno di 7 campi, viene ignorata con un avviso.
 * In caso di errori di parsing, la riga viene saltata e segnalata nel log.
 * 
 * @return Una lista di oggetti {@link Utente} caricati dal CSV.
 */
public static List<Utente> leggiUtentiDaCSV() {
    List<Utente> utenti = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(FILE_UTENTI))) {
        String line;
        int lineNumber = 0;

        while ((line = br.readLine()) != null) {
            lineNumber++;
            List<String> campi = parseCSVLine(line);

            if (campi.size() < 7) {
                System.err.println("⚠️ Riga " + lineNumber + " ignorata: campi insufficienti (" + campi.size() + ").");
                continue;
            }

            try {
                String nome = campi.get(0);
                String cognome = campi.get(1);
                String username = campi.get(2);
                String passwordCifrata = campi.get(3);
                LocalDate dataNascita = LocalDate.parse(campi.get(4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String domicilio = campi.get(5);
                String ruoloRaw = campi.get(6).trim();
                boolean ruolo = Boolean.parseBoolean(ruoloRaw);

                Utente utente = new Utente(nome, cognome, username, passwordCifrata, dataNascita, domicilio, ruolo);
                utenti.add(utente);

            } catch (Exception e) {
                System.err.println("Riga " + lineNumber + " saltata: " + e.getMessage());
            }
        }
    } catch (IOException e) {
        System.err.println("Errore lettura CSV: " + e.getMessage());
    }

    return utenti;
}

    

   /**
 * Legge i dati delle recensioni da un file CSV e li converte in una lista di oggetti {@link Recensione}.
 * Il file deve contenere almeno 6 campi per riga per essere valido.
 * 
 * Ogni riga rappresenta una recensione con informazioni su autore, ristorante, voto (da 1 a 5), commento,
 * data della recensione e eventuale risposta del ristoratore.
 * 
 * Se la riga ha meno di 6 campi, viene ignorata con un avviso.
 * In caso di errori di parsing, la riga viene saltata e segnalata nel log.
 * 
 * @return Una lista di oggetti {@link Recensione} caricati dal CSV.
 */
public static List<Recensione> leggiRecensioniDaCSV() {
    List<Recensione> recensioni = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(FILE_RECENSIONI))) {
        String line;
        int lineNumber = 0;

        while ((line = br.readLine()) != null) {
            lineNumber++;
            List<String> campi = parseCSVLine(line);

            if (campi.size() < 6) {
                System.err.println(
                        "Riga " + lineNumber + " ignorata: campi insufficienti (" + campi.size() + ").");
                continue;
            }

            try {
                String autore = campi.get(0);
                String ristorante = campi.get(1);
                int voto = Integer.parseInt(campi.get(2)); // 1-5
                String commento = campi.get(3);
                LocalDate data = LocalDate.parse(campi.get(4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String rispostaRistoratore = campi.get(5);

                Recensione r = new Recensione(autore, ristorante, voto, commento, data, rispostaRistoratore);
                recensioni.add(r);
            } catch (Exception e) {
                System.err.println("Riga " + lineNumber + " saltata: " + e.getMessage());
            }
        }
    } catch (IOException e) {
        System.err.println("Errore lettura CSV: " + e.getMessage());
    }

    return recensioni;
}


    /**
 * Legge i dati dei ristoranti preferiti da un file CSV e li converte in una lista di oggetti {@link Preferito}.
 * Il file deve contenere almeno 2 campi per riga per essere valido.
 * 
 * Ogni riga rappresenta un ristorante preferito associato a un utente.
 * 
 * Se la riga ha meno di 2 campi, viene ignorata con un avviso.
 * In caso di errori di parsing, la riga viene saltata e segnalata nel log.
 * 
 * @return Una lista di oggetti {@link Preferito} caricati dal CSV.
 */
public static List<Preferito> leggiPreferitiDaCSV() {
    List<Preferito> preferiti = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(FILE_RISTORANTI))) {
        String line;
        int lineNumber = 0;

        while ((line = br.readLine()) != null) {
            lineNumber++;
            List<String> campi = parseCSVLine(line);

            if (campi.size() < 2) {
                System.err.println(
                        "Riga " + lineNumber + " ignorata: campi insufficienti (" + campi.size() + ").");
                continue;
            }

            try {
                String utente = campi.get(0);
                String ristorante = campi.get(1);
                Preferito p = new Preferito(utente, ristorante);
                preferiti.add(p);
            } catch (Exception e) {
                System.err.println("Riga " + lineNumber + " saltata: " + e.getMessage());
            }
        }
    } catch (IOException e) {
        System.err.println("Errore lettura CSV: " + e.getMessage());
    }

    return preferiti;
}


   /**
 * Legge i dati dei ristoranti gestiti da un file CSV e li converte in una lista di oggetti {@link Preferito}.
 * Il file deve contenere almeno 2 campi per riga per essere valido.
 * 
 * Ogni riga rappresenta un ristorante gestito associato a un utente.
 * 
 * Se la riga ha meno di 2 campi, viene ignorata con un avviso.
 * In caso di errori di parsing, la riga viene saltata e segnalata nel log.
 * 
 * @return Una lista di oggetti {@link Preferito} caricati dal CSV.
 */
public static List<Preferito> leggiGestitiDaCSV() {
    List<Preferito> gestiti = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(FILE_RISTORANTI_GESTITI))) {
        String line;
        int lineNumber = 0;

        while ((line = br.readLine()) != null) {
            lineNumber++;
            List<String> campi = parseCSVLine(line);

            if (campi.size() < 2) {
                System.err.println(
                        "Riga " + lineNumber + " ignorata: campi insufficienti (" + campi.size() + ").");
                continue;
            }

            try {
                String utente = campi.get(0);
                String ristorante = campi.get(1);
                Preferito p = new Preferito(utente, ristorante);
                gestiti.add(p);
            } catch (Exception e) {
                System.err.println("Riga " + lineNumber + " saltata: " + e.getMessage());
            }
        }
    } catch (IOException e) {
        System.err.println("Errore lettura CSV: " + e.getMessage());
    }
    return gestiti;
}

/**
 * Analizza una riga di un file CSV gestendo le virgole all'interno delle virgolette.
 * 
 * @param line La riga CSV da analizzare.
 * @return Una lista di stringhe con i campi estratti dalla riga.
 */
private static List<String> parseCSVLine(String line) {
    List<String> result = new ArrayList<>();
    StringBuilder current = new StringBuilder();
    boolean inQuotes = false;

    for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);

        if (c == '\"') {
            inQuotes = !inQuotes; // alterna l'interno/esterno virgolette
        } else if (c == ',' && !inQuotes) {
            result.add(current.toString().trim());
            current.setLength(0);
        } else {
            current.append(c);
        }
    }

    result.add(current.toString().trim()); // ultimo campo
    return result;
}

/**
 * Salva una lista di oggetti in formato CSV nel file specificato.
 * 
 * @param filePath Il percorso del file CSV in cui salvare gli oggetti.
 * @param oggetti  La lista di oggetti da salvare, che devono implementare {@link CSVWritable}.
 */
public static void salvaOggettiCSV(String filePath, List<? extends CSVWritable> oggetti) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (CSVWritable oggetto : oggetti) {
            writer.write(oggetto.toCSV());
            writer.newLine();
        }
        System.out.println("Oggetti salvati in formato CSV su " + filePath);
    } catch (IOException e) {
        System.err.println("Errore nella scrittura CSV: " + e.getMessage());
    }
}

/**
 * Carica una lista di oggetti da un file CSV utilizzando una funzione parser.
 * 
 * @param filePath Il percorso del file CSV da cui caricare gli oggetti.
 * @param parser   Una funzione che converte un array di stringhe in un oggetto di tipo {@link T}.
 * @param <T>      Il tipo generico degli oggetti da caricare.
 * @return Una lista di oggetti caricati dal CSV.
 */
public static <T> List<T> caricaOggettiCSV(String filePath, Function<String[], T> parser) {
    List<T> lista = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                String[] campi = line.split(",");
                T oggetto = parser.apply(campi);
                lista.add(oggetto);
            }
        }

        System.out.println("Oggetti caricati da " + filePath);
    } catch (IOException e) {
        System.err.println("Errore nella lettura CSV: " + e.getMessage());
    }

    return lista;
}


   /**
 * Restituisce il percorso del file contenente i ristoranti preferiti.
 * 
 * @return Il percorso del file dei ristoranti preferiti.
 */
public static String getFileRistoranti() {
    return FILE_RISTORANTI;
}

/**
 * Restituisce il percorso del file contenente gli utenti registrati.
 * 
 * @return Il percorso del file degli utenti.
 */
public static String getFileUtenti() {
    return FILE_UTENTI;
}

/**
 * Restituisce il percorso del file contenente le recensioni.
 * 
 * @return Il percorso del file delle recensioni.
 */
public static String getFileRecensioni() {
    return FILE_RECENSIONI;
}

/**
 * Restituisce il percorso del file contenente i ristoranti gestiti.
 * 
 * @return Il percorso del file dei ristoranti gestiti.
 */
public static String getFileRistorantiGestiti() {
    return FILE_RISTORANTI_GESTITI;
}
