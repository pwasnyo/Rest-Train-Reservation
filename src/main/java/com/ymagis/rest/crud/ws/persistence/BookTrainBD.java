package com.ymagis.rest.crud.ws.persistence;

import com.ymagis.rest.crud.ws.model.BookTrain;
import com.ymagis.rest.crud.ws.model.Train;
import java.util.ArrayList;
import java.util.List;

/**
 * BookTrainBD sert à laisser persister toutes les informations concernant le
 * service web
 */
public class BookTrainBD {

    private static List<Train> trains = new ArrayList<Train>();

    private static List<BookTrain> bookTrains = new ArrayList<BookTrain>();

    static {
        trains.add(new Train("TR123", "Poitiers", "Paris", 1250));
        trains.add(new Train("TR127", "Poitiers", "Paris", 1420));
        trains.add(new Train("TR129", "Poitiers", "Paris", 1710));
    }

    public static List<Train> getTrains() {
        return trains;
    }

    public static List<BookTrain> getBookTrains() {
        return bookTrains;
    }
}
