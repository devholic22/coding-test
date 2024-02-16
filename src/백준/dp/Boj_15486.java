package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_15486 {

    private static int N; // 1 <= N <= 1,500,000
    private static Consult[] consults;
    private static int[] dp;
    private static class Consult {

        private int day;
        private int cost;
        private int bonus;

        public Consult(final int day, final int cost, final int bonus) {
            this.day = day;
            this.cost = cost;
            this.bonus = bonus;
        }
    }

    /*
    NOT SOLVED: 24.02.16 (금)
    퇴사 2 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        dp = new int[N + 1];
        consults = new Consult[N + 1];
        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int cost = Integer.parseInt(tokenizer.nextToken());
            int bonus = Integer.parseInt(tokenizer.nextToken());
            consults[i] = new Consult(i, cost, bonus);
        }

        /*
        for (int i = 1; i <= N; i++) {
            dp[i] = consults[i].bonus;
        }
        */
        for (int i = N; i > 0; i--) {
            if (i + consults[i].cost > N + 1) {
                dp[i] = 0;
                continue;
            } else {
                dp[i] = consults[i].bonus + dp[Math.min(i + consults[i].cost, N)];
            }
        }
        
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dp[i]);
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
