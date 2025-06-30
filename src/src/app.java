package src;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
 import java.lang.*;
 import java.time.LocalDate;

/**
 * Classe principale che avvia l'applicazione e gestisce l'interfaccia utente.
 */
public class app {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.avviaApplicazione();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Benvenuto su TheKnife ===");

            Utente logged = null;

            logged = manager.getLogged();

            if (logged == null) { 
                
                /**
                 * Se nessun utente e' loggato, mostra il menu per utenti anonimi.
                 */
                System.out.println("[1] Visualizza ristoranti");
                System.out.println("[2] Visualizza recensioni anonime");
                System.out.println("[3] Registrati");
                System.out.println("[4] Login");
                System.out.println("[5] Ricerca ristorante");
                System.out.println("[0] Esci");
                System.out.print("Scelta: ");
                String sceltaMenu = sc.nextLine();

                switch (sceltaMenu) {
                        case "1":
                        /**
                         * Mostra la lista dei ristoranti.
                         */
                        System.out.println("\nLista dei ristoranti:");
                        if (manager.getRistoranti().isEmpty()) {
                            System.out.println("Nessun ristorante disponibile.");
                            break;
                        }
                        // Stampa l'intestazione della tabella

                System.out.println("Nome                Indirizzo                      Tipo Cucina");
                System.out.println("---------------------------------------------------------------");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Sleep interrotto.");
                        }
                for (Ristorante ristorante : manager.getRistoranti()) {
                    String nomeRistorante = String.format("%-20s", ristorante.getNome());
                    String indirizzo = String.format("%-30s", ristorante.getIndirizzo());
                    String tipo = String.format("%-15s", ristorante.getTipoCucina());
                    System.out.println(nomeRistorante + indirizzo + tipo);
                }

                break;

                    case "2":
             

                        /**
                         * Mostra le recensioni anonime per un ristorante scelto dall'utente, in formato tabellare.
                         */
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Inserisci il nome del ristorante: ");
                        String nomeRistorante = scanner.nextLine();

                        System.out.printf("\n%-20s %-10s %-50s\n", "Data", "Voto", "Commento");
                        System.out.println("--------------------------------------------------------------------------");

                        boolean trovata = false;

                        for (Recensione rec : manager.getRecensioni()) {
                            if ( rec.getRistorante().equalsIgnoreCase(nomeRistorante)) {
                                System.out.printf("%-20s %-10d %-50s\n",
                                    rec.getData().toString(),
                                    rec.getVoto(),
                                    rec.getCommento());
                                trovata = true;
                            }
                        }

                        if (!trovata) {
                            System.out.println("Nessuna recensione anonima trovata per il ristorante \"" + nomeRistorante + "\".");
                        }


                    break;


                    case "3":
                        System.out.println("=== REGISTRAZIONE NUOVO UTENTE ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cognome: ");
        String cognome = sc.nextLine();

        // Verifica che l'username non sia già presente
        String username;
        do {
            System.out.print("Username: ");
            username = sc.nextLine();
            if (manager.usernameEsiste(username)) {
                System.out.println(" Username già in uso, inserisci un altro.");
            }
        } while (manager.usernameEsiste(username));

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Data di nascita (yyyy-mm-dd): ");
        String data = sc.nextLine();
        LocalDate dataNascita = LocalDate.parse(data);

        System.out.print("Domicilio: ");
        String domicilio = sc.nextLine();

        System.out.print("Tipo utente (0 = Cliente, 1 = Ristoratore): ");
        String ruolo = sc.nextLine();
        boolean isCliente = ruolo.equals("0");

        // Creazione nuovo utente
        Utente nuovo = new Utente(
            nome,
            cognome,
            username,
            password,
            dataNascita,
            domicilio,
            isCliente
        );

        manager.registraUtente(nuovo);
        System.out.println(" Registrazione completata con successo!");
                        /**
                         * Registra un nuovo utente.
                         */
                        
                        manager.registraUtente(nuovo);
                        break;
                    case "4":
                        /**
                         * Consente all'utente di effettuare il login.
                         */
                        System.out.print("Username: ");
                        String user = sc.nextLine();
                        System.out.print("Password: ");
                        String pass = sc.nextLine();
                        manager.login(user, pass);
                        break;
                    case "5":
                                System.out.println("Scegli un'opzione di ricerca:");
                        System.out.println("1 - Cerca per nome");
                        System.out.println("2 - Cerca per città");
                        System.out.println("3 - Cerca per tipo di cucina");

                        int scelta = sc.nextInt();
                        sc.nextLine(); // consuma newline

                        if (manager.getRistoranti() != null && !manager.getRistoranti().isEmpty()) {
                            int i = 0; // Contatore per i ristoranti trovati

                            // Intestazione tabella
                            System.out.printf("%-20s %-30s %-20s%n", "Nome", "Indirizzo", "Tipo Cucina");
                            System.out.println("----------------------------------------------------------------------");

                            switch (scelta) {
                                case 1:
                                    System.out.print("Inserisci il nome del ristorante: ");
                                    String nomeRicerca = sc.nextLine();
                                    for (Ristorante ristorante : manager.getRistoranti()) {
                                        if (ristorante.getNome().equalsIgnoreCase(nomeRicerca)) {
                                            System.out.printf("%-20s %-30s %-20s%n", ristorante.getNome(), ristorante.getIndirizzo(), ristorante.getTipoCucina());
                                            i++;
                                        }
                                    }
                                    break;

                                case 2:
                                    System.out.print("Inserisci la città del ristorante: ");
                                    String citta = sc.nextLine();
                                    for (Ristorante ristorante : manager.getRistoranti()) {
                                        if (ristorante.getIndirizzo().toLowerCase().contains(citta.toLowerCase())) {
                                            System.out.printf("%-20s %-30s %-20s%n", ristorante.getNome(), ristorante.getIndirizzo(), ristorante.getTipoCucina());
                                            i++;
                                        }
                                    }
                                    break;

                                case 3:
                                    System.out.print("Inserisci il tipo di cucina (es: Italiana, Giapponese): ");
                                    String tipo = sc.nextLine();
                                    for (Ristorante ristorante : manager.getRistoranti()) {
                                        if (ristorante.getTipoCucina().equalsIgnoreCase(tipo)) {
                                            System.out.printf("%-20s %-30s %-20s%n", ristorante.getNome(), ristorante.getIndirizzo(), ristorante.getTipoCucina());
                                            i++;
                                        }
                                    }
                                    break;

                                default:
                                    System.out.println("Scelta non valida.");
                                    return;
                            }

                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Sono stati trovati " + i + " ristorante/i corrispondente/i.");
                        } else {
                            System.out.println("Nessun ristorante disponibile.");
                        }

                        break;
                    case "0":
                        System.out.println("Arrivederci!");
                        break;
                        default:
                            System.out.println("Opzione non valida.");
                    }
                } // Close the if (logged == null) block

                if (logged instanceof Cliente) {
                    /**
                     * Menu dedicato ai clienti loggati.
                     */
                Cliente c = (Cliente) logged;
                System.out.println("[1] Aggiungi ai preferiti");
                System.out.println("[2] Visualizza preferiti");
                System.out.println("[3] Aggiungi recensione");
                System.out.println("[4] Modifica recensione");
                System.out.println("[5] Cancella recensione");
                System.out.println("[6] Logout");
                System.out.println("[7] Visualizza ristoranti");
                System.out.println("[8] Ricerca ristorante");
                System.out.println("[9] Visualizza recensioni ");
                System.out.print("Scelta: ");
                String scelta = sc.nextLine();

                switch (scelta) {
                    case "1":
                        /**
                         * Permette al cliente di aggiungere un ristorante ai preferiti.
                         */
                        System.out.print("Nome ristorante da aggiungere ai preferiti: ");
                        String nomeR = sc.nextLine();
                        Ristorante r = manager.getRistoranti().stream()
                            .filter(rr -> rr.getNome().equalsIgnoreCase(nomeR))
                            .findFirst().orElse(null);
                        if (r != null)
                            manager.aggiungiPreferitoAlClienteLoggato(r);
                        else
                            System.out.println("Ristorante non trovato.");
                        break;
                    case "2":
                    List<Ristorante> preferiti = c.getPreferiti();

                    if (preferiti != null && !preferiti.isEmpty()) {
                        // Ordina i ristoranti preferiti per nome (puoi cambiare il criterio se vuoi)
                        preferiti.sort(Comparator.comparing(Ristorante::getNome));

                        // Stampa intestazione della tabella
                        System.out.printf("%-25s %-20s %-30s %-20s%n", "Nome", "Città", "Indirizzo", "Tipo di cucina");
                        System.out.println("------------------------------------------------------------------------------------------");

                        // Stampa ogni ristorante in formato tabellare
                        for (Ristorante ristorante : preferiti) {
                            System.out.printf("%-25s %-20s %-30s %-20s%n",
                                ristorante.getNome(),
                                ristorante.getCitta(),
                                ristorante.getIndirizzo(),
                                ristorante.getTipoCucina());
                        }
                    } else {
                        System.out.println("Nessun preferito presente.");
                    }
                                                                                        /**
                                                                                         * Visualizza i ristoranti preferiti del cliente.
                                                                                         */
                                                                                        /*if (c.getPreferiti() != null && !c.getPreferiti().isEmpty())
                                                                                            c.getPreferiti().forEach(System.out::println);
                                                                                        else
                                                                                            System.out.println("Nessun preferito presente.");*/
                     break;
                    case "3":
                        /**
                         * Permette al cliente di aggiungere una recensione.
                         */
                        System.out.print("Nome ristorante: ");
                        String nomeRest = sc.nextLine();
                        Ristorante rest = manager.getRistoranti().stream()
                            .filter(rr -> rr.getNome().equalsIgnoreCase(nomeRest))
                            .findFirst().orElse(null);
                        if (rest != null) {
                            System.out.print("Voto (1-5): ");
                            int voto = Integer.parseInt(sc.nextLine());
                            System.out.print("Commento: ");
                            String commento = sc.nextLine();
                            manager.aggiungiRecensione(rest, voto, commento);
                        } else {
                            System.out.println("Ristorante non trovato.");
                        }
                        break;
                    case "4":
                        manager.modificaRecensione(sc); 
                        break;
                    case "5":
                        manager.cancellaRecensione(sc); 
                        break;
                    case "6":
                        manager.setLogged(null);
                        System.out.println("Logout effettuato.");
                        break;
                             case "7":
                        /**
                         * Mostra la lista dei ristoranti.
                         */
                        System.out.println("\nLista dei ristoranti:");
                        if (manager.getRistoranti().isEmpty()) {
                            System.out.println("Nessun ristorante disponibile.");
                            break;
                        }
                        // Stampa l'intestazione della tabella

                System.out.println("Nome                Indirizzo                      Tipo Cucina");
                System.out.println("---------------------------------------------------------------");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Sleep interrotto.");
                        }
                for (Ristorante ristorante : manager.getRistoranti()) {
                    String nomeRistorante = String.format("%-20s", ristorante.getNome());
                    String indirizzo = String.format("%-30s", ristorante.getIndirizzo());
                    String tipo = String.format("%-15s", ristorante.getTipoCucina());
                    System.out.println(nomeRistorante + indirizzo + tipo);
                }

                break;

                 case "8":
                                System.out.println("Scegli un'opzione di ricerca:");
                        System.out.println("1 - Cerca per nome");
                        System.out.println("2 - Cerca per città");
                        System.out.println("3 - Cerca per tipo di cucina");

                        int scelta1 = sc.nextInt();
                        sc.nextLine(); // consuma newline

                        if (manager.getRistoranti() != null && !manager.getRistoranti().isEmpty()) {
                            int i = 0; // Contatore per i ristoranti trovati

                            // Intestazione tabella
                            System.out.printf("%-20s %-30s %-20s%n", "Nome", "Indirizzo", "Tipo Cucina");
                            System.out.println("----------------------------------------------------------------------");

                            switch (scelta1) {
                                case 1:
                                    System.out.print("Inserisci il nome del ristorante: ");
                                    String nomeRicerca = sc.nextLine();
                                    for (Ristorante ristorante : manager.getRistoranti()) {
                                        if (ristorante.getNome().equalsIgnoreCase(nomeRicerca)) {
                                            System.out.printf("%-20s %-30s %-20s%n", ristorante.getNome(), ristorante.getIndirizzo(), ristorante.getTipoCucina());
                                            i++;
                                        }
                                    }
                                    break;

                                case 2:
                                    System.out.print("Inserisci la città del ristorante: ");
                                    String citta = sc.nextLine();
                                    for (Ristorante ristorante : manager.getRistoranti()) {
                                        if (ristorante.getIndirizzo().toLowerCase().contains(citta.toLowerCase())) {
                                            System.out.printf("%-20s %-30s %-20s%n", ristorante.getNome(), ristorante.getIndirizzo(), ristorante.getTipoCucina());
                                            i++;
                                        }
                                    }
                                    break;

                                case 3:
                                    System.out.print("Inserisci il tipo di cucina (es: Italiana, Giapponese): ");
                                    String tipo = sc.nextLine();
                                    for (Ristorante ristorante : manager.getRistoranti()) {
                                        if (ristorante.getTipoCucina().equalsIgnoreCase(tipo)) {
                                            System.out.printf("%-20s %-30s %-20s%n", ristorante.getNome(), ristorante.getIndirizzo(), ristorante.getTipoCucina());
                                            i++;
                                        }
                                    }
                                    break;

                                default:
                                    System.out.println("Scelta non valida.");
                                    return;
                            }

                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Sono stati trovati " + i + " ristorante/i corrispondente/i.");
                        } else {
                            System.out.println("Nessun ristorante disponibile.");
                        }

                        break;

                    case "9":


                      Scanner scanner = new Scanner(System.in);
                            System.out.print("Inserisci il nome del ristorante: ");
                            String nomeRistorante = scanner.nextLine();

                            System.out.printf("\n%-15s %-20s %-10s %-50s\n", "Username", "Data", "Voto", "Commento");
                            System.out.println("--------------------------------------------------------------------------------------------");

                            boolean trovata = false;

                            for (Recensione rec : manager.getRecensioni()) {
                                if (rec.getRistorante().equalsIgnoreCase(nomeRistorante)) {
                                    System.out.printf("%-15s %-20s %-10d %-50s\n",
                                        rec.getAutore(),
                                        rec.getData().toString(),
                                        rec.getVoto(),
                                        rec.getCommento());
                                    trovata = true;
                                }
                            }

                            if (!trovata) {
                                System.out.println("Nessuna recensione trovata per il ristorante \"" + nomeRistorante + "\".");
                            }

                            break;
                    default:
                        System.out.println("Opzione non valida.");
                }
                
            }  else if (logged instanceof Ristoratore) {
                System.out.println("[1] Inserisci ristorante"); 
                System.out.println("[2] Visualizza recensioni dei tuoi ristoranti e rispondi"); 
                System.out.println("[3] Visualizza statistiche dei tuoi ristoranti"); // TODO
                System.out.println("[4] Logout");
                System.out.print("Scelta: ");
                String scelta = sc.nextLine();
            
                switch (scelta) {
                    case "1":
                        manager.inserisciRistorante(sc); 
                        break;
                    case "2":
                        manager.visualizzaERispondiRecensioni(sc); 
                        break;
                    case "3":
                        manager.statisticheRistoranti();                         
                        break;
                    case "4":
                        manager.setLogged(null);
                        System.out.println("Logout effettuato.");
                        break;
                    default:
                        System.out.println("Opzione non valida.");
                }
            }

        }
    }
}
            
