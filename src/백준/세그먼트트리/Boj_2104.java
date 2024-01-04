package 백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2104 {

    private static int[] positions;
    private static long[] sums;
    private static int[] numbers;
    private static int treeIndex;
    private static long result;

    /*
    SOLVED: 24.01.04 (목)
    부분배열 고르기 - 플래티넘5
    최솟값 인덱스 구현을 알게 된 문제라, 진짜 많이 배웠던 문제다.
    구간 합과 세그먼트 트리를 함께 사용했다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100,000
        treeIndex = 1;
        while (treeIndex < N) {
            treeIndex *= 2;
        }
        numbers = new int[N + 1];
        sums = new long[N + 1];
        positions = new int[treeIndex * 2];
        result = Integer.MIN_VALUE;
        Arrays.fill(numbers, Integer.MAX_VALUE);
        Arrays.fill(positions, 0);
        Arrays.fill(sums, 0);
        treeIndex--;

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
            sums[i] = sums[i - 1] + numbers[i];
            update(i);
        }

        /*
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(positions));
        System.out.println(Arrays.toString(sums));
        */

        logic(1, N);
        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static void update(int index) {
        index += treeIndex;
        positions[index] = index - treeIndex;
        index /= 2;
        while (index >= 1) {
            if (numbers[positions[index * 2]] < numbers[positions[index * 2 + 1]]) {
                positions[index] = positions[index * 2];
            } else {
                positions[index] = positions[index * 2 + 1];
            }
            index /= 2;
        }
    }

    private static long getSum(int start, int end) {
        return sums[end] - sums[start - 1];
    }

    // 최솟값의 인덱스를 반환하는 함수이다.
    // numbers, positions, index 값을 적용할 시 실수하지 않도록 주의해야 한다.
    // 최솟값 인덱스를 구하는 함수는 따로 정리해두자. (그동안에는 최솟값 트리로만 구현했었기에..)
    private static int getMinIndex(int start, int end) {
        start += treeIndex;
        end += treeIndex;
        if (start == end) {
            return start;
        }
        int index = 0;
        while (start <= end) {
            if (start % 2 != 0) {
                if (numbers[index] > numbers[positions[start]]) {
                    index = positions[start];
                }
            }
            if (end % 2 == 0) {
                if (numbers[index] > numbers[positions[end]]) {
                    index = positions[end];
                }
            }
            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return index;
    }

    // 분할 정복으로 풀어야 시간 초과가 나지 않는다.
    // 최솟값 인덱스를 기준으로 왼쪽, 오른쪽 실행한다. 최솟값을 배제시키는 식으로 진행하는 것이다.
    private static void logic(int start, int end) {
        if (start > end) {
            return;
        }
        if (start == end) {
            result = Math.max(result, (long) numbers[start] * numbers[start]);
            return;
        }
        int minIndex = getMinIndex(start, end);
        long value = getSum(start, end) * numbers[minIndex];
        result = Math.max(result, value);
        logic(start, minIndex - 1);
        logic(minIndex + 1, end);
    }
}
