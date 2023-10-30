package org.lessons.java.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento{
    // ATTRIBUTI
    private LocalTime oraInizio;
    private BigDecimal prezzo;

    //    COSTRUTTORE
    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime oraInizio, BigDecimal prezzo) {
        super(titolo, data, postiTotali);
        this.oraInizio = oraInizio;
        this.prezzo = prezzo;
    }

    // METODI
    // GETTERS


    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    // SETTERS

    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    // DATA e ORA FORMATTATE [dd-mm-yyyy hh:mm]
    private DateTimeFormatter getOraFormattata() {
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    private DateTimeFormatter getDataFormattata() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public String getDataOraFormattate() {
        return getData().format(getDataFormattata()) + " " + getOraInizio().format(getOraFormattata());
    }

    // PREZZO FORMATTATO [##,##€]
    private String getPrezzoFormattato() {
        return getPrezzo().setScale(2) + "€";
    }

    // TO STRING [data e ora formattate - titolo - prezzo formattato]
    @Override
    public String toString() {
        return getDataOraFormattate() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }


}
