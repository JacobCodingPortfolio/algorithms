package pl.com.bt.algorithm;

public class BinomialTheoremSimplyIterate extends StepCounter {

    public Long calculate(long n, long k){
        stepsInOne = 0;
        return executeAlgorithm(n, k);
    }
    protected long executeAlgorithm(long n, long k){
        long wynik = 1;
        for(long i=1; i<=k; i++){
            stepsInOne++;
            stepsTotality++;
            wynik = wynik * (n-i+1) / i;
        }
        return wynik;
    }
}
