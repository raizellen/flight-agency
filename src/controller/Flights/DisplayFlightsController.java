package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.Flight;
import model.Flights;

public class DisplayFlightsController extends Controller<Flights> {
    @FXML private TextField countryTf;
    @FXML private TableView<Flight> flightsTv;
    @FXML private TableView<Flight> flightsFilteredTv;
    
    @FXML public void initialize(){
        stage.getIcons().add(new Image("/image/flights_icon.png"));
        if (countryTf != null){
            countryTf.textProperty().addListener((observable, oldValue, newValue) -> getFiltered());
        }
    }

    public final Flights getFlights(){ return model; }
    
    public final String getCountry(){ return countryTf.getText(); }

    public final ObservableList<model.Flight> getFiltered(){
        flightsFilteredTv.refresh();
        return getFlights().getFilteredFlights(getCountry());
    }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}
