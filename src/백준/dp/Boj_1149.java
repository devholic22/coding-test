package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1149 {

    private static int N;

    /*
    SOLVED: 24.01.08 (월)
    RGB거리 - 실버1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine()); // 2 <= N <= 1,000
        int[][] dp = new int[N + 1][3];
        int[] red = new int[N + 1];
        int[] green = new int[N + 1];
        int[] blue = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            red[i] = Integer.parseInt(tokenizer.nextToken());
            green[i] = Integer.parseInt(tokenizer.nextToken());
            blue[i] = Integer.parseInt(tokenizer.nextToken());
        }

        // 초깃값 설정
        dp[1][0] = red[1];
        dp[1][1] = green[1];
        dp[1][2] = blue[1];

        // dp[k][0] = min(dp[k - 1][1], dp[k - 1][2]) + red[i]와 같은 구조를 생각했어야 했다. (뒤에서부터 거꾸로 선택해보는...)
        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + red[i];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + green[i];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + blue[i];
        }

        /*
        for (int i = 0; i < 3; i++) {
            logic(1, houses[1][i], i);
        }
        */

        // writer.write(result + "");
        writer.write(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])) + "");
        writer.close();
        reader.close();
    }

    /*
    시도했던 코드 - 시간 초과
    private static void logic(int depth, int value, int index) {
        if (depth == N) {
            result = Math.min(result, value);
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (depth + 1 <= N && result > value + houses[depth + 1][i] && i != index) {
                logic(depth + 1, value + houses[depth + 1][i], i);
            }
        }
    }
     */
}
