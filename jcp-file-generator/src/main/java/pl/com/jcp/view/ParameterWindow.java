package pl.com.jcp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterWindow extends AnchorPane{

    private static final String POSITION_FXML_FILE_NAME_WINDOW = "/ParameterWindow.fxml";
    private Stage parameterStage;
    private FXMLLoader loader;
    private VBox mainContentWindow;
    private Button confirm;
    private Map<Parameter, String> parameterMap;

    private ParameterWindow(Window window, Parameter[] paramsExpected) throws Exception {
        this.loader = new FXMLLoader(getClass().getResource(POSITION_FXML_FILE_NAME_WINDOW));
        try {
            this.mainContentWindow = this.getLoader().load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.parameterStage = new Stage();
        this.parameterStage.setTitle("Insert additional parameters");
        this.parameterStage.initOwner(window);
        this.parameterStage.setResizable(false);
        this.parameterStage.sizeToScene();

        for(Parameter p: paramsExpected){
            final  ParameterPosition parameterPosition = new ParameterPosition(p);
            this.getMainContentWindow().getChildren().add(parameterPosition);
        }

        this.confirm = (Button) this.getNodeByKey("confirm");
        this.getConfirm().setOnAction(event -> {
            for(ParameterPosition position: this.getParametersPositions()){
                if(parameterMap == null){
                    parameterMap = new HashMap<>();
                }
                parameterMap.put(position.getParameter(), position.getParameterValue().getText());
            }
            this.parameterStage.close();
        });

        this.parameterStage.setOnCloseRequest(event -> {
            this.parameterMap = null;
        });

        this.getChildren().add(this.mainContentWindow);
        this.parameterStage.setScene(new Scene(this));
        this.parameterStage.showAndWait();
    }

    public static ParameterWindow getInstance(Window window, Parameter[] paramsExpected) throws Exception {
        return new ParameterWindow(window, paramsExpected);
    }

    public Map<Parameter, String> getParameters() {
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

    private List<ParameterPosition> getParametersPositions(){
        List<ParameterPosition> parameterPositions = null;
        for(Node node: this.getMainContentWindow().getChildren()){
            if(node instanceof ParameterPosition){
                if(parameterPositions == null){
                    parameterPositions = new ArrayList<>();
                }
                parameterPositions.add((ParameterPosition) node);
            }
        }
        return parameterPositions;
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
            parameterName.setText(formatEnumName(parameter.name()));
            parameterValue = (TextField) getNodeByKey("parameterValue");
        }

        private Object getNodeByKey(String key){
            return fxmlLoader.getNamespace().get(key);
        }

        public TextField getParameterValue() {
            return parameterValue;
        }

        public Parameter getParameter() {
            return parameter;
        }

        @Override
        public boolean equals(Object obj) {
            ParameterPosition position = (ParameterPosition) obj;
            return this.parameter.equals(position.parameter);
        }

        private String formatEnumName(String name){
            String formatName;
            formatName = name;
            formatName = formatName.toLowerCase();
            formatName = formatName.replace("_", " ");
            formatName = formatName.substring(0,1).toUpperCase() + formatName.substring(1).toLowerCase();
            return formatName;
        }
    }
}
