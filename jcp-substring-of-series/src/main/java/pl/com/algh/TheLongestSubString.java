package pl.com.algh;

import java.io.*;

public class TheLongestSubString {

    public static final String FILE_SAVE_PATH = "C:/Users/Jakub/Desktop/Programowanie/Projekty GitHub portfolio/algorithms/jcp-substring-of-series/sub_strings_save.txt";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = null;
        BufferedWriter writer = null;

            writer = new BufferedWriter(new FileWriter(new File(FILE_SAVE_PATH)));
            reader = new BufferedReader(new FileReader(new File(GenStringFile.FILE_LOAD_PATH)));
            String line;
            String s1;
            String s2;
            while((line = reader.readLine()) != null){
                String[] s = line.split(" ");
                s1 = s[0].trim();
                s2 = s[1].trim();
                writer.write("(" + s1 + ", " + s2 + ")  Substring: " + getTheLongestSubString(s1, s2));
                writer.newLine();
            }

            if(reader != null){
                reader.close();
            }
            if (writer != null){
                writer.close();
            }

    }

    private static String getTheLongestSubString(String s1, String s2){

        //Tworzenie tablicy dwu wymiarowej
        int m = s1.length() + 1;
        int n = s2.length() + 1;

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        
        int[][] s = new int[m][n];

        //Wypełnienie tablic zerami
        for(int i=0; i< m; i++) s[i][0] = 0;
        for(int j=0; j< n; j++) s[0][j] = 0;

        //Uzupełnienie tablicy
        for (int i=1; i<m; i++){
            for (int j=1; j<n; j++){
                if( chars1[i-1] == chars2[j-1] ){
                    s[i][j] = s[i-1][j-1] + 1;
                } else {
                    s[i][j] = getMax(s[i][j-1], s[i-1][j]);
                }
            }
        }

        String theLongestSubStringBuilder = "";
        //Rozpoczynamy od ostatniego znaku
        int i = m-1;
        int j = n-1;

        //Build the longest substring
        while(s[i][j] != 0){
            if( chars1[i-1] == chars2[j-1]){
                theLongestSubStringBuilder = chars1[i-1] + theLongestSubStringBuilder;
                --i;
                --j;
            } else {
                if( s[i-1][j] > s[i][j-1] ){
                    --i;
                } else {
                    --j;
                }
            }
        }

        return theLongestSubStringBuilder;
    }

    private static int getMax(int... numbers){
        if(numbers.length < 1){
            return 0;
        } else {
            int max = numbers[0];
            for(int i: numbers){
                if(i > max){
                    max = i;
                }
            }
            return max;
        }
    }

}
















