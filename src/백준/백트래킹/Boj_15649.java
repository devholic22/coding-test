package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_15649 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static int[] numbers;
    private static boolean[] used;
    private static int N;
    private static int M;

    /*
    SOLVED: 23.12.27 (수)
    N과 M (1) - 실버3
     */
    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        // 1 <= M <= N <= 8
        // 백트래킹의 시간 복잡도는 O(N^M) (N가지 x N-1가지 x N-2가지.. x 1가지)
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        numbers = new int[M];
        used = new boolean[N];
        backtracking(0, M);

        writer.close();
        reader.close();
    }

    private static void backtracking(int depth, int goal) throws IOException {
        if (depth == goal) {
            print(numbers);
            return;
        }
        for (int i = 0; i < N; i++) {
            // 중복 허용 금지
            if (used[i]) {
                continue;
            }
            // 사용 처리 및 숫자 할당
            used[i] = true;
            numbers[depth] = i + 1;
            backtracking(depth + 1, goal);
            // 원상 복구
            numbers[depth] = 0;
            used[i] = false;
        }
    }

    // 출력
    private static void print(int[] numbers) throws IOException {
        for (int number : numbers) {
            writer.write(number + " ");
        }
        writer.write("\n");
    }
}
