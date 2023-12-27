package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_15650 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static boolean[] used;
    private static int[] numbers;
    private static int N;
    private static int M;

    /*
    SOLVED: 23.12.27 (수)
    N과 M (2) - 실버3
     */
    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        // 1 <= M <= N <= 8 -> O(N^M)
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        used = new boolean[N];
        numbers = new int[M];

        backtracking(0, M);

        writer.close();
        reader.close();
    }

    private static void backtracking(int depth, int goal) throws IOException {
        if (depth == goal) {
            print();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (used[i]) { // 중복 방지
                continue;
            }
            // 사용 처리
            used[i] = true;
            numbers[depth] = i + 1;

            backtracking(depth + 1, goal);

            // 사용 해제
            numbers[depth] = 0;
            used[i] = false;
        }
    }

    private static void print() throws IOException {
        // 만약 오름차순이 아니게 되었다면 그냥 끝낸다
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                return;
            }
        }
        for (int number : numbers) {
            writer.write(number + " ");
        }
        writer.write("\n");
    }
}
