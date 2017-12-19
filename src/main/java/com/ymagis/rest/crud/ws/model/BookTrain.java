package com.ymagis.rest.crud.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * La classe BookTrain modélise le concept de réservation de Train qui contient
 * un attribut String numBook (identifiant fonctionnel de la réservation d'un
 * train), un attribut Train currentTrain (association sur le train de la
 * réservation), un attribut int numberPlaces (nombre de places réservées).
 */

@XmlRootElement(name = "booktrain")
public class BookTrain {

    private String numBook;
    private Train currenTrain;
    private int numberPlaces;

    public String getNumBook() {
        return numBook;
    }

    public void setNumBook(String bookNumber) {
        this.numBook = bookNumber;
    }

    public Train getCurrenTrain() {
        return currenTrain;
    }

    public void setCurrenTrain(Train currenTrain) {
        this.currenTrain = currenTrain;
    }

    public int getNumberPlaces() {
        return numberPlaces;
    }

    public void setNumberPlaces(int numberPlaces) {
        this.numberPlaces = numberPlaces;
    }
    
    public String toString(){
        return " booking "+this.numBook+" in train "+this.currenTrain;
    }
}
