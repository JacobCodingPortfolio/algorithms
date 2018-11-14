package pl.com.bt.client;

import javafx.application.Platform;
import pl.com.bt.algorithm.BinomialTheoremBasic;
import pl.com.bt.algorithm.BinomialTheoremRecursively;
import pl.com.bt.algorithm.BinomialTheoremSimplyIterate;
import pl.com.bt.algorithm.StepCounter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlgorithmExecutor implements Runnable {

    private UserInterfaceController controller;
    private boolean execute = false;
    private boolean breakExecute = false;

    private static final String GAP = "---------------------------------------------------------";

    private BinomialTheoremBasic binomialTheoremBasic;
    private BinomialTheoremRecursively binomialTheoremRecursively;
    private BinomialTheoremSimplyIterate binomialTheoremSimplyIterate;

    public AlgorithmExecutor(UserInterfaceController controller) {
        this.controller = controller;
    }

    public void run() {
        while (true){
            if(execute){
                startAction();
                List<Long[]> numbers = null;
                try {
                    numbers = getNumbersFromFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    binomialTheoremBasic = new BinomialTheoremBasic();
                    loggerInfo("Basic algorithm execution.");
                    executeAlgorithm(binomialTheoremBasic, numbers);
                    loggerInfo("For basic algorithm executions: " + String.valueOf(binomialTheoremBasic.getStepsTotality()) + "\n");
                    binomialTheoremRecursively = new BinomialTheoremRecursively();
                    loggerInfo("Recursively algorithm execution.");
                    executeAlgorithm(binomialTheoremRecursively, numbers);
                    loggerInfo("For recursively algorithm executions: " + String.valueOf(binomialTheoremRecursively.getStepsTotality()) + "\n");
                    binomialTheoremSimplyIterate = new BinomialTheoremSimplyIterate();
                    loggerInfo("Simply iterate algorithm execution.");
                    executeAlgorithm(binomialTheoremSimplyIterate, numbers);
                    loggerInfo("For simply iterate algorithm executions: " + String.valueOf(binomialTheoremSimplyIterate.getStepsTotality()) + "\n");
                    loggerCount("Done.");

                } catch (StopExecute stopExecute){
                    loggerCount("Execution stopped.");
                } finally {
                    finishAction();
                }

            }
            pause();
        }
    }

    private void executeAlgorithm(StepCounter stepCounter, List<Long[]> numbers) throws StopExecute {
        Long start = System.currentTimeMillis();
        for (Long[] longs: numbers){
            stepCounter.calculate(longs[0], longs[1]);
            loggerCount("Total step: " + String.valueOf(stepCounter.getStepsTotality()) + " Steps for current activity: " + String.valueOf(stepCounter.getStepsInOne()));
            if(breakExecute){
                throw new StopExecute("Break execute");
            }
            pause();
        }
        Long end = System.currentTimeMillis();
        Long time = end - start;
        loggerInfo("Execute time in seconds: " + String.valueOf(TimeUnit.MILLISECONDS.toSeconds(time)));
    }

    private void pause(){
        try {
            Thread.sleep(0,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startAction() {
        Platform.runLater(() -> {
            controller.disableButtons();
            controller.getStartAlgorithm().setText("Stop");
        });
        loggerInfo(GAP);
        loggerInfo("Execute started: " + String.valueOf(new Date()));
        loggerInfo(GAP);
    }

    private void loggerInfo(String info){
        Platform.runLater(() -> {
            controller.infoToLogger(info, UserInterfaceController.KindOfLogger.INFO);
        });
    }

    private void loggerCount(String info){
        Platform.runLater(() -> {
            controller.infoToLogger(info, UserInterfaceController.KindOfLogger.COUNTER);
        });
    }

    private void finishAction(){
        Platform.runLater(() -> {
            controller.enableButton();
            controller.getStartAlgorithm().setText("Start");
        });
        execute = false;
        breakExecute = false;
        loggerInfo(GAP);
        loggerInfo("Execute finish: " + String.valueOf(new Date()));
        loggerInfo(GAP);
    }

    public void start(){
        if(execute){
            stop();
        } else {
            execute = true;
        }
    }

    public void stop(){
        breakExecute = true;
    }

    private List<Long[]> getNumbersFromFile() throws IOException {
        List<Long[]> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(controller.getFileIn()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        while ((line = reader.readLine()) != null){
            line = line.trim();
            if(!line.isEmpty()){
                String[] strings = line.split(" ");
                Long[] longs = new Long[]{Long.valueOf(strings[0]), Long.valueOf(strings[1])};
                list.add(longs);
            }
        }
        return list;
    }

}
