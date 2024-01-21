package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_28017 {

    private static int[][] weapons;
    private static int[][] dp;
    private static int result;
    private static int N;
    private static int M;

    /*
    SOLVED: 24.01.21 (일)
    게임을 클리어하자 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        weapons = new int[N][M];
        dp = new int[N][M];
        result = Integer.MAX_VALUE;

        // 값 저장
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                weapons[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        // 초깃값 할당
        for (int i = 0; i < M; i++) {
            dp[0][i] = weapons[0][i];
        }

        // dp[i][j] = weapons[i][j] + min(dp[i - 1][0], dp[i - 1][1], ..., dp[i - 1][M])
        // 점화식을 세우는 게 너무 어렵다.. 따라서 아래 반복문도 세우기 어려웠다.

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < M; k++) {
                    if (j == k) {
                        continue;
                    }
                    min = Math.min(min, dp[i - 1][k]);
                }
                dp[i][j] = weapons[i][j] + min;
            }
        }

        // 최종 정답: 마지막 날에 결정된 값 중 최솟값
        for (int i = 0; i < M; i++) {
            result = Math.min(result, dp[N - 1][i]);
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
