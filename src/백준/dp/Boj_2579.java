package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_2579 {

    /*
    SOLVED: 24.01.08 (월)
    계단 오르기 - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 300
        int[] stairs = new int[N + 1];
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(reader.readLine());
        }
        dp[1] = stairs[1];
        if (N >= 2) {
            dp[2] = stairs[1] + stairs[2];
        }
        if (N >= 3) {
            dp[3] = Math.max(stairs[1] + stairs[3], stairs[2] + stairs[3]);
        }
        for (int i = 4; i <= N; i++) {
            // 본인 위치 선택은 필수
            // 본인 위치 + (본인 위치 - 1)번째를 선택하려면 (본인 위치 - 2)번째는 선택하지 않아야 함. 즉 dp[i - 2]는 안되고 dp[i - 3]만 활용 가능
            // 본인 위치 + (본인 위치 - 2)번째를 선택하려면 그 이전의 계단 선택은 어떻게 하든지 상관 없음. 즉 stairs[i] + dp[i - 2] 가능
            dp[i] = Math.max(stairs[i] + stairs[i - 1] + dp[i - 3], stairs[i] + dp[i - 2]);
        }

        writer.write(dp[N] + "");
        writer.close();
        reader.close();
    }
}
