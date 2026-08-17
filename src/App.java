
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agency;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/image/login_icon.png"));
        ViewLoader.showStage(new Agency(), "/view/LoginView.fxml", "Login", stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}