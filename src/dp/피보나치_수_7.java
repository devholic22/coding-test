package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 피보나치_수_7 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        saveInput();
        initArray();
        dynamic();
        printAnswer();
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void saveInput() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    private static void initArray() {
        dp = new int[N + 1];
        dp[0] = 0;
        if (N >= 1) {
            dp[1] = 1;
        }
    }

    private static void dynamic() {
        for (int n = 2; n <= N; n++) {
            dp[n] = (dp[n - 1] + dp[n - 2]) % 1_000_000_007;
        }
    }

    private static void printAnswer() throws IOException {
        bw.write(dp[N] + "");
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
