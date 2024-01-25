package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1920 {

    /*
    SOLVED: 24.01.25 (목)
    수 찾기 - 실버 4
    기본적인 이진 탐색
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100,000
        int[] numbers = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(numbers); // 이진 탐색 필수 조건
        int M = Integer.parseInt(reader.readLine()); // 1 <= M <= 100,000
        int[] finds = new int[M]; // 21억까지 가능
        boolean[] results = new boolean[M];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < M; i++) {
            finds[i] = Integer.parseInt(tokenizer.nextToken());
            results[i] = isFind(numbers, finds[i]);
        }

        for (int i = 0; i < M; i++) {
            if (results[i]) {
                writer.write("1\n");
            } else {
                writer.write("0\n");
            }
        }
        writer.close();
        reader.close();
    }

    // 이진 탐색 로직
    // 경험 상 같을 때 까지 반복하는 게 있고 같지 않을 때 까지만 반복하는 것도 있는 것 같다.
    private static boolean isFind(int[] numbers, int value) {
        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (value < numbers[mid]) {
                end = mid - 1;
            } else if (value > numbers[mid]){
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
