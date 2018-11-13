package pl.com.jcp;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractGenerator {

    private GeneratorSelector generatorSelector;
    private AnchorPane mainContent;
    private Label algorithmInfo;
    private Button executeAlgorithm;
    private FXMLLoader fxmlLoader;

    public AbstractGenerator(GeneratorSelector generatorSelector) {
        this.generatorSelector = generatorSelector;
        this.fxmlLoader = new FXMLLoader(getClass().getResource("/" + getFxmlName() + ".fxml"));
        try {
            this.mainContent = this.fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.executeAlgorithm = (Button) getNodeByIs("executeAlgorithm");
        this.executeAlgorithm.setOnMouseClicked(event -> {
            //TODO - initialize get parameters window
            //algorithmAction();
        });
        this.algorithmInfo = (Label) getNodeByIs("algorithmInfo");
        this.algorithmInfo.setText(getAlgorithmInfo());
    }

    protected abstract String getFxmlName();
    protected abstract String getAlgorithmInfo();
    protected abstract void algorithmAction(Map<Parameter, String> parameterStringMap);
    protected abstract Parameter[] getDesirableParameters();

    public AnchorPane getMainContent() {
        return mainContent;
    }

    public GeneratorSelector generatorSelector(){
        return generatorSelector;
    }

    protected Object getNodeByIs(String key){
        return fxmlLoader.getNamespace().get(key);
    }

}
