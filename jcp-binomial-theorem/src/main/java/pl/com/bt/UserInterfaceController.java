package pl.com.bt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class UserInterfaceController {

    @FXML
    private Button buttonFileIn;
    @FXML
    private Button buttonDirOut;
    @FXML
    private Button startAlgorithm;
    @FXML
    private Label labelFileInPath;
    @FXML
    private Label labelDirOutPath;
    @FXML
    private TextArea textextAreaLogstArea;

    private Thread thread;
    private AlgorithmExecutor algorithmExecutor;

    public void fileInClick(MouseEvent mouseEvent) {

    }


    public void dirOutClick(MouseEvent mouseEvent) {

    }

    public void startAlgorithm(MouseEvent mouseEvent) {
        if(algorithmExecutor == null){
            algorithmExecutor = new AlgorithmExecutor(this);
        }
        if(thread == null){
            thread = new Thread(algorithmExecutor);
            thread.start();
        }
        algorithmExecutor.start();
    }

    public void disableButtons(){
        buttonFileIn.setDisable(true);
        buttonDirOut.setDisable(true);
    }

    public void enableButton(){
        buttonFileIn.setDisable(false);
        buttonDirOut.setDisable(false);
    }

    public void infoToLogger(String info){
        textextAreaLogstArea.setText(info);
    }


}
