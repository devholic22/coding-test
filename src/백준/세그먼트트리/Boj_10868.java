package 백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_10868 {

    private static int[] numbers;
    private static int treeIndex;

    /*
    SOLVED: 24.01.03 (수)
    최솟값 - 골드1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 100,000
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 100,000
        // 이전 세그먼트 트리 문제들과 같음
        treeIndex = 1;
        while (treeIndex < N) {
            treeIndex *= 2;
        }
        numbers = new int[treeIndex * 2];
        Arrays.fill(numbers, Integer.MAX_VALUE); // 최솟값이 구해지므로 MAX_VALUE로 초기화
        treeIndex--;

        // 값 저장 - O(NlogN)
        for (int i = 1; i <= N; i++) {
            int number = Integer.parseInt(reader.readLine());
            update(i, number);
        }

        // 조회 출력 - O(MlogN)
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            writer.write(calculateMinValue(start, end) + "\n");
        }

        writer.close();
        reader.close();
    }

    private static void update(int index, int value) {
        index += treeIndex;
        numbers[index] = value;
        index /= 2;
        while (index >= 1) {
            numbers[index] = Math.min(numbers[index * 2], numbers[index * 2 + 1]);
            index /= 2;
        }
    }

    private static int calculateMinValue(int start, int end) {
        start += treeIndex;
        end += treeIndex;
        int result = Integer.MAX_VALUE;

        while (start <= end) {
            if (start % 2 != 0) {
                result = Math.min(result, numbers[start]);
            }
            if (end % 2 == 0) {
                result = Math.min(result, numbers[end]); // 용어 실수..
            }
            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return result;
    }
}
