package 백준.세그먼트트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_2042 {

    private static long[] numbers;
    private static int treeIndex;

    /*
    SOLVED: 23.12.31 (일)
    구간 합 구하기 - 골드1
    죠르디 해설 참고, 세그먼트 트리의 대표적인 기본 문제
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 1,000,000
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 10,000
        int K = Integer.parseInt(tokenizer.nextToken()); // 1 <= K <= 10,000
        treeIndex = 1;
        int CHANGE = 1;
        int CALCULATE = 2;

        while (treeIndex < N) {
            treeIndex *= 2;
        }
        numbers = new long[treeIndex * 2];
        treeIndex--; // 일종의 제로 인덱스 (zero index) 모방: 특정 index를 받을 때, 내부적으로 몇을 더해야 그곳에 도달하는가? 그러려면 -1을 해야 한다.

        // 기본 값을 적용할 때에도 update 함수를 이용한다.
        // 인덱스가 1번부터임을 주의하라.
        for (int i = 1; i <= N; i++) { // O(NlogN)
            update(i, Long.parseLong(reader.readLine())); // O(logN)
        }

        for (int i = 0; i < M + K; i++) { // O((M+K)logN)
            tokenizer = new StringTokenizer(reader.readLine());
            int command = Integer.parseInt(tokenizer.nextToken());
            if (command == CHANGE) {
                int index = Integer.parseInt(tokenizer.nextToken());
                long value = Long.parseLong(tokenizer.nextToken());
                update(index, value); // log(N)
            } else {
                int start = Integer.parseInt(tokenizer.nextToken());
                int end = Integer.parseInt(tokenizer.nextToken());
                writer.write(sum(start, end) + "\n"); // log(N)
            }
        }

        writer.close();
        reader.close();
    }

    private static long sum(int start, int end) {
        // treeIndex 더하기 중요
        start += treeIndex;
        end += treeIndex;

        long sum = 0;
        while (start <= end) {
            if (start % 2 != 0) {
                sum += numbers[start];
            }
            if (end % 2 == 0) {
                sum += numbers[end];
            }
            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        return sum;
    }

    private static void update(int index, long value) {
        index += treeIndex; // treeIndex 적용 (실제 tree에서 몇 번 인덱스를 바꾸고자 하는가)
        numbers[index] = value;
        index /= 2; // 미리 2로 나누기 중요 (자신의 부모부터 시작되도록 하기 위함)
        while (index >= 1) {
            numbers[index] = numbers[index * 2] + numbers[index * 2 + 1];
            index /= 2;
        }
    }
}
