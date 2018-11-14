package pl.com.bt;

public class AlgorithmExecutor implements Runnable {

    private UserInterfaceController controller;
    private boolean execute = false;

    public AlgorithmExecutor(UserInterfaceController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true){
            if(execute){



                finishAction();
            }
            System.out.println("Running.");
            pause();
        }
    }

    private void pause(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void finishAction(){
        execute = false;
    }

    public void start(){
        execute = true;
    }

}
