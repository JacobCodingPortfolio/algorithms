package pl.com.jcp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Map;

public abstract class GeneratorAbstract extends AnchorPane{

    private GeneratorSelector generatorSelector;
    private AnchorPane mainContent;
    private Label algorithmInfo;
    private Button executeAlgorithm;
    private FXMLLoader fxmlLoader;

    private static final String FXML_ABSTRACT_GENERATOR = "/AbstractGenerator.fxml";

    public GeneratorAbstract(GeneratorSelector generatorSelector) {
        this.generatorSelector = generatorSelector;
        this.fxmlLoader = new FXMLLoader(getClass().getResource(FXML_ABSTRACT_GENERATOR));
        try {
            this.mainContent = this.fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.executeAlgorithm = (Button) getNodeByIs("executeAlgorithm");
        this.executeAlgorithm.setOnMouseClicked(event -> {
            ParameterWindow parameterWindow = null;
            try {
                parameterWindow = ParameterWindow.getInstance(App.getApp().getPrimaryScene().getWindow(), getDesirableParameters());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if(getDesirableParameters() != null && getDesirableParameters().length != 0){
                    Map<Parameter, String> parameterMap = parameterWindow.getParameters();
                    if(parameterMap != null){
                        executeGeneratorLogicOnClick(parameterMap);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.algorithmInfo = (Label) getNodeByIs("algorithmInfo");
        this.algorithmInfo.setText(getGeneratorInfo());
        this.getChildren().add(mainContent);
        this.generatorSelector.addAbstractGenerator(this);
    }

    protected abstract void executeGeneratorLogicOnClick(Map<Parameter, String> parameterMap);
    protected abstract String getGeneratorInfo();
    protected abstract Parameter[] getDesirableParameters();

    protected Object getNodeByIs(String key){
        return fxmlLoader.getNamespace().get(key);
    }

    public GeneratorSelector getGeneratorSelector() {
        return generatorSelector;
    }
}
