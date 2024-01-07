package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_25418 {

    private static BufferedWriter writer;
    private static int K;
    private static int[] numbers;

    /*
    SOLVED: 24.01.07 (일)
    정수 a를 k로 만들기 - 실버3
    새로 알게 된 사실: 자바 환경에서도 DP의 depth가 깊어질 경우 StackOverflow가 발생할 수 있다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int A = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        numbers = new int[K + 1]; // K를 표현할 수 있어야 하므로 K + 1까지만 할당
        numbers[A] = 0; // 시작 지점의 기본값은 0

        for (int i = A; i <= K; i++) {
            // i + 1이 K 이하일 때만 진행
            if (i + 1 <= K) {
                // 값이 없는 상태라면 이전 값 + 1
                if (numbers[i + 1] == 0) {
                    numbers[i + 1] = numbers[i] + 1;
                } else {
                    // 그렇지 않다면 기존에 저장된 것과 이후의 값을 비교하여 더 작은 값으로 할당
                    numbers[i + 1] = Math.min(numbers[i + 1], numbers[i] + 1);
                }
            }
            // 2 * i가 K 이하일 때만 진행
            if (2 * i <= K) {
                // 값이 없는 상태라면 이전 값 + 1
                if (numbers[2 * i] == 0) {
                    numbers[2 * i] = numbers[i] + 1;
                } else {
                    // 그렇지 않다면 기존에 저장된 것과 이후의 값을 비교하여 더 작은 값으로 할당
                    numbers[2 * i] = Math.min(numbers[2 * i], numbers[i] + 1);
                }
            }
        }

        writer.write(numbers[K] + "");
        writer.close();
        reader.close();
    }
}
