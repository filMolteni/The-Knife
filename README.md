# 🍴 TheKnife

**TheKnife** è una piattaforma Java ispirata a "TheFork", pensata per permettere agli utenti di trovare ristoranti in tutto il mondo, visualizzarne i dettagli, inserire recensioni, aggiungere preferiti e per i gestori di gestire le proprie attività.

## 📌 Funzionalità principali

### 🔓 Utenti non registrati
- Visualizzazione di tutti i ristoranti con dettagli (nome, indirizzo, cucina, fascia di prezzo, servizi)
- Visualizzazione delle recensioni in forma anonima
- Registrazione come **cliente** o **ristoratore**
- Login

### 👤 Clienti registrati
- Definizione e visualizzazione dei **ristoranti preferiti**
- Inserimento di **recensioni** (1-5 stelle + commento)
- Modifica e cancellazione delle proprie recensioni *(in sviluppo)*
- Logout

### 🧑‍🍳 Ristoratori registrati
- Inserimento di un **nuovo ristorante** *(in sviluppo)*
- Visualizzazione delle recensioni ricevute e possibilità di rispondere *(in sviluppo)*
- Visualizzazione di **statistiche** sul proprio ristorante (media voti, n. recensioni) *(in sviluppo)*
- Logout

## 🗂️ Struttura del progetto

TheKnife/ │ ├── src/ # Codice sorgente │ ├── App.java # Punto di ingresso con menu interattivo │ ├── Manager.java # Classe di gestione centrale │ ├── FileManager.java # Gestione di lettura/scrittura file CSV │ ├── Ristorante.java # Classe che rappresenta un ristorante │ ├── Cliente.java # Classe utente cliente │ ├── Ristoratore.java # Classe utente gestore │ ├── Utente.java # Classe base per Cliente e Ristoratore │ ├── Recensione.java # Modello per recensioni │ ├── Preferito.java # Associazione tra utente e ristoranti preferiti │ └── FasciaPrezzo.java # Enum per fasce di prezzo │ ├── data/ # File CSV di salvataggio persistente │ ├── utenti.csv │ ├── ristoranti.csv │ ├── recensioni.csv │ ├── preferiti.csv │ ├── gestiti.csv │ └── michelin_my_maps.csv # File sorgente originale (esempio Michelin)

## ⚙️ Requisiti

- Java 17 o superiore
- IDE consigliato: IntelliJ IDEA / Eclipse / VS Code
- Librerie esterne: nessuna

## ▶️ Avvio del programma

1. Clona il repository
2. Apri il progetto in un IDE Java
3. Esegui il file `App.java`
4. Interagisci con il menu da console

## 📦 Persistenza dati

Il sistema salva e legge i dati da file `.csv` contenuti nella cartella `data/`. Ogni tipo di dato ha un file dedicato:
- `utenti.csv`: clienti e ristoratori
- `ristoranti.csv`: elenco ristoranti
- `recensioni.csv`: tutte le recensioni registrate
- `preferiti.csv`: preferiti per ogni cliente
- `gestiti.csv`: ristoranti gestiti da ristoratori

## 🚧 Funzionalità future

- [ ] Inserimento/modifica ristoranti da parte dei gestori
- [ ] Statistiche avanzate per i gestori
- [ ] Miglioramento del parser CSV (gestione avanzata delle virgolette)
- [ ] Interfaccia grafica (JavaFX o Swing)

## 📌 Autori

Progetto sviluppato per il **Laboratorio Interdisciplinare A** - Università degli Studi  **Insubria**
*Anno accademico 2024/2025*
Partecipanti *Filippo Molteni, Enea Giana, Simone Marcarini*
---

