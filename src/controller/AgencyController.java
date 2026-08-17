package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agency;
import model.Trip;

public class AgencyController extends Controller<Agency>{
    @FXML private Label welcomeLbl;

    @FXML public void initialize(){
        welcomeLbl.setText("Hi " + getAgency().getLoggedInUser().getName() +", welcome to the Prog2 Travel Agency");
        stage.getIcons().add(new Image("/image/agency_icon.png"));
    }
    
    public final Agency getAgency(){ return model; }

    @FXML private void handleFlight(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency(), "/view/Flights/ExploreFlightsView.fxml", "Explore Flights", new Stage());
    }

    @FXML private void handleDestination(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency(), "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations", new Stage());
    }

    @FXML private void handleTrip(ActionEvent event) throws Exception{
        ViewLoader.showStage(new Trip(getAgency()), "/view/Trip/BookTripView.fxml", "Explore Trips", new Stage());
    }

    @FXML private void handleExit(ActionEvent event) throws Exception{
        stage.close();
    }
}