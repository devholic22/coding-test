package 백준.복습_1회차.dp.배낭문제;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_7579_앱 {

    private static int N;
    private static int M;
    private static int[] memory;
    private static int[] cost;
    private static int[][] dp;

    /*
    Solved: 24.02.19 (월)
    앱 - 골드3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 100
        M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 10,000,000
        memory = new int[N + 1];
        cost = new int[N + 1];
        dp = new int[N + 1][10000 + 1]; // 최대 비용을 10000으로 잡은 이유: N이 최대 100이고, 각 비용이 100까지만 있음. 즉 전부 낸다고 가정하면 100 x 100 = 10000
        int result = Integer.MAX_VALUE;

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 10000; j++) {
                // 선택하지 않을 경우 (or 못할 경우) - dp[i - 1][j]
                // 선택할 경우 - Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i])
                int remain = j - cost[i];
                if (remain < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                }
                // dp[i][j]는 가치를 뜻한다. 즉, 여기에서는 메모리이다.
                // 따라서, 계산된 메모리가 M 이상이면 result와 갱신 비교하면 된다.
                // 무엇과? ==> j와 (dp[i][j]에서 j는 현재 비용을 뜻한다. 최대점이 maxWeight (여기에서는 10000)이고..)
                if (dp[i][j] >= M) {
                    result = Math.min(result, j);
                }
            }
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
