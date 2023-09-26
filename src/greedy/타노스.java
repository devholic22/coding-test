package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;

public class 타노스 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static String test;
    private static String[] testArr;
    private static boolean[] used;
    private static String[] temp;
    private static int ONE_COUNT;
    private static int ZERO_COUNT;
    public static void main(String[] args) throws IOException {
        init();
        test = br.readLine();
        used = new boolean[test.length()];
        temp = new String[used.length / 2];
        testArr = test.split("");
        test = String.join("", testArr);
        ONE_COUNT = getNumberCount(testArr, 1);
        ZERO_COUNT = getNumberCount(testArr, 0);

        int pos = 0;
        while (getNumberCount(testArr, 1) != ONE_COUNT / 2) {
            if (testArr[pos].equals("1")) {
                testArr[pos] = "X";
            }
            pos++;
        }

        pos = testArr.length - 1;
        while (getNumberCount(testArr, 0) != ZERO_COUNT / 2) {
            if (testArr[pos].equals("0")) {
                testArr[pos] = "X";
            }
            pos--;
        }

        for (String s : testArr) {
            if (!s.equals("X")) {
                bw.write(s);
            }
        }
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }

    private static int getNumberCount(String[] arr, int number) {
        int count = 0;
        for (String s : arr) {
            if (s.equals(String.valueOf(number))) {
                count++;
            }
        }
        return count;
    }
}
