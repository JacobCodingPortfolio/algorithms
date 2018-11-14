package pl.com.jcp.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    private Scene primaryScene;
    private GeneratorSelector generatorSelector;
    private GeneratorTcn generatorTcn;
    private GeneratorOcn generatorOcn;
    private static App app;

    @Override
    public void start(Stage primaryStage) throws Exception {
        app = this;
        this.primaryStage = primaryStage;
        this.generatorSelector = new GeneratorSelector();
        this.primaryScene = new Scene(generatorSelector);
        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.sizeToScene();
        this.primaryStage.setResizable(false);
        this.initializeGenerators();
        this.primaryStage.show();
    }

    private void initializeGenerators() {
        this.generatorTcn = new GeneratorTcn(this.generatorSelector);
        this.generatorOcn = new GeneratorOcn(this.generatorSelector);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getPrimaryScene() {
        return primaryScene;
    }

    public void setPrimaryScene(Scene primaryScene) {
        this.primaryScene = primaryScene;
    }

    public GeneratorSelector getGeneratorSelector() {
        return generatorSelector;
    }

    public void setGeneratorSelector(GeneratorSelector generatorSelector) {
        this.generatorSelector = generatorSelector;
    }

    public static synchronized App getApp() {
        return app;
    }
}
