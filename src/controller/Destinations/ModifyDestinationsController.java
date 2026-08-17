package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Destination;
import model.Destinations;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyDestinationsController extends Controller<Destinations> {
    @FXML private Button addBtn;
    @FXML private Button removeBtn;
    @FXML private TextField nameTf;
    @FXML private TextField countryTf;

    @FXML public void initialize(){
        stage.getIcons().add(new Image("/image/destinations_icon.png"));
        if (addBtn != null){
            addBtn.disableProperty().bind(nameTf.textProperty().isEmpty().or(countryTf.textProperty().isEmpty()));
        }
        else if (removeBtn != null) {
            removeBtn.disableProperty().bind(nameTf.textProperty().isEmpty().or(countryTf.textProperty().isEmpty()));
        }
    }

    public final Destinations getDestinations(){ return model; }

    public final String getName(){ return nameTf.getText(); }
    public final String getCountry(){ return countryTf.getText(); }

    @FXML private void handleAdd(ActionEvent event) throws Exception{
        try {
            getDestinations().addDestination(new Destination(getName(), getCountry()));
            stage.close();
        } catch (DuplicateItemException e){
            ViewLoader.showStage(new ErrorModel(e, "Destination already exists"), "/view/Error/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML private void handleRemove(ActionEvent event) throws Exception{
        try {
            getDestinations().removeDestination(getDestinations().destination(getName(), getCountry()));
            stage.close();
        } catch (ItemNotFoundException e){
            ViewLoader.showStage(new ErrorModel(e, "Destination cannot be found"), "/view/Error/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}