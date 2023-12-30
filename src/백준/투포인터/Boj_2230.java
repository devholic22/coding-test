package 백준.투포인터;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2230 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static int N;
    private static int M;
    private static int[] numbers;

    /*
    SOLVED: 23.12.30 (토)
    수 고르기 - 골드5
     */
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 100,000
        M = Integer.parseInt(tokenizer.nextToken()); // 0 <= M <= 2,000,000,000
        numbers = new int[N]; // 0 <= numbers[i] <= 1,000,000,000
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(numbers); // 정렬을 미리 시켜야 한다. 그래야 최적의 경우를 획득할 수 있기 때문이다. O(NlogN)

        int start = 0; // 시작점
        int end = 0; // 종료점
        int result = Integer.MAX_VALUE; // 최소인 값으로 구할 것이므로 처음에는 MAX VALUE

        // O(N)
        while (start < N && end < N) {
            int distance = distance(start, end);
            if (distance < M) {
                end++;
            } else {
                result = Math.min(result, distance);
                start++;
            }
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }

    // 절댓값으로 계산
    private static int distance(int start, int end) {
        return Math.abs(numbers[start] - numbers[end]);
    }
}
