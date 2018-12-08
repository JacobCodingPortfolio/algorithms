package pl.jcp.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmGraph {

    public static final String FILE_IN_PATH = "C:/Users/Jakub/Desktop/PLIKI_GRAFY/in.txt";
    public static final String FILE_OUT_PATH = "C:/Users/Jakub/Desktop/PLIKI_GRAFY/out.txt";
    public static final String SPACE = " ";
    public static final String EMPTY = "";

    public static void main(String[] args) throws IOException {

        int n, m, k, t;
        double chf, eur;

        BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_IN_PATH)));
        String firstLine = reader.readLine();
        String[] firstLineElements = firstLine.split(SPACE);
        n = Integer.valueOf(firstLineElements[0]);
        m = Integer.valueOf(firstLineElements[1]);
        chf = Double.valueOf(firstLineElements[2]);
        eur = Double.valueOf(firstLineElements[3]);
        k = Integer.valueOf(firstLineElements[4]);
        t = Integer.valueOf(firstLineElements[5]);

        //Macierz sąsiedztwa
        double[][] tSR = new double[n][n];
        boolean[][] tMs = new boolean[n][n];

        String line;
        int iLine = 0;
        while((line = reader.readLine()) != null){
            //Wczytanie wartości do macierzy sr
            String[] lineContent = line.split(SPACE);
            for(int j=0; j<lineContent.length; j++){
                tSR[iLine][j] = Double.valueOf(lineContent[j]);
            }
            iLine++;
        }

        reader.close();
        macierzSasiedztwa(tSR, tMs, n, m, chf, eur);

        List<Integer>[] tLS = (List<Integer>[]) new List[n];
        for(int i=0; i<tLS.length; i++){
            tLS[i] = new ArrayList<Integer>();
        }
        tablicaListSasiedztwa(tMs, tLS, n);

        //Wypisanie list sąsiedztwa
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "\t");
            for (Integer a : tLS[i]) {
                System.out.print(a + SPACE);
            }
            System.out.println(EMPTY);
        }

        List<List<Integer>> tlsssk = new ArrayList<List<Integer>>();
        SSSK(tLS, n, tlsssk);

        for (List<Integer> a : tlsssk) {
            quickSort(a, 0, a.size()-1);
        }

        System.out.println("\nWypisanie tablic list silnie spojnych skladowych");
        //wypisanie tablic list silnie spojnych skladowych
        for (List<Integer> a : tlsssk) {
            for (Integer b : a) {
                System.out.print(b + SPACE);
            }
            System.out.println(EMPTY);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FILE_OUT_PATH)));
        
        for (List<Integer> a : tlsssk) {
            if (isSSSKcontain(k, a)) {
                for (Integer b : a) {
                    writer.write(String.valueOf(b));
                    writer.write(SPACE);
                }
                writer.newLine();
                break;
            }
        }

        for (List<Integer> a : tlsssk) {
            if (isSSSKcontain(t, a)) {
                for (Integer b : a) {
                    writer.write(String.valueOf(b));
                    writer.write(SPACE);
                }
                break;
            }
        }

        writer.close();

    }

    private static boolean isSSSKcontain(int v, List<Integer> lsssk) {
        for (Integer a : lsssk) {
            if (v == a) return true;
        }
        return false;
    }

    private static void quickSort(List<Integer> a, int l, int r) {
        int i = l, j = r;
        int x = a.get((l + r) / 2); //[(l + r) / 2];

        do {
            while (a.get(i) < x) i++;
            while (a.get(j) > x) j--;
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (l < j) quickSort(a, l, j);
        if (r > i) quickSort(a, i, r);
    }

    private static void swap(List<Integer> a, int i, int j) {
        int bufor = a.get(i);
        a.set(i, a.get(j));
        a.set(j, bufor);
    }

    private static void SSSK(List<Integer>[] tLS, int n, List<List<Integer>> tlsssk) {

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) visited[i] = false;

        int v = 1;
        List<Integer> stos = new ArrayList<Integer>();
        for (; v <= n ; v++) {
            if (!visited[v - 1]) {
                DFSstack(tLS, visited, v, stos);
            }
        }

        System.out.println("\nWypisanie stosu:");
        for(Integer s: stos){
            System.out.print(s + SPACE);
        }
        System.out.println();

        List<Integer>[] tT = (List<Integer>[]) new List[n];
        for(int i=0; i<tT.length; i++){
            tT[i] = new ArrayList<Integer>();
        }

        transponujGraf(tLS, tT, n);

        System.out.println("\nWypisanie grafu transponowanego:");
        for(List<Integer> integers: tT){
            for(Integer integer: integers){
                System.out.print(integer + SPACE);
            }
            System.out.println(EMPTY);
        }

        for (int i = 0; i < n; i++) visited[i] = false;


        for(int i=(stos.size()-1); i >= 0; i--){
            v = stos.get(i);
            if (!visited[v - 1]) {
                final List<Integer> tsssk = new ArrayList<Integer>();
                DFSwrite(tT, visited, v, tsssk);
                tlsssk.add(tsssk);
            }
        }


    }

    private static void DFSwrite(List<Integer>[] tT, boolean[] visited, int v, List<Integer> tsssk) {
        visited[v - 1] = true;
        tsssk.add(v);
        for (Integer a : tT[v - 1]) {
            if (!visited[a - 1]) {
                DFSwrite(tT, visited, a, tsssk);
            }
        }
    }

    private static void transponujGraf(List<Integer>[] tLS, List<Integer>[] tT, int n) {
        for (int i = 0; i < n; i++) {
            for (Integer a : tLS[i]) {
                tT[a - 1].add(i + 1);
            }
        }
    }

    private static void DFSstack(List<Integer>[] tLS, boolean[] visited, int v, List<Integer> stos) {
        visited[v - 1] = true;
        for (Integer a : tLS[v - 1]) {
            if (!visited[a - 1]) {
                DFSstack(tLS, visited, a, stos);
            }
        }
        stos.add(v);
    }

    private static void tablicaListSasiedztwa(boolean[][] tMs, List<Integer>[] tLS, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tMs[i][j]) {
                    tLS[i].add(j + 1);
                }
            }
        }
    }

    private static void macierzSasiedztwa(double[][] tSR, boolean[][] tMs, int n, int m, double chf, double eur) {
        double diff = chf - eur;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((m * (1 - tSR[i][j])) > diff)
                    tMs[i][j] = true;
                else
                    tMs[i][j] = false;
            }
        }
    }

}
