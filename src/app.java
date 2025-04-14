package src;

import java.util.List;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.avviaApplicazione();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Benvenuto su TheKnife ===");

            Utente logged = manager.getLogged();

            if (logged == null) {
                System.out.println("[1] Visualizza ristoranti");
                System.out.println("[2] Visualizza recensioni anonime");
                System.out.println("[3] Registrati");
                System.out.println("[4] Login");
                System.out.println("[0] Esci");
                System.out.print("Scelta: ");
                String scelta = sc.nextLine();

                switch (scelta) {
                    case "1":
                        for (Ristorante r : manager.getRistoranti())
                            System.out.println(r);
                        break;
                    case "2":
                        for (Recensione rec : manager.getRecensioni())
                            System.out.println(rec);
                        break;
                    case "3":
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Cognome: ");
                        String cognome = sc.nextLine();
                        System.out.print("Username: ");
                        String username = sc.nextLine();
                        System.out.print("Password: ");
                        String password = sc.nextLine();
                        System.out.print("Data di nascita (yyyy-mm-dd): ");
                        String data = sc.nextLine();
                        System.out.print("Domicilio: ");
                        String domicilio = sc.nextLine();
                        System.out.print("Tipo utente (0 = Cliente, 1 = Ristoratore): ");
                        String ruolo = sc.nextLine();

                        Utente nuovo = new Utente(
                            nome, cognome, username, password,
                            java.time.LocalDate.parse(data),
                            domicilio,
                            ruolo.equals("0") // true se cliente
                        );
                        manager.registraUtente(nuovo);
                        break;
                    case "4":
                        System.out.print("Username: ");
                        String user = sc.nextLine();
                        System.out.print("Password: ");
                        String pass = sc.nextLine();
                        manager.login(user, pass);
                        break;
                    case "0":
                        System.out.println("Arrivederci!");
                        return;
                    default:
                        System.out.println("Opzione non valida.");
                }

            }  else if (logged instanceof Cliente) {
                Cliente c = (Cliente) logged;
            
                System.out.println("[1] Aggiungi ai preferiti");
                System.out.println("[2] Visualizza preferiti");
                System.out.println("[3] Aggiungi recensione");
                System.out.println("[4] Modifica recensione"); // TODO
                System.out.println("[5] Cancella recensione"); // TODO
                System.out.println("[6] Logout");
                System.out.print("Scelta: ");
                String scelta = sc.nextLine();
            
                switch (scelta) {
                    case "1":
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
                        if (c.getPreferiti() != null && !c.getPreferiti().isEmpty())
                            c.getPreferiti().forEach(System.out::println);
                        else
                            System.out.println("Nessun preferito presente.");
                        break;
                    case "3":
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
                    default:
                        System.out.println("Opzione non valida.");
                }
            
            // === RISTORATORE ===
            } else if (logged instanceof Ristoratore) {
                System.out.println("[1] Inserisci ristorante"); // TODO
                System.out.println("[2] Visualizza recensioni dei tuoi ristoranti e rispondi"); // TODO
                System.out.println("[3] Visualizza statistiche dei tuoi ristoranti"); // TODO
                System.out.println("[4] Logout");
                System.out.print("Scelta: ");
                String scelta = sc.nextLine();
            
                switch (scelta) {
                    case "1":
                        manager.inserisciRistorante(sc); 
                        break;
                    case "2":
                        // manager.visualizzaERispondiRecensioni(); // TODO
                        System.out.println("[TODO] Visualizza recensioni e rispondi");
                        break;
                    case "3":
                        // manager.statisticheRistoranti(); // TODO
                        System.out.println("[TODO] Statistiche ristorante");
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
