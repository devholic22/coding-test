package 백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_2268 {

    private static long[] numbers;
    private static int treeIndex;

    /*
    SOLVED: 24.01.06 (토)
    수들의 합 7 - 골드1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int SUM = 0;
        treeIndex = 1;
        while (treeIndex < N) {
            treeIndex *= 2;
        }
        numbers = new long[treeIndex * 2];
        treeIndex--;

        for (int i = 1; i <= M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int command = Integer.parseInt(tokenizer.nextToken());
            if (command == SUM) {
                int start = Integer.parseInt(tokenizer.nextToken());
                int end = Integer.parseInt(tokenizer.nextToken());
                if (start > end) {
                    writer.write(sum(end, start) + "\n"); // 조건 잘 읽자!
                } else {
                    writer.write(sum(start, end) + "\n");
                }
            } else {
                int index = Integer.parseInt(tokenizer.nextToken());
                int value = Integer.parseInt(tokenizer.nextToken());
                update(index, value);
            }
        }
        writer.close();
        reader.close();
    }

    private static void update(int index, long value) {
        index += treeIndex;
        numbers[index] = value;
        index /= 2;
        while (index >= 1) { // index "<=" 1이 아니다!
            numbers[index] = numbers[index * 2] + numbers[index * 2 + 1];
            index /= 2;
        }
    }

    private static long sum(int start, int end) {
        start += treeIndex;
        end += treeIndex;
        long result = 0;
        while (start <= end) {
            if (start % 2 != 0) {
                result += numbers[start];
            }
            if (end % 2 == 0) {
                result += numbers[end];
            }
            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return result;
    }
}
