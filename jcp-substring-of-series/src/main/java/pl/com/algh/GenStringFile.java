package pl.com.algh;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenStringFile {

    private static final String FILE_SAVE_PATH = "C:/Users/jnartowicz/Desktop/Projekty Jakub/algorithms/jcp-substring-of-series/sub_strings.txt";
    private static final int COUNT_OF_ROWS = 1000;
    private static final int COUNT_OF_STRING_IN_ROW = 2;
    private static final int FIRST_ASCII = 65;
    private static final int LAST_ASCII = 70;  //90
    private static final int EXTENT_OF_COUNT_LETTER_STRING_START = 10;
    private static final int EXTENT_OF_COUNT_LETTER_STRING_END = 20;

    public static void main(String[] args) {
        File file = new File(FILE_SAVE_PATH);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            generateString(writer);
        } catch (IOException e) {;
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String generateString(BufferedWriter writer){
        Random random = new Random();
        StringBuilder stringFileBuilder = new StringBuilder();
        for(int j = 0; j < COUNT_OF_ROWS; j++){
            for(int k = 0; k < COUNT_OF_STRING_IN_ROW; k++){
                int stringLen = EXTENT_OF_COUNT_LETTER_STRING_START + (Math.abs(random.nextInt()) % (EXTENT_OF_COUNT_LETTER_STRING_END - EXTENT_OF_COUNT_LETTER_STRING_START + 1));
                for(int i = 0; i < stringLen; i++){
                    int numberAscii = FIRST_ASCII + (Math.abs(random.nextInt()) % (LAST_ASCII - FIRST_ASCII + 1));
                    char ch = (char) numberAscii;
                    stringFileBuilder.append(ch);
                }
                stringFileBuilder.append(" ");
            }
            try {
                writer.write(stringFileBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringFileBuilder = new StringBuilder();
            if((j+1) < COUNT_OF_ROWS){
                try {
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringFileBuilder.toString();
    }



}
