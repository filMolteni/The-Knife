package src;
public enum FasciaPrezzo {
    ECONOMICA("€"),
    MEDIA("€€"),
    COSTOSA("€€€"),
    LUSSO("€€€€");

    private final String simbolo;

    FasciaPrezzo(String simbolo) {
        this.simbolo = simbolo;
    }

    public static FasciaPrezzo fromString(String s) {
        for (FasciaPrezzo f : values()) {
            if (f.simbolo.equals(s)) return f;
        }
        return null; // oppure un default, es. ECONOMICA
    }
}
