package pl.com.jcp.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

/**
 * Two column number generated
 */

public class TcnGenerator {

    private static final String fileExtension = ".txt";
    private static Random random = new Random();

    private File directoryToSaveFile;
    private Integer numberRange;
    private Long numberCount;
    private String outputFileName;

    public TcnGenerator(File directoryToSaveFile, Integer numberRange, Long numberCount, String outputFileName) {
        this.directoryToSaveFile = directoryToSaveFile;
        this.numberRange = numberRange;
        this.numberCount = numberCount;
        this.outputFileName = outputFileName;
    }

    public Boolean fileGenerateSuccess() throws Exception {
        if(directoryToSaveFile != null && directoryToSaveFile.isDirectory()){
            String filePath = directoryToSaveFile.getPath() + "/" + outputFileName + fileExtension;
            File fileTxt = new File(filePath);
            if(!fileTxt.exists()){
                fileTxt.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileTxt));
            for(long i=0; i< numberCount; i++){
                Integer firstNumber = getRandomNumber(this.numberRange);
                Integer secondNumber = getRandomNumber(this.numberRange);

                if(firstNumber < secondNumber){
                    Integer buffer = firstNumber;
                    firstNumber = secondNumber;
                    secondNumber = buffer;
                }

                writer.write(String.valueOf(firstNumber));
                writer.write(" ");
                writer.write(String.valueOf(secondNumber));
                writer.newLine();
            }
            writer.close();
            return true;
        } else {
            throw new Exception();
        }
    }

    private Integer getRandomNumber(Integer numberRange){
        Integer randomNumber = Math.abs(random.nextInt()) % numberRange;
        if(randomNumber.equals(0)){
            return getRandomNumber(numberRange);
        } else {
            return randomNumber;
        }
    }


}
