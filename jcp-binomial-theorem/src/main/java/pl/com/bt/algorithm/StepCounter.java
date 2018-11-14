package pl.com.bt.algorithm;

public abstract class StepCounter {

    protected long stepsInOne = 0;
    protected long stepsTotality = 0;

    public abstract Long calculate(long n, long k);
    protected abstract long executeAlgorithm(long n, long k);

    public long getStepsInOne() {
        return stepsInOne;
    }

    public long getStepsTotality() {
        return stepsTotality;
    }
}
