package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_15652 {

    private static int[] numbers;
    private static int[] answers;
    private static BufferedWriter writer;
    private static int N;
    private static int M;

    /*
    SOLVED: 24.01.01 (월)
    N과 M (4) - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        numbers = new int[N];
        answers = new int[M];
        // 1 ~ N의 값 저장
        for (int i = 0; i < N; i++) {
            numbers[i] = i + 1;
        }
        backtracking(0);
        writer.close();
        reader.close();
    }

    private static void backtracking(int depth) throws IOException {
        if (depth == M) {
            print();
            return;
        }
        for (int i = 0; i < N; i++) {
            answers[depth] = numbers[i];
            // 같거나 오름차순일 경우에만 진행되도록 설정
            if (depth > 0 && answers[depth - 1] > answers[depth]) {
                continue;
            }
            backtracking(depth + 1);
            answers[depth] = 0;
        }
    }

    private static void print() throws IOException {
        for (int answer : answers) {
            writer.write(answer + " ");
        }
        writer.write("\n");
    }
}
