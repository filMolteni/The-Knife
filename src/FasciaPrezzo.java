package src;

public enum FasciaPrezzo {
    ECONOMICA("€", "economica"),
    MEDIA("€€", "media"),
    COSTOSA("€€€", "costosa"),
    LUSSO("€€€€", "lusso");

    private final String simbolo;
    private final String nomeUtente;

    FasciaPrezzo(String simbolo, String nomeUtente) {
        this.simbolo = simbolo;
        this.nomeUtente = nomeUtente;
    }

    public static FasciaPrezzo fromString(String s) {
        for (FasciaPrezzo f : values()) {
            if (f.simbolo.equals(s)) return f;
        }
        return null; // oppure un default, es. ECONOMICA
    }
    
    public static FasciaPrezzo fromInput(String input) {
        for (FasciaPrezzo f : values()) {
            if (f.name().equalsIgnoreCase(input) || f.nomeUtente.equalsIgnoreCase(input) || f.simbolo.equals(input)) {
                return f;
            }
        }
        return null;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }
}
