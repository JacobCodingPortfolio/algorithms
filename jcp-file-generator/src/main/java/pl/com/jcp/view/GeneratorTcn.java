package pl.com.jcp.view;

import pl.com.jcp.generator.TcnGenerator;

import java.util.Map;

/**
 * Two columns with numbers generator.
 */

public class GeneratorTcn extends GeneratorAbstract {

    public GeneratorTcn(GeneratorSelector generatorSelector) {
        super(generatorSelector);
    }

    @Override
    protected void executeGeneratorLogicOnClick(Map<Parameter, String> parameterMap) {

        Integer range = null;
        Long numberCount = null;
        String fileName = null;

        if(parameterMap != null){
            if(parameterMap.containsKey(Parameter.NUMBER_COUNT)){
                numberCount = Long.valueOf(parameterMap.get(Parameter.NUMBER_COUNT));
            } else {
                System.out.println("Parameter not exists."); //TODO - error handler
            }
            if(parameterMap.containsKey(Parameter.RANGE)){
                range = Integer.valueOf(parameterMap.get(Parameter.RANGE));
            } else {
                System.out.println("Parameter not exists."); //TODO - error handler
            }
            if(parameterMap.containsKey(Parameter.OUTPUT_FILE_NAME)){
                fileName = parameterMap.get(Parameter.OUTPUT_FILE_NAME);
            } else {
                System.out.println("Parameter not exists."); //TODO - error handler
            }

            TcnGenerator tcnGenerator = new TcnGenerator(getGeneratorSelector().getDirectoryToSaveFiles(), range, numberCount, fileName);
            try {
                tcnGenerator.fileGenerateSuccess();
                System.out.println("File generated successful.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("File generate failed.");
            }

        } else {
            System.out.println("Map not contain any parameters."); //TODO - error handler
        }
    }

    @Override
    protected String getGeneratorInfo() {
        return "Generate file with two column numbers";
    }

    @Override
    protected Parameter[] getDesirableParameters() {
        return new Parameter[]{Parameter.RANGE, Parameter.NUMBER_COUNT, Parameter.OUTPUT_FILE_NAME};
    }
}
