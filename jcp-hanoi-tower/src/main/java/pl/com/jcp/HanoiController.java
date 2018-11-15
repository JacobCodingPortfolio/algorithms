package pl.com.jcp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;
import java.util.List;

public class HanoiController {

    private static final Integer LOG_TOTAL_LIMIT = 500;

    @FXML
    private TextField textFieldPackCount;
    @FXML
    private Button startMoving;
    @FXML
    private TextArea logger;

    private String logCount;
    private List<String> logTotal = new LinkedList<>();

    private Thread thread;
    private HanoiRunner runner;

    public void executeMovingPack(MouseEvent mouseEvent) {
        if(runner == null){
            runner = new HanoiRunner(this);
        }
        if(thread == null){
            thread = new Thread(runner);
            thread.start();
        }
        this.runner.switchOn();
    }

    public TextField getTextFieldPackCount() {
        return textFieldPackCount;
    }

    public void blockControls(){
        textFieldPackCount.setDisable(true);
        startMoving.setDisable(true);
    }

    public void unblockControls(){
        textFieldPackCount.setDisable(false);
        startMoving.setDisable(false);
    }

    public void logCount(String log){
        this.logCount = log;
        buildLogs();
    }

    public void logTotal(String log){
        if(this.logTotal.size() > LOG_TOTAL_LIMIT){
            this.logTotal.remove(0);
        }
        this.logTotal.add(log);
        buildLogs();
    }

    private void buildLogs(){
        StringBuilder stringBuilder = new StringBuilder();
        if(this.logCount != null && !this.logCount.trim().isEmpty()){
            stringBuilder.append("\n" + this.logCount + "\n");
            stringBuilder.append("\n");
        }
        for(int i = (this.logTotal.size()-1); i >= 0; i--){
            stringBuilder.append(this.logTotal.get(i));
        }
        logger.setText(stringBuilder.toString());
    }

}
