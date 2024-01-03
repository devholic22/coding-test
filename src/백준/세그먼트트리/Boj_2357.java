package 백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2357 {

    // 최솟값 트리, 최댓값 트리 두 개를 함께 만들면 된다.
    private static int[] maxTree;
    private static int[] minTree;

    private static int treeIndex;

    /*
    SOLVED: 24.01.03 (수)
    최솟값과 최댓값 - 골드1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 100,000
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 100,000

        // 다른 세그먼트 트리들과 같은 작업
        treeIndex = 1;
        while (treeIndex < N) {
            treeIndex *= 2;
        }
        // 최댓값 트리는 초깃값으로 최솟값
        maxTree = new int[treeIndex * 2];
        Arrays.fill(maxTree, Integer.MIN_VALUE);
        // 최솟값 트리는 초깃값으로 최댓값
        minTree = new int[treeIndex * 2];
        Arrays.fill(minTree, Integer.MAX_VALUE);
        treeIndex--;

        // 값 저장 - O(NlogN)
        for (int i = 1; i <= N; i++) {
            int number = Integer.parseInt(reader.readLine());
            update(i, number);
        }

        // 결과 출력 (result[0]: 최솟값, result[1]: 최댓값) - (MlogN)
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int[] result = calculateValue(start, end);
            writer.write(result[0] + " " + result[1] + "\n");
        }

        writer.close();
        reader.close();
    }

    private static void update(int index, int value) {
        index += treeIndex;
        maxTree[index] = value;
        minTree[index] = value;
        index /= 2;
        while (index >= 1) {
            maxTree[index] = Math.max(maxTree[index * 2], maxTree[index * 2 + 1]);
            minTree[index] = Math.min(minTree[index * 2], minTree[index * 2 + 1]);
            index /= 2;
        }
    }

    private static int[] calculateValue(int start, int end) {
        start += treeIndex;
        end += treeIndex;
        int minResult = Integer.MAX_VALUE;
        int maxResult = Integer.MIN_VALUE;
        while (start <= end) {
            if (start % 2 != 0) {
                minResult = Math.min(minResult, minTree[start]);
                maxResult = Math.max(maxResult, maxTree[start]);
            }
            if (end % 2 == 0) {
                minResult = Math.min(minResult, minTree[end]);
                maxResult = Math.max(maxResult, maxTree[end]);
            }
            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return new int[] {minResult, maxResult};
    }
}
