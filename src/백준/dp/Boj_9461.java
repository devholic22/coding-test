package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_9461 {

    /*
    SOLVED: 24.01.20 (토)
    파도반 수열 - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] numbers = new long[100 + 1];

        numbers[1] = 1;
        numbers[2] = 1;
        numbers[3] = 1;

        // 비교적 규칙을 쉽게 찾을 수 있었다.
        for (int i = 4; i <= 100; i++) {
            numbers[i] = numbers[i - 2] + numbers[i - 3];
        }
        // System.out.println(Arrays.toString(numbers)); 출력 결과 int 범위가 초과됨을 알았다.
        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(reader.readLine());
            writer.write(numbers[number] + "\n");
        }
        writer.close();
        reader.close();
    }
}
