package pl.com.bt.algorithm;

public class BinomialTheoremRecursively extends StepCounter{

    public Long calculate(long n, long k){
        stepsInOne = 0;
        return executeAlgorithm(n, k);
    }
    protected long executeAlgorithm(long n, long k){
        stepsInOne++;
        stepsTotality++;
        if(k == 0 || k == n){
            return 1;
        } else {
            return executeAlgorithm(n-1, k-1) + executeAlgorithm(n-1, k-1);
        }
    }
}
