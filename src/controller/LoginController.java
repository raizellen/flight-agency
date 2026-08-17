package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Agency;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidCredentialsException;

public class LoginController extends Controller<Agency>{
    @FXML private Button loginBtn;
    @FXML private TextField usernameTf;
    @FXML private PasswordField passwordPf;
    
    @FXML private void initialize(){
        loginBtn.disableProperty().bind(usernameTf.textProperty().isEmpty().or(passwordPf.textProperty().isEmpty()));
    }
    
    public final Agency getAgency(){ return model; }

    public final String getLogin(){ return usernameTf.getText(); }
    public final String getPassword(){ return passwordPf.getText(); }

    @FXML private void handleLogin(ActionEvent event) throws Exception{
        try{
            if (getAgency().getAdministrators().hasAdministrator(getLogin(), getPassword())){
                getAgency().setLoggedInUser(getAgency().getAdministrators().getAdministrator(getLogin(), getPassword()));
                ViewLoader.showStage(getAgency(), "/view/AgencyView.fxml", "Agency Menu", new Stage());
                stage.close();
            }
        }
        catch (InvalidCredentialsException e){
            ViewLoader.showStage(new ErrorModel(e, "Invalid Credentials"), "/view/Error/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML private void handleExit(ActionEvent event) throws Exception{
        stage.close();
    }
}