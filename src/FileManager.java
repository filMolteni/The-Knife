package src;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManager {

    private static final String FILE_RISTORANTI = "data/ristoranti.dat";
    private static final String FILE_UTENTI = "data/utenti.dat";
    private static final String FILE_RECENSIONI = "data/recensioni.dat";

    // CSV iniziale (fornito)
    private static final String CSV_RISTORANTI_INIZIALI = "data/michelin_my_maps.csv";

    // === CREAZIONE CARTELLA DATI E FILE BASE ===
    public static void inizializzaFile() {
        try {
            Files.createDirectories(Paths.get("data"));

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
        
                br.readLine(); // Salta intestazione
        
                while ((line = br.readLine()) != null) {
                    lineNumber++;
        
                    // Dividi la riga in campi separati da virgolette, tenendo conto delle virgole
                    String[] c = line.split("(?<!\"),(?!\")"); // Split separando solo le virgole non racchiuse da virgolette
        
                    // Puliamo eventuali virgolette iniziali/finali dal primo e ultimo campo
                    c[0] = c[0].replaceFirst("^\"", "").trim(); // Prima colonna (stringa)
                    c[c.length - 1] = c[c.length - 1].replaceFirst("\"$", "").trim(); // Ultima colonna (stringa)
        
                    if (c.length < 14) {
                        System.err.println("⚠️ Riga " + lineNumber + " ignorata: campi insufficienti.");
                        continue;
                    }
        
                    try {
                        String nome = c[0];
                        String indirizzo = c[1];
        
                        // Location = "Città, Nazione"
                        String[] loc = c[2].split(",");
                        String citta = loc.length > 0 ? loc[0].trim() : "";
                        String nazione = loc.length > 1 ? loc[1].trim() : "";
        
                        String tipoCucina = c[4];
        
                        // Gestiamo i numeri con virgola, per latitudine e longitudine
                        double lon = Double.parseDouble(c[5].replace(",", "."));
                        double lat = Double.parseDouble(c[6].replace(",", "."));
        
                        String telefono = c[7];
                        String url = c[8];
                        String websiteUrl = c[9];
                        String award = c[10];
                        String greenStar = c[11];
                        String servizi = c[12];
                        String descrizione = c[13];
        
                        Ristorante r = new Ristorante(nome, nazione, citta, indirizzo, lat, lon, tipoCucina,
                                                      telefono, url, websiteUrl, award, greenStar, servizi, descrizione);
                        ristoranti.add(r);
        
                    } catch (Exception e) {
                        System.err.println("❌ Riga " + lineNumber + " saltata: " + e.getMessage());
                    }
                }
        
            } catch (IOException e) {
                System.err.println("Errore lettura CSV: " + e.getMessage());
            }
        
            return ristoranti;
        }
        
    
    

    // === SALVATAGGIO OGGETTI SERIALIZZATI ===
    public static void salvaOggetti(String filePath, List<?> oggetti) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(oggetti);
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio su file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> caricaOggetti(String filePath) {
        List<T> lista = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            lista = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File vuoto o non esistente: " + filePath);
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
