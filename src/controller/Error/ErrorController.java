package controller.Error;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import model.Exceptions.ErrorModel;

public class ErrorController extends Controller<ErrorModel>{
    @FXML private Label errorLbl;
    @FXML private Label messageLbl;

    @FXML public void initialize(){
        stage.getIcons().add(new Image("/image/error_icon.png"));
        errorLbl.setText(getError().getException().getClass().getSimpleName().toString());
        messageLbl.setText(getError().getMessage());
    }
    
    public final ErrorModel getError(){ return model; }

    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}
