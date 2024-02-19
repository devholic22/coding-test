package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_2798_블랙잭 {

    private static int N;
    private static int M;
    private static int result;
    private static int[] temp;
    private static int[] numbers;
    private static boolean[] used;

    /*
    Solved: 24.02.19 (월)
    블랙잭 - 브론즈2
    - ✅ 데이터의 크기를 통해 백트래킹이 적합함을 인지하였다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 3 <= N <= 100
        M = Integer.parseInt(tokenizer.nextToken()); // 10 <= M <= 100,000
        used = new boolean[N];
        temp = new int[N];
        numbers = new int[N];
        result = 0;

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        backtracking(0);

        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static void backtracking(int depth) {
        if (depth == 3) {
            calculate();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            temp[depth] = numbers[i];
            backtracking(depth + 1);
            used[i] = false;
        }
    }

    private static void calculate() {
        int sum = 0;
        for (int t : temp) {
            sum += t;
        }
        if (sum > M) {
            return;
        }
        result = Math.max(result, sum);
    }
}
