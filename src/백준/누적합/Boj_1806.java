package 백준.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1806 {

    /*
    SOLVED: 23.12.29 (금)
    부분합 - 골드4
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int target = Integer.parseInt(tokenizer.nextToken());
        int[] numbers = new int[N]; // 숫자 배열
        int[] sumValues = new int[N]; // 누적 합 보관 배열
        int result = Integer.MAX_VALUE;

        // 숫자 저장
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        // 누적 합 저장
        sumValues[0] = numbers[0];
        for (int i = 1; i < N; i++) {
            sumValues[i] = numbers[i] + sumValues[i - 1];
        }

        // 구간 합 로직 - O(N)
        int start = 0;
        int end = 0;
        while (end < N && start < N) {
            int sum = calculateBetweenSum(start, end, sumValues, numbers);
            if (sum < target) { // 미만일 경우에는 더 늘려본다.
                end++;
            } else { // 이상일 경우에는 result를 갱신하고, start를 증가시킨다.
                result = Math.min(result, end - start + 1);
                start++;
            }
        }

        // 원래의 초깃값과 같을 경우에는 0으로 설정
        if (result == Integer.MAX_VALUE) {
            result = 0;
        }
        writer.write(result + "");
        writer.close();
        reader.close();
    }

    // 구간합 계산 - O(1)
    private static int calculateBetweenSum(int start, int end, int[] sumValues, int[] numbers) {
        return sumValues[end] - sumValues[start] + numbers[start];
    }
}
