package pl.com.jcp;

import javafx.application.Platform;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HanoiRunner implements Runnable {

    private static final String GAP = "-------------------------------------------------\n";
    private HanoiController hanoiController;
    private boolean execute = false;
    private int totalSteps = 0;
    private Integer numberOfPacks = null;
    private BufferedWriter writer;

    public HanoiRunner(HanoiController hanoiController) {
        this.hanoiController = hanoiController;
    }

    @Override
    public void run() {

        while (true){
            if(execute) {
                numberOfPacks = null;
                startProcedure();
                this.hanoiExecute(numberOfPacks);
                finishProcedure();
            }
            pause();
        }
    }

    private void startProcedure() {

        Platform.runLater(() -> {
            this.hanoiController.logTotal(GAP);
            this.hanoiController.logTotal("Started on: " + String.valueOf(new Date()) + "\n");
            this.hanoiController.logTotal(GAP);
        });

        BufferedReader reader = null;
        if(hanoiController.getFileIn() != null){
            try {
                reader = new BufferedReader(new FileReader(hanoiController.getFileIn())); //Get the number from file
                String s = reader.readLine();
                numberOfPacks = Integer.valueOf(s);
                File folderIn = hanoiController.getFileIn().getParentFile();
                if(folderIn.isDirectory()){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                    Date date = new Date();
                    String fileOutName = "out_" + simpleDateFormat.format(date);
                    File outFile = new File(folderIn.getPath() + "/" + fileOutName + ".txt");
                    writer = new BufferedWriter(new FileWriter(outFile));
                    Platform.runLater(() -> this.hanoiController.logTotal("Generated file out name: " + outFile.getName() + "\n"));
                }
            } catch (Exception e) {
                numberOfPacks = null;
            } finally {
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        System.out.println("Problem with closing reader.");
                    }
                }
            }
        }

        if(numberOfPacks == null){
            try {
                String s = hanoiController.getTextFieldPackCount().getText();
                numberOfPacks = Integer.valueOf(s);
            } catch (Exception e) {
                numberOfPacks = 1;
            }
            Platform.runLater(() -> this.hanoiController.logTotal("Number of pack get from text field.\n"));
        } else {
            Platform.runLater(() -> this.hanoiController.logTotal("Number of pack get from file.\n"));
        }

        hanoiController.blockControls();
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
        if(writer != null){
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("Problem with closing writer.");
            }
        }
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
            move(start, finish);
        } else {
            this.hanoiAlgorithm(packs-1, start, finish, buffer);
            this.hanoiAlgorithm(1, start, buffer, finish);
            this.hanoiAlgorithm(packs-1, buffer, start, finish);
        }
    }

    private void move(String start, String finish) {
        String move = "Move from: " + start + " to: " + finish;
        if(writer != null){
            try{
                if (totalSteps > 1) {
                    writer.newLine();
                }
                writer.write(move);
            } catch (Exception e){
                System.out.println("Problem with write to file.+");
            }

        }
        Platform.runLater(() -> this.hanoiController.logTotal(move + "\n"));
    }

    private void addStep(){
        this.totalSteps++;
        Platform.runLater(() -> this.hanoiController.logCount("Count of executed operations: " + String.valueOf(totalSteps)));
    }

}
