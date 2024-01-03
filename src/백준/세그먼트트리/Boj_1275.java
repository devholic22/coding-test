package 백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1275 {

    private static long[] numbers;
    private static int treeIndex;

    /*
    SOLVED: 24.01.03 (수)
    커피숍2 - 골드1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 100,000
        int Q = Integer.parseInt(tokenizer.nextToken()); // 1 <= Q <= 100,000

        treeIndex = 1;
        while (treeIndex < N) {
            treeIndex *= 2;
        }
        numbers = new long[treeIndex * 2];
        Arrays.fill(numbers, 0);
        treeIndex--;

        // 값의 범위를 놓쳐서 초반에 틀렸었다... 꼭 잊지 말자..!!
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            long number = Long.parseLong(tokenizer.nextToken());
            update(i, number);
        }

        for (int i = 0; i < Q; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt((tokenizer.nextToken()));
            int a = Integer.parseInt(tokenizer.nextToken());
            long b = Long.parseLong(tokenizer.nextToken());
            // 노트 사항을 몰랐었다..!
            if (x > y) {
                writer.write(calculate(y, x) + "\n");
            } else {
                writer.write(calculate(x, y) + "\n");
            }
            update(a, b);
        }

        writer.close();
        reader.close();
    }

    private static long calculate(int start, int end) {
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

    private static void update(int index, long value) {
        index += treeIndex;
        numbers[index] = value;
        index /= 2;
        while (index >= 1) {
            numbers[index] = numbers[index * 2] + numbers[index * 2 + 1];
            index /= 2;
        }
    }
}
