package 백준.dp.배낭문제;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_12865_평범한배낭 {

    private static class Product {

        private int weight;
        private int value;

        public Product(final int weight, final int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    /*
    Solved: 24.02.17 (토)
    평범한 배낭 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 100
        int maxWeight = Integer.parseInt(tokenizer.nextToken());
        int[][] dp = new int[N + 1][maxWeight + 1];
        Product[] products = new Product[N + 1];
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int value = Integer.parseInt(tokenizer.nextToken());
            products[i] = new Product(weight, value);
        }

        // 핵심 로직
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                int select;
                if (j - products[i].weight < 0) {
                    select = 0;
                } else {
                    select = dp[i - 1][j - products[i].weight] + products[i].value;
                }
                dp[i][j] = Math.max(dp[i - 1][j], select);
            }
        }

        /*
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
         */
        // nw(n, w) = max(nw(n - 1, w), nw(n - 1, w - weight[i]) + value[i])
        // 예시로 2개를 고르면서 무게 제한이 10이라면, 1개를 선택할 것이면서 특정 무게를 포함한 경우 / 1개를 선택할 것이면서 다른 하나는 고르지 않아 무게가 그대로인 경우로 나눌 수 있다.
        // 그동안 나는 재귀적으로만 (백트래킹 등) 배낭 문제를 생각했었고 따라서 시간 초과가 났었다.
        // DP를 2차원 배열로 푸는 것에 익숙해져야 한다.

        writer.write(dp[N][maxWeight] + "");
        writer.close();
        reader.close();
    }
}
