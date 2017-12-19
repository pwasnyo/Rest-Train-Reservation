package com.ymagis.rest.crud.ws.services;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.ymagis.rest.crud.ws.model.BookTrain;
import com.ymagis.rest.crud.ws.model.Train;
import com.ymagis.rest.crud.ws.persistence.BookTrainBD;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * La classe BookTrainResource permettant l'accès aux services web REST de la
 * ressource Réservation. Quatre méthodes sont à définir: La première
 * createBookTrain est invoquée pour la création d'une ressource Réservation
 * (méthode POST). La deuxième getBookTrains(méthode GET) est utilisée pour
 * lister l'ensemble des réservations. La troisième getBookTrain(méthode GET)
 * permet de retourner les informations d'une réservation à partir d'un numéro
 * de réservation. Finalement removeBookTrain(méthode DELETE) permet de
 * supprimer une réservation.
 */
@Path("/trainBooking")
@Api(value="trainBooking")
public class BookTrainResource {

    //http://localhost:8080/train-reservation-ws/rest/trainBooking/bookTrain?numTrain=TR123&numberPlaces=2
    // on ne peut pas faire de post ni de put en http
    @POST
    @Path("/bookTrain")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value="Allow to book a train by giving is id and the number of places with want to book, after that it return you the booking id else if id train is false no booking will be done")
    public Response createBookTrain(@QueryParam("numTrain") String numTrain, @QueryParam("numberPlaces") int numberPlaces) {
        Train currentTrain = null;
        //on cherche si en db(ici notre List de train) si le train en paramètre dans lequel on veut réservé existe
        for (Train current : BookTrainBD.getTrains()) {
            if (current.getNumTrain().equals(numTrain)) {
                currentTrain = current;
            }
        }

        if (currentTrain == null) {
            return Response
                    .status(Status.NO_CONTENT)
                    .entity("No Train with id "+numTrain)
                    .build();
        }

        BookTrain newBookTrain = new BookTrain();
        newBookTrain.setNumberPlaces(numberPlaces);
        newBookTrain.setCurrenTrain(currentTrain);
        newBookTrain.setNumBook(Long.toString(System.currentTimeMillis()));

        BookTrainBD.getBookTrains().add(newBookTrain);

        return Response
                .status(Status.OK)
                .entity(newBookTrain.getNumBook())
                .build();
    }

    @GET
    @Path("/bookedTrains")
    @Produces({MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
    @ApiOperation(value="get all booked trains")
    public Response getBookTrains() {
        System.out.println("getBookTrains");
        if (BookTrainBD.getBookTrains().size() == 0) {
            String noContent="<train>\n"
                    + "<heureDepart>NoContent</heureDepart>\n"
                    + "<numTrain>NoContent</numTrain>\n"
                    + "<villeArrivee>NoContent</villeArrivee>\n"
                    + "<villeDepart>NoContent</villeDepart>\n"
                    + "</train>";
            return Response
                    .status(Status.NO_CONTENT)
                    .entity(noContent)
                    .build();
        }
        return Response
                .status(Status.OK)
                .entity(BookTrainBD.getBookTrains())
                .build();
    }

    @GET
    @Path("/getBooked/{id}")
    @ApiOperation(value="get the train according to booking id number")
    @Produces(MediaType.APPLICATION_XML)
    public Response getBookTrain(@PathParam("id") String bookNumber) {
        List<BookTrain> bookTrains = BookTrainBD.getBookTrains();

        for (BookTrain current : bookTrains) {
            if (current.getNumBook().equals(bookNumber)) {
                return Response
                        .status(Status.OK)
                        .entity(current)
                        .build();
            }
        }

        return Response
                .status(Status.NO_CONTENT)
                .build();
    }

    @DELETE
    @Path("/unbookTrain/{id}")
    @ApiOperation(value="cancel booking with with given id")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeBookTrain(@PathParam("id") String bookNumber) {
        BookTrain currentBookTrain = null;
        for (BookTrain current : BookTrainBD.getBookTrains()) {
            if (current.getNumBook().equals(bookNumber)) {
                currentBookTrain = current;
            }
        }

        BookTrainBD.getBookTrains().remove(currentBookTrain);
        return Response
                .status(Status.ACCEPTED)
                .entity(currentBookTrain+" well cancel")
                .build();
    }
}
