package 백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj_1725 {

    private static int[] numbers;
    private static int[] positions;
    private static int treeIndex;
    private static long result;

    /*
    SOLVED: 24.01.04 (목)
    히스토그램 - 플래티넘5
    부분배열 고르기 문제와 크게 다를 게 없다. 그러니 그것을 보자.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        result = 0;
        treeIndex = 1;
        while (treeIndex < N) {
            treeIndex *= 2;
        }

        numbers = new int[N + 1];
        Arrays.fill(numbers, Integer.MAX_VALUE);
        positions = new int[treeIndex * 2];
        treeIndex--;

        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
            update(i);
        }

        /*
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(positions));
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

    private static int getMinIndex(int start, int end) {
        int index = 0;
        start += treeIndex;
        end += treeIndex;
        if (start == end) {
            return start;
        }
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

    private static void logic(int start, int end) {
        if (start > end) {
            return;
        }
        if (start == end) {
            result = Math.max(result, numbers[start]);
            return;
        }
        int minIndex = getMinIndex(start, end);
        result = Math.max(result, (long) (end - start + 1) * numbers[minIndex]);
        logic(start, minIndex - 1);
        logic(minIndex + 1, end);
    }
}
