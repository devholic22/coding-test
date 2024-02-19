package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_10819_차이를_최대로 {

    private static int N;
    private static int[] arr;
    private static int[] numbers;
    private static boolean[] used;
    private static int result;

    /*
    Solved: 24.02.18 (일)
    차이를 최대로 - 실버2
    - ✅ 데이터의 크기를 통해 백트래킹이 적합함을 인지하였다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine()); // 3 <= N <= 8
        result = 0;
        arr = new int[N];
        numbers = new int[N];
        used = new boolean[N];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        backtracking(0);

        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static void backtracking(final int depth) {
        if (depth == N) {
            calculate();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            arr[depth] = numbers[i];
            backtracking(depth + 1);
            used[i] = false;
        }
    }

    private static void calculate() {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(arr[i] - arr[i + 1]);
        }
        result = Math.max(result, sum);
    }
}
