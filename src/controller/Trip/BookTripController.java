package controller.Trip;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Trip;
import model.Exceptions.ErrorModel;
import model.Exceptions.InsufficientDestinationsException;

public class BookTripController extends Controller<Trip>{
    @FXML private Label welcomeLbl;

    @FXML public void initialize(){
        welcomeLbl.setText("Hi " + getTrip().getAgency().getLoggedInUser().getName() +", welcome to the Trip Section");
        stage.getIcons().add(new Image("/image/trip_icon.png"));
    }
    
    public final Trip getTrip(){ return model; }

    @FXML private void handleAddDestination(ActionEvent event) throws Exception{
        ViewLoader.showStage(getTrip().getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination To Trip", new Stage());
    }

    @FXML private void handleRemoveDestination(ActionEvent event) throws Exception{
        ViewLoader.showStage(getTrip().getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination From Trip", new Stage());
    }

    @FXML private void handleConnecting(ActionEvent event) throws Exception{
        try{
            getTrip().addConnectingFlights();
        } catch (InsufficientDestinationsException e) {
            ViewLoader.showStage(new ErrorModel(e, "Please add more destinations"), "/view/Error/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML private void handleViewTrip(ActionEvent event) throws Exception{
        ViewLoader.showStage(getTrip(), "/view/Trip/DisplayTripView.fxml", "Display Trip", new Stage());
    }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}
