package pl.com.algh;

public class TheLongestSubString {

    public static void main(String[] args) {

    }

    private String getTheLongestSubString(String s1, String s2){
        //Tworzenie tablicy dwu wymiarowej
        int m = s1.length() + 1;
        int n = s2.length() + 1;
        
        int[][] s = new int[m][n];

        //Wypełnienie tablic zerami
        for(int i=0; i< m; i++) s[i][0] = 0;
        for(int i=0; i< n; i++) s[0][i] = 0;

        for (int i=1; i<m; i++){
            for (int j=1; j<n; j++){



            }
        }


        return null;
    }

}
