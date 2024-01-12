package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_15988 {

    /*
    SOLVED: 24.01.12 (금)
    1로 만들기 3 - 실버2
    범위 계산 잘 하자..
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        long[] numbers = new long[1_000_001];

        numbers[1] = 1; // 1
        numbers[2] = 2; // 1 + 1, 2
        numbers[3] = 4; // 1 + 1 + 1, 2 + 1, 1 + 2, 3

        // 미리 만들어둔다.
        for (int i = 4; i <= 1_000_000; i++) {
            numbers[i] = (numbers[i - 3] + numbers[i - 2] + numbers[i - 1]) % 1_000_000_009;
        }

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(reader.readLine());
            writer.write(numbers[input] + "\n");
        }

        writer.close();
        reader.close();
    }
}
