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
    private Label labelFileInPath;
    @FXML
    private Label labelDirOutPath;
    @FXML
    private TextArea textextAreaLogstArea;


    public void fileInClick(MouseEvent mouseEvent) {
    }


    public void dirOutClick(MouseEvent mouseEvent) {
    }

    public void disableButtons(){
        buttonFileIn.setDisable(true);
        buttonDirOut.setDisable(true);
    }

}
