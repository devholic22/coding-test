package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2776 {

    /*
    SOLVED: 24.01.27 (토)
    암기왕 - 실버 4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000,000
            int[] numbers = new int[N];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                numbers[j] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(numbers);
            int M = Integer.parseInt(reader.readLine()); // 1 <= M <= 1,000,000
            int[] questions = new int[M];
            int[] result = new int[M];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                questions[j] = Integer.parseInt(tokenizer.nextToken());
                result[j] = isFind(questions[j], numbers) ? 1 : 0;
            }
            for (int j = 0; j < M; j++) {
                writer.write(result[j] + "\n");
            }
        }
        writer.close();
        reader.close();
    }

    private static boolean isFind(final int number, final int[] numbers) {
        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] == number) {
                return true;
            }
            if (numbers[mid] > number) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}
