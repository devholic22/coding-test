package 백준.구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1065 {

    /*
    SOLVED: 24.02.10 (토)
    한수 - 실버4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1000
        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (isHan(i)) {
                result++;
            }
        }
        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static boolean isHan(final int number) {
        if (number < 100) {
            return true;
        }
        String[] numberTokens = String.valueOf(number).split("");
        int distance = Integer.parseInt(numberTokens[0]) - Integer.parseInt(numberTokens[1]);
        for (int i = 1; i < numberTokens.length - 1; i++) {
            if (Integer.parseInt(numberTokens[i]) - Integer.parseInt(numberTokens[i + 1]) != distance) {
                return false;
            }
        }
        return true;
    }
}
