package pl.com.bt.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    private TextArea textAreaLogs;

    private Thread thread;
    private AlgorithmExecutor algorithmExecutor;
    private List<String> infoList = new ArrayList<>();
    private String counter = "";

    private File fileIn;
    private File dirOut;

    public void fileInClick(MouseEvent mouseEvent) {
        FileChooser chooser = new FileChooser();
        fileIn = chooser.showOpenDialog(UserInterface.getUserInterface().getStage().getScene().getWindow());
        if(fileIn != null){
            this.labelFileInPath.setText("File name with input: " + fileIn.getName());
        }
        checkPathSelected();
    }

    public void dirOutClick(MouseEvent mouseEvent) {
        DirectoryChooser chooser = new DirectoryChooser();
        dirOut = chooser.showDialog(UserInterface.getUserInterface().getStage().getScene().getWindow());
        if(dirOut != null){
            this.labelDirOutPath.setText("Directory name for output: " + fileIn.getName());
        }
        checkPathSelected();
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

    public void infoToLogger(String info, KindOfLogger kindOfLogger){
        switch (kindOfLogger){
            case INFO:
                infoList.add(info);
                break;
            case COUNTER:
                counter = info;
                break;
            default:
                break;
        }
        StringBuilder builder = new StringBuilder();
        if(counter != null && !counter.isEmpty()){
            builder.append(counter + "\n");
            builder.append("\n");
        }
        for(int i=(infoList.size()-1); i>=0; i--){
            builder.append(infoList.get(i) + "\n");
        }
        textAreaLogs.setText(builder.toString() + "\n");
    }

    public enum KindOfLogger {
        INFO,
        COUNTER
    }

    public void checkPathSelected(){
        if(fileIn != null && dirOut != null){
            startAlgorithm.setDisable(false);
        }
    }

    public Button getStartAlgorithm() {
        return startAlgorithm;
    }

    public File getFileIn() {
        return fileIn;
    }

    public File getDirOut() {
        return dirOut;
    }

}
