package 백준.dp.배낭문제;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_7579_앱 {

    private static class App {

        private int weightMemory;
        private int costTime;
    }

    /*
    Solved: 24.02.17 (토)
    앱 - 골드3
    - ✅ 발생할 수 있는 최대 무게 (100 x 100)를 이용해보려는 시도를 해봤다. (M으로 할 경우 시간 초과가 발생함을 알고 있었다.)
    - ✅ 기본 공식을 떠올렸고, 통하는 공식이었다.
    - ❌ result = Math.min(result, j)를 떠올리지 못했다.
    - ❌ 비용이 0부터 시작할 수도 있음을 떠올리지 못했다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 100
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 10_000_000
        int[][] dp = new int[N + 1][10000 + 1];
        App[] apps = new App[N + 1];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            apps[i] = new App();
            apps[i].weightMemory = Integer.parseInt(tokenizer.nextToken());
        }
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            apps[i].costTime = Integer.parseInt(tokenizer.nextToken());
        }

        /*
        원래 알았던 기본적인 템플릿: M이 "제한 무게" 였을 경우
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (j - apps[i].costTime < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - apps[i].costTime] + apps[i].weightMemory);
                }
            }
        }
        */

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 10000; j++) { // 최소 시간이 0이 나올 수도 있으므로 0부터 시작해야 한다.
                if (j - apps[i].costTime < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - apps[i].costTime] + apps[i].weightMemory);
                }
                // 문제에서 요구했던 추가적인 부분
                // j는 "시간"에 대한 열이므로 j를 이용하여 result를 갱신시키면 되었다.
                if (dp[i][j] >= M) {
                    // System.out.println("i = " + i + ", j = " + j + ", result = " + result);
                    result = Math.min(result, j);
                }
            }
        }

        /*
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 100; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
         */
        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
