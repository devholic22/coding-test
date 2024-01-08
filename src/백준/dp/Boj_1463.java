package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1463 {

    /*
    SOLVED: 24.01.08 (월)
    1로 만들기 - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000,000
        int[] numbers = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            numbers[i] = numbers[i - 1] + 1; // 기본적으로는 -1을 한 값보다 연산을 한 번 더 한 것으로 설정
            if (i % 3 == 0) numbers[i] = Math.min(numbers[i], numbers[i / 3] + 1); // 3으로 나뉠 경우 선택 가능
            if (i % 2 == 0) numbers[i] = Math.min(numbers[i], numbers[i / 2] + 1); // 2로 나뉠 경우 선택 가능
        }

        writer.write(numbers[N] + "");
        writer.close();
        reader.close();
    }
}
