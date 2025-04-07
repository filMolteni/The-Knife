package src;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;

public class FileManager {

    private static final String FILE_RISTORANTI = "data/ristoranti.dat";
    private static final String FILE_UTENTI = "data/utenti.dat";
    private static final String FILE_RECENSIONI = "data/recensioni.dat";

    // CSV iniziale (fornito)
    private static final String CSV_RISTORANTI_INIZIALI = "data/michelin_my_maps.csv";

    // === CREAZIONE CARTELLA DATI E FILE BASE ===
    public static void inizializzaFile() {
        try {

            if (!Files.exists(Paths.get(FILE_RISTORANTI))) {
                Files.createFile(Paths.get(FILE_RISTORANTI));
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

    // === LETTURA RISTORANTI DA CSV ORIGINALE ===
    public static List<Ristorante> leggiRistorantiDaCSV() {
        List<Ristorante> ristoranti = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader("data/michelin_my_maps.csv"))) {
            String line;
            int lineNumber = 0;
    
            // Salta intestazione
            br.readLine();
    
            while ((line = br.readLine()) != null) {
                lineNumber++;
    
                List<String> campi = parseCSVLine(line);
    
                if (campi.size() < 14) {
                    System.err.println("⚠️ Riga " + lineNumber + " ignorata: campi insufficienti (" + campi.size() + ").");
                    continue;
                }
    
                try {
                    String nome = campi.get(0);
                    String indirizzo = campi.get(1);
                    String location = campi.get(2);
                    String[] loc = location.split(",");
                    String citta = loc.length > 0 ? loc[0].trim() : "";
                    String nazione = loc.length > 1 ? loc[1].trim() : "";
    
                    String tipoCucina = campi.get(4);
                    double lon = Double.parseDouble(campi.get(5).replace(",", "."));
                    double lat = Double.parseDouble(campi.get(6).replace(",", "."));
                    String telefono = campi.get(7);
                    String url = campi.get(8);
                    String website = campi.get(9);
                    String award = campi.get(10);
                    String greenStar = campi.get(11);
                    String servizi = campi.get(12);
                    String descrizione = campi.get(13);
    
                    Ristorante r = new Ristorante(nome, nazione, citta, indirizzo, lat, lon, tipoCucina,
                                                  telefono, url, website, award, greenStar, servizi, descrizione);
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

        System.out.println("📥 Oggetti caricati da " + filePath);
    } catch (IOException e) {
        System.err.println("⚠️ Errore nella lettura CSV: " + e.getMessage());
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
}
