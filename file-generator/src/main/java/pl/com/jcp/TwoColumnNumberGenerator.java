package pl.com.jcp;

import java.util.Map;

public class TwoColumnNumberGenerator extends AbstractGenerator {

    public TwoColumnNumberGenerator(GeneratorSelector generatorSelector) {
        super(generatorSelector);
    }

    @Override
    protected String getFxmlName() {
        return "TwoColumnNumberGen";
    }

    @Override
    protected String getAlgorithmInfo() {
        return "Generate file with two column numbers";
    }

    @Override
    protected void algorithmAction(Map<Parameter, String> parameterStringMap) {

    }

    @Override
    protected Parameter[] getDesirableParameters() {
        return new Parameter[]{Parameter.RANGE, Parameter.NUMBER_COUNT};
    }
}
