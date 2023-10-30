package org.lessons.java.events;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    // ATTRIBUTI
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    //    costruttore
    public Evento(String titolo, LocalDate data, int postiTotali) {
        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

//    EXCEPTIONS
    //    data che non sia già passata

    public void dataPassata() {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data non valida");
        }
    }

    //    posti totali > 0
    public void postiPositivi() {
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Posti totali non validi");
        }
    }

    //    posti prenotati <= posti totali
    public void postiPrenotatiMinoriTotali() {
        if (postiPrenotati > postiTotali) {
            throw new IllegalArgumentException("Posti prenotati non validi");
        }
    }


//    METODI
    //  GETTERS


    public String getTitolo() {
        return titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    //  SETTERS

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    // METODO PRENOTA
    public void prenota(int posti) {
        postiPrenotati += posti;
    }

    //METODO DISDICI
    public void disdici(int posti) {
        postiPrenotati -= posti;
    }

    // OVERRIDE TOSTRING
    @Override
    public String toString(){
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + titolo;
    }
}
