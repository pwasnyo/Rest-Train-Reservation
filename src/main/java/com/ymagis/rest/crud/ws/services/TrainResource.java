package com.ymagis.rest.crud.ws.services;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.ymagis.rest.crud.ws.model.Train;
import com.ymagis.rest.crud.ws.persistence.BookTrainBD;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/trains")
@Api(value="trains")
// au dessus de la classe veut dire que toutes les méthodes de la class produirnt du xml
//@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class TrainResource {

    public TrainResource() {}

    /*  http://localhost:8080/train-reservation-ws/rest/trains/getAllTrains
        train-reservation-w est <finalName> dans le pom.xml
        /rest/* se trouve dans le web.xml et trains/getAll c'est le path
    */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/getAllTrains")
    @ApiOperation(value="Get all the train available")
    public List<Train> getTrains() {
        return BookTrainBD.getTrains();
    }

    //http://localhost:8080/train-reservation-ws/rest/trains/numTrain-TR123
    @GET
    @Path("/numTrain-{id}")
    @Produces(MediaType.APPLICATION_XML)
    @ApiOperation(value="Get a train according to is id as this numTrain-TR123")
    public Train getTrain(@PathParam("id") String numTrain) {
        System.out.println("getTrain");

        for (Train current : BookTrainBD.getTrains()) {
            if (numTrain.equals(current.getNumTrain())) {
                return current;
            }
        }
        return null;
    }

    //http://localhost:8080/train-reservation-ws/rest/trains/search?departure=poitiers&arrival=paris&arrivalhour=1250
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_XML)
    @ApiOperation(value="search train by giving the departure, arrival and arrivalhour")
    public List<Train> searchTrainsByCriteria(@QueryParam("departure") String departure, @QueryParam("arrival") String arrival, @QueryParam("arrivalhour") String arrivalHour) {
        System.out.println("searchTrainsByCriteria");
        List<Train> trains = new ArrayList<>();
        for (Train train : BookTrainBD.getTrains()) {
            if(train.getVilleDepart().equals(departure) && train.getVilleArrivee().equals(arrival) && train.getHeureDepart() == Integer.parseInt(arrivalHour)){
                trains.add(train);
            }
        }
        return trains;
    }

    @Path("/booktrains")
    public BookTrainResource getBookTrainResource() {
        return new BookTrainResource();
    }
}
