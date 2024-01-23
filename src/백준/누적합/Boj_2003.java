package 백준.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_2003 {

    private static int[] sums;

    /*
    SOLVED: 24.01.23 (화)
    수들의 합 2 - 실버4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 10000
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] numbers = new int[N + 1];
        sums = new int[N + 1];
        tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
            sums[i] = numbers[i] + sums[i - 1];
        }

        int start = 1;
        int end = 1;
        int result = 0;
        while (start <= N && end <= N) {
            if (getSum(start, end) == M) {
                result++;
                start++;
                end = start;
            } else if (getSum(start, end) < M) {
                end++;
            } else {
                start++;
                end = result;
            }
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static int getSum(int start, int end) {
        return sums[end] - sums[start - 1];
    }
}
