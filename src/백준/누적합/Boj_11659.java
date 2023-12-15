package 백준.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_11659 {

    /*
    SOLVED: 23.12.15 (금)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int[] numbers = new int[N + 1];
        int[] values = new int[N + 1];
        int sum = 0;
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
            values[i - 1] = sum; // 누적합 저장
            sum += numbers[i];
        }
        values[N] = sum; // 마지막 값 처리

        // start, end 입력받고 구간합 계산
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            writer.write(values[end] - values[start - 1] + "\n");
        }

        writer.close();
        reader.close();
    }
}
