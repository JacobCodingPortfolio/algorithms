package pl.com.bt.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserInterface extends Application {

    private Stage stage;
    private static final String FXML_FILE_NAME_PATH = "/UserInterface.fxml";
    private static UserInterface userInterface;
    private UserInterfaceController interfaceController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        userInterface = this;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE_NAME_PATH));
        Parent parent = fxmlLoader.load();
        this.interfaceController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static synchronized UserInterface getUserInterface() {
        return userInterface;
    }

    public UserInterfaceController getInterfaceController() {
        return interfaceController;
    }

    public Stage getStage() {
        return stage;
    }
}
