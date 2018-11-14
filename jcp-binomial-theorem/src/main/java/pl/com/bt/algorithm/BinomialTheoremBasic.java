package pl.com.bt.algorithm;

public class BinomialTheoremBasic extends StepCounter {

    public Long calculate(long n, long k){
        stepsInOne = 0;
        return executeAlgorithm(n, k);
    }
    protected long executeAlgorithm(long n, long k){
        stepsInOne++;
        stepsTotality++;
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private long factorial(long f){
        long result = 1;
        for(long i=1; i<=f; i++){
            result *= i;
        }
        return result;
    }
}
