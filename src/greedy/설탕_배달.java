package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 설탕_배달 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[] dp;
    private static int N;

    public static void main(String[] args) throws IOException {
        init();
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        dp[3] = 1;
        if (N >= 5) {
            dp[5] = 1;
        }
        for (int i = 6; i <= N; i++) {
            if (dp[i - 3] != -1 && dp[i - 5] == -1) {
                dp[i] = dp[i - 3] + 1;
            } else if (dp[i - 3] == -1 && dp[i - 5] != -1){
                dp[i] = dp[i - 5] + 1;
            } else if (dp[i - 3] != -1 && dp[i - 5] != -1) {
                dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
            }
        }
        bw.write(dp[N] + "");
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
