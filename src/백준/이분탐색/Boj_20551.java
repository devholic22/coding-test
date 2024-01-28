package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_20551 {

    /*
    SOLVED: 24.01.27 (토)
    Sort 마스터 배지훈의 후계자 - 실버 4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 2 * 10^5
        long[] numbers = new long[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(numbers);
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 2 * 10^5
        long[] questions = new long[M];
        int[] answers = new int[M];
        for (int i = 0; i < M; i++) {
            questions[i] = Integer.parseInt(reader.readLine());
            answers[i] = getIndex(questions[i], numbers);
        }

        for (int answer : answers) {
            writer.write(answer + "\n");
        }
        writer.close();
        reader.close();
    }

    private static int getIndex(final long number, final long[] numbers) {
        int start = 0;
        int end = numbers.length - 1;
        int mid = 0;
        boolean find = false;
        int index = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (numbers[mid] == number) {
                find = true;
                index = mid;
                end = mid - 1;
            } else if (numbers[mid] > number) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (find) {
            return index;
        }
        return -1;
    }
}
