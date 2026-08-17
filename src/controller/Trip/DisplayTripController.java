package controller.Trip;

import java.util.InputMismatchException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Destination;
import model.Destinations;
import model.Flight;
import model.Flights;
import model.Itinery;
import model.Trip;
import model.Exceptions.ErrorModel;

public class DisplayTripController extends Controller<Trip>{
    @FXML private ListView<Itinery> tripLv;

    @FXML public void initialize(){
        stage.getIcons().add(new Image("/image/trip_icon.png"));
        tripLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tripLv.setItems(getTrip().getItinery());
        
        getTrip().getDestinations().getDestinations().addListener(new ListChangeListener<Destination>() {
            @Override
            public void onChanged(Change<? extends Destination> changed){
                while (changed.next()){
                    if (changed.wasAdded()){
                        tripLv.setItems(getTrip().getItinery());
                    }
                    if (changed.wasRemoved()){
                        getTrip().getFlights().getFlights().clear();
                        tripLv.setItems(getTrip().getItinery());
                    }
                }
            }
        });

        getTrip().getFlights().getFlights().addListener(new ListChangeListener<Flight>() {
            @Override
            public void onChanged(Change<? extends Flight> changed){
                while (changed.next()){
                    if (changed.wasAdded()){
                        tripLv.setItems(getTrip().getItinery());
                    }
                    if (changed.wasRemoved()){
                        tripLv.setItems(getTrip().getItinery());
                    }
                }
            }
        });
    }

    public final Trip getTrip(){ return model; }

    public final ObservableList<Itinery> getSelected(){
        return tripLv.getSelectionModel().getSelectedItems();
    }

    @FXML private void handleView(ActionEvent event) throws Exception{
        if (getTrip().getFlights().getFlights().containsAll(getSelected())){
            Flights flights = new Flights(getSelected());
            ViewLoader.showStage(flights, "/view/Flights/DisplayFlightsView.fxml", "View Flights", new Stage());
        }
        else if (getTrip().getDestinations().getDestinations().containsAll(getSelected())){
            Destinations destinations = new Destinations(getSelected());
            ViewLoader.showStage(destinations, "/view/Destinations/DisplayDestinationsView.fxml", "View Destinations", new Stage());
        }
        else {
            ViewLoader.showStage(new ErrorModel(new InputMismatchException(), "Selected items do not match"), "/view/Error/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}