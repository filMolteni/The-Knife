package src;

/**
 * Interfaccia che definisce un metodo per la serializzazione di oggetti in formato CSV.
 * Qualsiasi classe che implementa questa interfaccia deve fornire una rappresentazione CSV dei suoi dati.
 */
public interface CSVWritable {
    /**
     * Restituisce una rappresentazione dell'oggetto in formato CSV.
     *
     * @return Stringa contenente i dati dell'oggetto separati da virgole.
     */
    String toCSV();
}
