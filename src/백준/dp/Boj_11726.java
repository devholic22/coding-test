package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_11726 {

    /*
    SOLVED: 24.01.09 (화)
    2 x n 타일링 - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000
        int[] numbers = new int[1001];
        numbers[1] = 1;
        numbers[2] = 2;
        for (int i = 3; i <= N; i++) {
            numbers[i] = (numbers[i - 1] + numbers[i - 2]) % 10007;
        }

        writer.write(numbers[N] + "");
        writer.close();
        reader.close();
    }
}
