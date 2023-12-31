package 백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11505 {

    private static long[] numbers;
    private static int treeIndex;
    private static int divide;

    /*
    SOLVED: 23.12.31 (일)
    구간 곱 구하기 - 골드1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 1,000,000
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 10,000
        int K = Integer.parseInt(tokenizer.nextToken()); // 1 <= K <= 10,000

        int UPDATE = 1;
        int CALCULATE = 2;
        divide = 1_000_000_007;

        treeIndex = 1;
        while (treeIndex < N) { // N이 2의 제곱수일 경우 그 때 까지만 하면 되기에 treeIndex "<" N으로만 해도 충분
            treeIndex *= 2;
        }
        treeIndex--; // 인덱스 계산 위함

        numbers = new long[treeIndex * 2];

        Arrays.fill(numbers, 1); // 곱셈의 초깃값은 1이어야 함

        // 초깃값 할당 - O(NlogN)
        for (int i = 1; i <= N; i++) {
            update(i, Long.parseLong(reader.readLine())); // O(logN)
        }

        // O((M+K)logN)
        for (int i = 0; i < M + K; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int command = Integer.parseInt(tokenizer.nextToken());
            if (command == UPDATE) {
                int index = Integer.parseInt(tokenizer.nextToken());
                long value = Long.parseLong(tokenizer.nextToken());
                update(index, value);
            } else {
                int start = Integer.parseInt(tokenizer.nextToken());
                int end = Integer.parseInt(tokenizer.nextToken());
                writer.write((calculate(start, end) % divide) + "\n");
            }
        }
        writer.close();
        reader.close();
    }

    private static long calculate(int start, int end) {
        long result = 1;
        start += treeIndex;
        end += treeIndex;
        while (start <= end) {
            if (start % 2 != 0) {
                result = (result * numbers[start]) % divide; // 중요: 오버플로우 방지하기 위해 마지막에 divide로 나눈 나머지로 되도록
            }
            if (end % 2 == 0) {
                result = (result * numbers[end]) % divide; // 중요: 오버플로우 방지하기 위해 마지막에 divide로 나눈 나머지로 되도록
            }
            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return result;
    }

    private static void update(int index, long value) {
        index += treeIndex;
        numbers[index] = value;
        index /= 2;

        while (index >= 1) {
            numbers[index] = (numbers[index * 2] * numbers[index * 2 + 1]) % divide; // 중요: 오버플로우 방지하기 위해 마지막에 divide로 나눈 나머지로 되도록
            index /= 2;
        }
    }
}
