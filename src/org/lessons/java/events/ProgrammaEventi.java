package org.lessons.java.events;

import java.util.ArrayList;
import java.util.List;

public class ProgrammaEventi {
    // ATTRIBUTI
    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo, ArrayList<Evento> eventi) {
        this.titolo = titolo;
        this.eventi = eventi;
    }

    // METODI
    //metodo che aggiunge alla lista un evento passato come parametro
    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }
    // metodo che restituisce una lista con tutti gli eventi presenti in una certa data
    public List<Evento> eventiInData(String data) {
        List<Evento> eventiInData = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getData().equals(data)) {
                eventiInData.add(evento);
            }
        }
        return eventiInData;
    }

    // metodo che restituisce quanti eventi sono presenti nel programma
    public int numeroEventi() {
        return eventi.size();
    }
    // metodo che svuota la lista di eventi
    public void svuotaEventi() {
        eventi.clear();
    }
    // metodo che restituisce una stringa che mostra il titolo del programma e tutti gli eventi ordinati per data nella forma: data1 - titolo1, data2 - titolo2, ...
    @Override
    public String toString() {
        StringBuilder stringa = new StringBuilder(titolo + "\n");
        for (Evento evento : eventi) {
            stringa.append(evento.getData()).append(" - ").append(evento.getTitolo()).append(", ");
        }
        return stringa.toString();
    }
}
