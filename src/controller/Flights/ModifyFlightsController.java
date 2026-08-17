package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Flight;
import model.Flights;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyFlightsController extends Controller<Flights> {
    @FXML private Button addBtn;
    @FXML private Button removeBtn;
    @FXML private TextField costTf;
    @FXML private TextField landingTf;
    @FXML private TextField takeoffTf;
    @FXML private TextField airlineTf;
    @FXML private TextField flightNoTf;

    @FXML public void initialize(){
        stage.getIcons().add(new Image("/image/flights_icon.png"));
        if (addBtn != null){
            addBtn.disableProperty().bind(airlineTf.textProperty().isEmpty()
                .or(flightNoTf.textProperty().isEmpty())
                .or(takeoffTf.textProperty().isEmpty())
                .or(landingTf.textProperty().isEmpty())
                .or(costTf.textProperty().isEmpty()));
        }
        if (removeBtn != null) {
            removeBtn.disableProperty().bind(takeoffTf.textProperty().isEmpty().or(landingTf.textProperty().isEmpty()));
        }
    }
    
    public final Flights getFlights(){ return model; }

    public final String getAirline(){ return airlineTf.getText(); }
    public final int getFlightNo(){ return Integer.parseInt(flightNoTf.getText()); }
    public final String getTakeoff(){ return takeoffTf.getText(); }
    public final String getLanding(){ return landingTf.getText(); }
    public final double getCost(){ return Double.parseDouble(costTf.getText()); }

    @FXML private void handleAdd(ActionEvent event) throws Exception{
        try{
            getFlights().addFlight(new Flight(getAirline(), getFlightNo(), getTakeoff(), getLanding(), getCost()));
            stage.close();
        } catch (DuplicateItemException e) {
            ViewLoader.showStage(new ErrorModel(e, "Flight already exists"), "/view/Error/ErrorView.fxml", "Error", new Stage());
        } catch (NumberFormatException e) {
            ViewLoader.showStage(new ErrorModel(e, "Invalid input"), "/view/Error/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML private void handleRemove(ActionEvent event) throws Exception{
        try {
            getFlights().removeFlight(getFlights().getFlight(getTakeoff(), getLanding()));
            stage.close();
        } catch (ItemNotFoundException e) {
            ViewLoader.showStage(new ErrorModel(e, "Flight does not exist"), "/view/Error/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}