package src;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileManager {

    private static final String FILE_RISTORANTI = "data/ristorantiPreferiti.dat";
    private static final String FILE_RISTORANTI_GESTITI = "data/ristorantiGestiti.dat";
    private static final String FILE_UTENTI = "data/utenti.dat";
    private static final String FILE_RECENSIONI = "data/recensioni.dat";

    // CSV iniziale (fornito)
    private static final String CSV_RISTORANTI_INIZIALI = "data/michelin_my_maps.csv";

    // === CREAZIONE CARTELLA DATI E FILE BASE ===
    public static void inizializzaFile() {
        try {

            if (!Files.exists(Paths.get(FILE_RISTORANTI))) {
                Files.createFile(Paths.get(FILE_RISTORANTI));
            }if (!Files.exists(Paths.get(FILE_RISTORANTI_GESTITI))) {
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
    

    // === LETTURA UTENTI DA CVS ===
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
    
    

    // === LETTURA RECENSIONI DA CVS ===
    public static List<Recensione> leggiRecensioniDaCSV() {
        List<Recensione> Recensioni = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_RECENSIONI))) {
            String line;
            int lineNumber = 0;

            // Salta intestazione
            br.readLine();

            while ((line = br.readLine()) != null) {
                lineNumber++;

                List<String> campi = parseCSVLine(line);

                if (campi.size() < 14) {
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
                    Recensioni.add(r);
                } catch (Exception e) {
                    System.err.println("Riga " + lineNumber + " saltata: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Errore lettura CSV: " + e.getMessage());
        }
        return Recensioni;
    }

    // === LETTURA PREFERITI DA CVS ===
    public static List<Preferito> leggiPreferitiDaCSV() {
        List<Preferito> Preferiti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_RISTORANTI))) {
            String line;
            int lineNumber = 0;

            // Salta intestazione
            br.readLine();

            while ((line = br.readLine()) != null) {
                lineNumber++;

                List<String> campi = parseCSVLine(line);

                if (campi.size() < 14) {
                    System.err.println(
                            "Riga " + lineNumber + " ignorata: campi insufficienti (" + campi.size() + ").");
                    continue;
                }

                try {
                    String utente = campi.get(0);
                    String ristorante = campi.get(1);
                    Preferito p = new Preferito(utente, ristorante);
                    Preferiti.add(p);
                } catch (Exception e) {
                    System.err.println("Riga " + lineNumber + " saltata: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Errore lettura CSV: " + e.getMessage());
        }
        return Preferiti;
    }

    // === LETTURA GESTITI DA CVS ===
    public static List<Preferito> leggiGestitiDaCSV() {
        List<Preferito> Gestiti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_RISTORANTI_GESTITI))) {
            String line;
            int lineNumber = 0;

            // Salta intestazione
            br.readLine();

            while ((line = br.readLine()) != null) {
                lineNumber++;

                List<String> campi = parseCSVLine(line);

                if (campi.size() < 14) {
                    System.err.println(
                            "Riga " + lineNumber + " ignorata: campi insufficienti (" + campi.size() + ").");
                    continue;
                }

                try {
                    String utente = campi.get(0);
                    String ristorante = campi.get(1);
                    Preferito p = new Preferito(utente, ristorante);
                    Gestiti.add(p);
                } catch (Exception e) {
                    System.err.println("Riga " + lineNumber + " saltata: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Errore lettura CSV: " + e.getMessage());
        }
        return Gestiti;
    }

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

    // === Percorsi Getter ===
    public static String getFileRistoranti() {
        return FILE_RISTORANTI;
    }

    public static String getFileUtenti() {
        return FILE_UTENTI;
    }

    public static String getFileRecensioni() {
        return FILE_RECENSIONI;
    }
    
    public static String getFileRistorantiGestiti() {
        return FILE_RISTORANTI_GESTITI;
    }
}
