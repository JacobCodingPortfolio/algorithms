package pl.com.euclides;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private static final String FILE_IN = "C:/Users/Jakub/Desktop/Programowanie/InOutFiles/euclides_in.txt";
    private static final String FILE_OUT = "C:/Users/Jakub/Desktop/Programowanie/InOutFiles/euclides_out.txt";

    public static void main(String[] args) throws Exception {
        List<Integer[]> integers = new ArrayList<Integer[]>();
        File fileIn = new File(FILE_IN);
        File fileOut = new File(FILE_OUT);
        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        String line = "";
        while ((line = reader.readLine()) != null){
            line = line.trim();
            if(!line.equals("")){
                String[] lineTab = line.split(" ");
                integers.add(new Integer[]{Integer.parseInt(lineTab[0]), Integer.parseInt(lineTab[1])});
            }
        }
        reader.close();

        if(!fileOut.exists()){
            fileOut.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
        for(Integer[] integers1: integers){
            writer.write(String.valueOf(nwd(integers1[0], integers1[1])));
            writer.newLine();
        }
        writer.close();
    }

    public static int nwd(int a, int b){
        if(b == 0){
            return a;
        }
        return nwd(b, (a%b));
    }

}
