package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agency;

public class ExploreDestinationsController extends Controller<Agency> {
    @FXML private Label welcomeLbl;

    @FXML public void initialize(){
        welcomeLbl.setText("Hi " + getAgency().getLoggedInUser().getName() +", welcome to the Destinations Section");
        stage.getIcons().add(new Image("/image/destinations_icon.png"));
    }
    
    public final Agency getAgency(){ return model; }

    @FXML private void handleViewAll(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency().getDestinations(), "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", new Stage());
    }

    @FXML private void handleViewCountry(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency().getDestinations(), "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destinations Filtered", new Stage());
    }

    @FXML private void handleAddDestination(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency().getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination", new Stage());
    }

    @FXML private void handleRemoveDestination(ActionEvent event) throws Exception{
        ViewLoader.showStage(getAgency().getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", new Stage());
    }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}