package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agency;

public class ExploreFlightsController extends Controller<Agency> {
    @FXML private Label welcomeLbl;

    @FXML public void initialize(){
        welcomeLbl.setText("Hi " + getAgency().getLoggedInUser().getName() +", welcome to the Flights Section");
        stage.getIcons().add(new Image("/image/flights_icon.png"));
    }
    
    public final Agency getAgency(){ return model; }

    @FXML private void handleViewAll(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency().getFlights(), "/view/Flights/DisplayFlightsView.fxml", "Display Flights", new Stage());
    }

    @FXML private void handleViewCountry(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency().getFlights(), "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Flights Filtered", new Stage());
    }

    @FXML private void handleAddFlight(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency().getFlights(), "/view/Flights/AddFlightView.fxml", "Add Flight", new Stage());
    }

    @FXML private void handleRemoveFlight(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency().getFlights(), "/view/Flights/RemoveFlightView.fxml", "Remove Flight", new Stage());
    }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}
