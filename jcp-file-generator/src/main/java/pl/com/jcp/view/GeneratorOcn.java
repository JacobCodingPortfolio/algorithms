package pl.com.jcp.view;

import java.util.Map;

public class GeneratorOcn extends GeneratorAbstract {

    public GeneratorOcn(GeneratorSelector generatorSelector) {
        super(generatorSelector);
    }

    @Override
    protected void executeGeneratorLogicOnClick(Map<Parameter, String> parameterMap) {
        System.out.println("Poszło");
    }

    @Override
    protected String getGeneratorInfo() {
        return "Generate one column numbers.";
    }

    @Override
    protected Parameter[] getDesirableParameters() {
        return new Parameter[]{Parameter.NUMBER_COUNT, Parameter.OUTPUT_FILE_NAME};
    }
}
