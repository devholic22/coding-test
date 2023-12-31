package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_15651 {

    private static int[] numbers;
    private static int N;
    private static int M;
    private static BufferedWriter writer;

    /*
    SOLVED: 23.12.31 (일)
    N과 M (3) - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        // 1 <= M <= N <= 7, 최악의 경우 7^7 (823,543)
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = i;
        }
        int[] selected = new int[M];
        backtracking(0, selected);
        writer.close();
        reader.close();
    }

    private static void backtracking(int depth, int[] selected) throws IOException {
        if (depth == M) {
            print(selected);
            return;
        }
        for (int i = 1; i <= N; i++) {
            selected[depth] = numbers[i];
            backtracking(depth + 1, selected);
            selected[depth] = 0;
        }
    }

    private static void print(int[] selected) throws IOException {
        StringBuilder builder = new StringBuilder("");
        for (int number : selected) {
            builder.append(number);
            builder.append(" ");
        }
        String answer = builder.toString();
        writer.write(answer.strip() + "\n");
    }
}
