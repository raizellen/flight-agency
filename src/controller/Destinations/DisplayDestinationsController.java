package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.Destination;
import model.Destinations;

public class DisplayDestinationsController extends Controller<Destinations> {
    @FXML private TextField countryTf;
    @FXML private TableView<Destination> destinationsTv;
    @FXML private TableView<Destination> destinationsFilteredTv;

    @FXML public void initialize(){
        stage.getIcons().add(new Image("/image/destinations_icon.png"));
        if (countryTf != null){
            countryTf.textProperty().addListener((observable, oldValue, newValue) -> getFiltered());
        }
    }

    public final Destinations getDestinations(){ return model; }

    public final String getCountry(){ return countryTf.getText(); }

    public final ObservableList<model.Destination> getFiltered(){
        destinationsFilteredTv.refresh();
        return getDestinations().getFilteredDestinations(getCountry());
    }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}