package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1309_동물원 {

    /*
    Solved: 24.02.17 (토)
    동물원 - 실버1
    - ✅ 점화식을 떠올렸다.
    - ❌ 부분적으로만 왜 그런지 알았다. (전부 고르지 않을 경우 1, 1개를 고를 경우 2 x n, n개를 고를 경우 2)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int[] dp = new int[100_000 + 1];
        dp[1] = 3;
        dp[2] = 7;
        for (int i = 3; i <= N; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 2]) % 9901;
        }

        writer.write(dp[N] + "");
        writer.close();
        reader.close();
    }
}
