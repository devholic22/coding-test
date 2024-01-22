package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1904 {

    /*
    SOLVED: 24.01.23 (월)
    01타일 - 실버3
    어쩌다보니 점화식이 직전에 풀은 극장 좌석과 동일..
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000,000
        int[] numbers = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (i <= 2) {
                numbers[i] = i;
                continue;
            }
            numbers[i] = (numbers[i - 1] + numbers[i - 2]) % 15746;
        }

        writer.write(numbers[N] + "");

        writer.close();
        reader.close();
    }
}
