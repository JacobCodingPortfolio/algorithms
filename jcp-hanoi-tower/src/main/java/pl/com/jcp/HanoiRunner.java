package pl.com.jcp;

import javafx.application.Platform;

import java.util.Date;

public class HanoiRunner implements Runnable {

    private static final String GAP = "-------------------------------------------------\n";
    private HanoiController hanoiController;
    private boolean execute = false;
    private int totalSteps = 0;

    public HanoiRunner(HanoiController hanoiController) {
        this.hanoiController = hanoiController;
    }

    @Override
    public void run() {
        Integer numberOfPacks;
        while (true){
            if(execute) {
                startProcedure();
                try {
                    String s = hanoiController.getTextFieldPackCount().getText();
                    numberOfPacks = Integer.valueOf(s);
                } catch (Exception e) {
                    numberOfPacks = 1;
                }
                this.hanoiExecute(numberOfPacks);
                finishProcedure();
            }
            pause();
        }
    }

    private void startProcedure() {
        hanoiController.blockControls();
        Platform.runLater(() -> {
            this.hanoiController.logTotal(GAP);
            this.hanoiController.logTotal("Started on: " + String.valueOf(new Date()) + "\n");
            this.hanoiController.logTotal(GAP);
        });
    }

    private void finishProcedure() {
        hanoiController.unblockControls();
        execute = false;
        Platform.runLater(() -> {
            this.hanoiController.logTotal("Executed operations: " + String.valueOf(totalSteps) + "\n");
            this.hanoiController.logTotal(GAP);
            this.hanoiController.logTotal("Finished on: " + String.valueOf(new Date()) + "\n");
            this.hanoiController.logTotal(GAP);
            this.hanoiController.logCount("");
        });
    }

    private void pause(){
        try {
            Thread.sleep(0,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchOn(){
        execute = true;
    }

    public void hanoiExecute(Integer packsNumber){
        totalSteps = 0;
        Platform.runLater(() -> this.hanoiController.logTotal("Start for: " + String.valueOf(packsNumber) + " packs.\n"));
        this.hanoiAlgorithm(packsNumber, "A", "B" ,"C");
    }

    /**
     *
     * @param packs - count of packs
     * @param start - name of starting stick
     * @param buffer - name of buffer stick
     * @param finish - name of finish stick
     */
    public void hanoiAlgorithm(int packs, String start, String buffer, String finish){
        if(packs == 1){
            this.addStep();
            pause();
            Platform.runLater(() -> this.hanoiController.logTotal("Move from: " + start + " to: " + finish + "\n"));
        } else {
            this.hanoiAlgorithm(packs-1, start, finish, buffer);
            this.hanoiAlgorithm(1, start, buffer, finish);
            this.hanoiAlgorithm(packs-1, buffer, start, finish);
        }
    }

    private void addStep(){
        this.totalSteps++;
        Platform.runLater(() -> this.hanoiController.logCount("Count of executed operations: " + String.valueOf(totalSteps)));
    }

}
