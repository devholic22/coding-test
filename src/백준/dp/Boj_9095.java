package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_9095 {

    /*
    SOLVED: 24.01.08 (월)
    1, 2, 3 더하기 - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine()); // 1 <= N < 11
            int[] numbers = new int[11]; // 기본 설정 (인덱스 오류 방지하기 위해 편리하게 11로 고정)
            // DP를 위한 초깃값 설정
            numbers[1] = 1;
            numbers[2] = 2;
            numbers[3] = 4;

            // DP
            for (int j = 4; j <= N; j++) {
                numbers[j] = numbers[j - 1] + numbers[j - 2] + numbers[j - 3];
            }
            writer.write(numbers[N] + "\n");
        }

        writer.close();
        reader.close();
    }
}
