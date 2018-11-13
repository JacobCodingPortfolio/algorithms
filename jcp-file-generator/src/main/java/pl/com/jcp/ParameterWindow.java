package pl.com.jcp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Map;

public class ParameterWindow extends AnchorPane{

    private static final String POSITION_FXML_FILE_NAME_WINDOW = "/ParameterWindow.fxml";
    private Stage parameterStage;
    private FXMLLoader loader;
    private VBox mainContentWindow;
    private Button confirm;
    private Map<Parameter, String> parameterMap;

    private ParameterWindow(){
        this.loader = new FXMLLoader(getClass().getResource(POSITION_FXML_FILE_NAME_WINDOW));
        try {
            this.mainContentWindow = this.loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.confirm = (Button) this.getNodeByKey("confirm");
        this.confirm.setOnMouseClicked(event -> {

            this.parameterStage.close();
        });
    }

    public Map<Parameter, String> getParameters(Window window, Parameter[] paramsExptected) throws Exception {
        ParameterWindow parameterWindow = new ParameterWindow();
        for(Parameter p: paramsExptected){
            final  ParameterPosition parameterPosition = new ParameterPosition(p);
            parameterWindow.getChildren().add(parameterPosition);
        }
        this.parameterStage = new Stage();
        this.parameterStage.setScene(new Scene(parameterWindow));
        this.parameterStage.setTitle("Insert additional parameters");
        this.parameterStage.initOwner(window);
        this.parameterStage.showAndWait();
        return parameterMap;
    }

    private Object getNodeByKey(String key){
        return loader.getNamespace().get(key);
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public VBox getMainContentWindow() {
        return mainContentWindow;
    }

    public Button getConfirm() {
        return confirm;
    }

    private class ParameterPosition extends AnchorPane{

        private static final String POSITION_FXML_FILE_NAME = "/ParameterPosition.fxml";
        private FXMLLoader fxmlLoader;
        private AnchorPane positionContainer;
        private Label parameterName;
        private TextField parameterValue;
        private Parameter parameter;

        public ParameterPosition(Parameter parameter) throws Exception {
            if(parameter == null){
                throw new Exception();
            }
            this.parameter = parameter;
            this.fxmlLoader = new FXMLLoader(getClass().getResource(POSITION_FXML_FILE_NAME));
            try {
                this.positionContainer = this.fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.getChildren().add(this.positionContainer);
            parameterName = (Label) getNodeByKey("parameterName");
            parameterName.setText(parameter.name().toUpperCase());
            parameterValue = (TextField) getNodeByKey("parameterValue");
        }

        private Object getNodeByKey(String key){
            return fxmlLoader.getNamespace().get(key);
        }

        public Label getParameterName() {
            return parameterName;
        }

        public void setParameterName(Label parameterName) {
            this.parameterName = parameterName;
        }

        public TextField getParameterValue() {
            return parameterValue;
        }

        public void setParameterValue(TextField parameterValue) {
            this.parameterValue = parameterValue;
        }

        public Parameter getParameter() {
            return parameter;
        }

        public void setParameter(Parameter parameter) {
            this.parameter = parameter;
        }

        @Override
        public boolean equals(Object obj) {
            ParameterPosition position = (ParameterPosition) obj;
            return this.parameter.equals(position.parameter);
        }
    }
}
