package pl.com.jcp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static final String FXML_FILE_NAME = "/HanoiUI.fxml";
    private HanoiController hanoiController;
    private Stage stage;
    private static App app;

    @Override
    public void start(Stage primaryStage) throws Exception {
        app = this;
        this.stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE_NAME));
        Parent parent = fxmlLoader.load();
        this.hanoiController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.sizeToScene();
        this.stage.show();
    }

    public static synchronized App getApp() {
        return app;
    }

    public HanoiController getHanoiController() {
        return hanoiController;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
        Platform.exit();
    }
}
