package 백준.복습_1회차.dp.배낭문제;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_12865_평범한배낭 {

    private static int N;
    private static int maxWeight;
    private static int[][] dp;
    private static int[] weight;
    private static int[] value;

    /*
    Solved: 24.02.19 (월)
    평범한 배낭 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        maxWeight = Integer.parseInt(tokenizer.nextToken());
        dp = new int[N + 1][maxWeight + 1];
        weight = new int[N + 1];
        value = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            weight[i] = Integer.parseInt(tokenizer.nextToken());
            value[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                // dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
                int remain = j - weight[i];
                if (remain < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        writer.write(dp[N][maxWeight] + "");
        writer.close();
        reader.close();
    }
}
