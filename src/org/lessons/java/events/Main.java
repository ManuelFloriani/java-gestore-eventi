package org.lessons.java.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inizializzazione scanner da tastier
        Scanner scanner = new Scanner(System.in);



        // ************************* INSERIMENTO EVENTO ***************************

        // Stampa video di benvenuto
        System.out.println("INSERIRE UN NUOVO EVENTO");
        System.out.println("------------------------");
        // Richiesta dati evento
        // Chiedo se l'evento è un concerto
        System.out.println("Il nuovo evento è un concerto? (y/n)");

        /******************************************************************************************************
          *********************************************** CONCERTO ****************************************
         ******************************************************************************************************/

        if (scanner.nextLine().equalsIgnoreCase("y")) {
            // Chiedere il titolo lanciando una eccezione se è vuoto
            String titolo = null;
            // Ciclo finchè l'utente non inserisce un titolo
            while (true) {
                System.out.println("Inserire il titolo del concerto:");
                titolo = scanner.nextLine();

                if (!titolo.isEmpty()) {
                    break;
                } else {
                    System.out.println("Il titolo non può essere vuoto!");
                    System.out.println("------------------------");
                }
            }

            // Chiedere la data lanciando una eccezione se è passata o non nel formato corretto
            LocalDate data = null;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Ciclo finchè l'utente non inserisce una data valida
            while (true) {
                System.out.println("Inserire la data del concerto (gg/mm/aaaa):");
                String input = scanner.nextLine();

                try {
                    data = LocalDate.parse(input, dateFormatter);

                    // Confronta la data inserita con la data corrente
                    if (data.isBefore(LocalDate.now())) {
                        System.out.println("La data inserita è nel passato. Riprova.");
                        System.out.println("------------------------");
                        continue; // Continua il ciclo senza uscire
                    }

                    break; // Esci dal ciclo se la data è valida e nel futuro
                } catch (DateTimeParseException e) {
                    System.out.println("La data inserita non è nel formato corretto (gg/mm/aaaa). Riprova.");
                    System.out.println("------------------------");
                }
            }
            // Chiedere l'ora lanciando una eccezione se non nel formato corretto
            String ora = null;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            // Ciclo finchè l'utente non inserisce un'ora valida
            while (true) {
                System.out.println("Inserire l'ora del concerto (hh:mm):");
                String input = scanner.nextLine();

                try {
                    ora = input;
                    LocalTime.parse(input, timeFormatter);

                    break; // Esci dal ciclo se l'ora è valida
                } catch (DateTimeParseException e) {
                    System.out.println("L'ora inserita non è nel formato corretto (hh:mm). Riprova.");
                    System.out.println("------------------------");
                }
            }
            // Chiedere il prezzo lanciando una eccezione se è minore o uguale a 0 o in formato non corretto
            BigDecimal prezzo = null;
            // Ciclo finchè l'utente non inserisce un prezzo valido
            while (true) {
                System.out.println("Inserire il prezzo del concerto:");
                String input = scanner.nextLine();

                try {
                    prezzo = new BigDecimal(input);

                    if (prezzo.compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("Il prezzo deve essere maggiore di 0.");
                    }

                    break; // Esci dal ciclo se il prezzo è valido
                } catch (NumberFormatException e) {
                    System.out.println("Il prezzo deve essere un numero.");
                    System.out.println("------------------------");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println("------------------------");
                }
            }
            // Chiedere il numero di posti totali disponibili lanciando una eccezione se è minore o uguale a 0
            int postiTotali = 0;

            while (true) {
                System.out.println("Inserire il numero di posti totali disponibili: ");
                try {
                    postiTotali = Integer.parseInt(scanner.nextLine());
                    if (postiTotali <= 0) {
                        throw new IllegalArgumentException("Il numero di posti totali deve essere maggiore di 0.");
                    }

                    break; // Esci dal ciclo se il numero di posti totali è valido
                } catch (IllegalArgumentException e) {
                    System.out.println("Il numero di posti totali deve essere maggiore di 0.");
                    System.out.println("------------------------");
                }
            }
            // Creazione concerto
            Concerto concerto = new Concerto(titolo, data, postiTotali, LocalTime.parse(ora, timeFormatter), prezzo);
            // Stampa video di conferma
            System.out.println("***************************");
            System.out.println("Concerto creato con successo!");
            System.out.println("Hai creato il concerto " + concerto.getTitolo() + " del " + concerto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " alle " + concerto.getOraInizio().format(DateTimeFormatter.ofPattern("HH:mm")) + " con " + concerto.getPostiTotali() + " posti totali e prezzo " + concerto.getPrezzo() + "€.");
            System.out.println("***************************");

            //************************* PRENOTAZIONI ***************************

            // Stampa video inserimento prenotazioni
            System.out.println("INSERIRE PRENOTAZIONI? (y/n)");
            // Se l'utente vuole inserire prenotazioni
            int posti = 0;

            if (scanner.nextLine().equalsIgnoreCase("y")) {
                boolean prenotazioneEffettuata = false;

                while (!prenotazioneEffettuata) {
                    // Chiedo il numero di posti da prenotare
                    System.out.println("Inserire il numero di posti da prenotare:");

                    String input = scanner.nextLine();
                    // Gestione delle eccezioni [vuoto, non numerico, non positivo, non maggiore di posti totali]
                    try {
                        posti = Integer.parseInt(input);

                        if (posti <= 0) {
                            throw new IllegalArgumentException("Il numero di posti da prenotare deve essere maggiore di 0.");
                        } else if (posti > concerto.getPostiTotali()) {
                            throw new IllegalArgumentException("Il numero di posti da prenotare deve essere minore o uguale a " + concerto.getPostiTotali() + ".");
                        }

                        prenotazioneEffettuata = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Il numero di posti da prenotare deve essere un numero.");
                        System.out.println("------------------------");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("------------------------");
                    }

                }
                // setta i posti prenotati uguale a posti
                concerto.prenota(posti);
                // Stampo un messaggio di conferma
                System.out.println("Grazie! Hai prenotato " + posti + " posti per " + concerto);
                System.out.println("Rimangono " + (concerto.getPostiTotali() - posti) + " posti disponibili.");
            }
            else {
                System.out.println("OK, nessuna prenotazione inserita.");
            }


            //************************* DISDETTE ***************************

            // Stampa video inserimento disdette
            System.out.println("DISDIRE PRENOTAZIONI? (y/n)");
            // Se l'utente vuole inserire disdette
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                boolean disdettaEffettuata = false;

                int postiDaDisdire = 0 ;
                while (!disdettaEffettuata) {
                    // Chiedo il numero di posti da disdire
                    System.out.println("Inserire il numero di posti da disdire:");

                    postiDaDisdire = Integer.parseInt(scanner.nextLine());
                    // Gestione delle eccezioni [vuoto, non numerico, non positivo]
                    try {
                        if (postiDaDisdire <= 0) {
                            throw new IllegalArgumentException("Il numero di posti da disdire deve essere maggiore di 0.");
                        } else if (postiDaDisdire > concerto.getPostiPrenotati()) {
                            throw new IllegalArgumentException("Il numero di posti da disdire non può essere superiore ai posti prenotati.");
                        }

                        disdettaEffettuata = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Il numero di posti da disdire deve essere un numero.");
                        System.out.println("------------------------");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("------------------------");
                    }
                    // Aggiorna i posti prenotati
                }
                concerto.disdici(postiDaDisdire);

                // Stampo un messaggio di conferma
                System.out.println("Grazie! Hai disdetto " + postiDaDisdire + " posti per l'concerto " + concerto.getTitolo() + " del " + concerto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ". ");
                System.out.println("Rimangono " + (concerto.getPostiTotali() - concerto.getPostiPrenotati()) + " posti disponibili.");
            }
            else {
                System.out.println("OK, nessuna disdetta inserita.");
            }



        } else {

            /******************************************************************************************************
             * *********************************************** EVENTO ****************************************
             ******************************************************************************************************/

            // Chiedere il titolo lanciando una eccezione se è vuoto
            String titolo = null;
            // Ciclo finchè l'utente non inserisce un titolo
            while (true) {
                System.out.println("Inserire il titolo dell'evento:");
                titolo = scanner.nextLine();

                if (!titolo.isEmpty()) {
                    break;
                } else {
                    System.out.println("Il titolo non può essere vuoto!");
                    System.out.println("------------------------");
                }
            }

            // Chiedere la data lanciando una eccezione se è passata o non nel formato corretto
            LocalDate data = null;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Ciclo finchè l'utente non inserisce una data valida
            while (true) {
                System.out.println("Inserire la data dell'evento (gg/mm/aaaa):");
                String input = scanner.nextLine();

                try {
                    data = LocalDate.parse(input, dateFormatter);

                    // Confronta la data inserita con la data corrente
                    if (data.isBefore(LocalDate.now())) {
                        System.out.println("La data inserita è nel passato. Riprova.");
                        System.out.println("------------------------");
                        continue; // Continua il ciclo senza uscire
                    }

                    break; // Esci dal ciclo se la data è valida e nel futuro
                } catch (DateTimeParseException e) {
                    System.out.println("La data inserita non è nel formato corretto (gg/mm/aaaa). Riprova.");
                    System.out.println("------------------------");
                }
            }

            // Chiedere il numero di posti totali disponibili lanciando una eccezione se è minore o uguale a 0
            int postiTotali = 0;

            while (true) {
                System.out.println("Inserire il numero di posti totali disponibili: ");
                try {
                    postiTotali = Integer.parseInt(scanner.nextLine());
                    if (postiTotali <= 0) {
                        throw new IllegalArgumentException("Il numero di posti totali deve essere maggiore di 0.");
                    }

                    break; // Esci dal ciclo se il numero di posti totali è valido
                } catch (IllegalArgumentException e) {
                    System.out.println("Il numero di posti totali deve essere maggiore di 0.");
                    System.out.println("------------------------");
                }
            }

            // Creazione evento
            Evento evento = new Evento(titolo, data, postiTotali);
            // Stampa video di conferma
            System.out.println("***************************");
            System.out.println("Evento creato con successo!");
            System.out.println("Hai creato l'evento " + evento.getTitolo() + " del " + evento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " con " + evento.getPostiTotali() + " posti totali.");
            System.out.println("***************************");


            //************************* PRENOTAZIONI ***************************

            // Stampa video inserimento prenotazioni
            System.out.println("INSERIRE PRENOTAZIONI? (y/n)");
            // Se l'utente vuole inserire prenotazioni
            int posti = 0;

            if (scanner.nextLine().equalsIgnoreCase("y")) {
                boolean prenotazioneEffettuata = false;

                while (!prenotazioneEffettuata) {
                    // Chiedo il numero di posti da prenotare
                    System.out.println("Inserire il numero di posti da prenotare:");

                    String input = scanner.nextLine();
                    // Gestione delle eccezioni [vuoto, non numerico, non positivo, non maggiore di posti totali]
                    try {
                        posti = Integer.parseInt(input);

                        if (posti <= 0) {
                            throw new IllegalArgumentException("Il numero di posti da prenotare deve essere maggiore di 0.");
                        } else if (posti > evento.getPostiTotali()) {
                            throw new IllegalArgumentException("Il numero di posti da prenotare deve essere minore o uguale a " + evento.getPostiTotali() + ".");
                        }

                        prenotazioneEffettuata = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Il numero di posti da prenotare deve essere un numero.");
                    System.out.println("------------------------");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("------------------------");
                    }

                }
                // setta i posti prenotati uguale a posti
                evento.prenota(posti);
                // Stampo un messaggio di conferma
                System.out.println("Grazie! Hai prenotato " + posti + " posti per " + evento);
                System.out.println("Rimangono " + (evento.getPostiTotali() - posti) + " posti disponibili.");
            }
            else {
                System.out.println("OK, nessuna prenotazione inserita.");
            }


            //************************* DISDETTE ***************************

            // Stampa video inserimento disdette
            System.out.println("DISDIRE PRENOTAZIONI? (y/n)");
            // Se l'utente vuole inserire disdette
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                boolean disdettaEffettuata = false;

                int postiDaDisdire = 0 ;
                while (!disdettaEffettuata) {
                    // Chiedo il numero di posti da disdire
                    System.out.println("Inserire il numero di posti da disdire:");

                    postiDaDisdire = Integer.parseInt(scanner.nextLine());
                    // Gestione delle eccezioni [vuoto, non numerico, non positivo]
                    try {
                        if (postiDaDisdire <= 0) {
                            throw new IllegalArgumentException("Il numero di posti da disdire deve essere maggiore di 0.");
                        } else if (postiDaDisdire > evento.getPostiPrenotati()) {
                            throw new IllegalArgumentException("Il numero di posti da disdire non può essere superiore ai posti prenotati.");
                        }

                        disdettaEffettuata = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Il numero di posti da disdire deve essere un numero.");
                        System.out.println("------------------------");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("------------------------");
                    }
                    // Aggiorna i posti prenotati
                }
                    evento.disdici(postiDaDisdire);

                // Stampo un messaggio di conferma
                System.out.println("Grazie! Hai disdetto " + postiDaDisdire + " posti per l'evento " + evento.getTitolo() + " del " + evento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ". ");
                System.out.println("Rimangono " + (evento.getPostiTotali() - evento.getPostiPrenotati()) + " posti disponibili.");
            }
            else {
                System.out.println("OK, nessuna disdetta inserita.");
            }
        }
    }
}



