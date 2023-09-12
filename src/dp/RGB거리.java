package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class RGB거리 {

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    private static int[][] cost;
    private static int[][] dp;
    private static int N;
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        init();

        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][RED] = cost[0][RED];
        dp[0][GREEN] = cost[0][GREEN];
        dp[0][BLUE] = cost[0][BLUE];

        bw.write(Math.min(paint(N - 1, RED), Math.min(paint(N - 1, GREEN), paint(N - 1, BLUE))) + "");
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static boolean isAlreadyPainted(int house, int color) {
        return dp[house][color] != 0;
    }

    private static int paint(int house, int color) {
        if (!isAlreadyPainted(house, color)) {
            if (color == RED) {
                dp[house][RED] = cost[house][RED] + Math.min(paint(house - 1, GREEN), paint(house - 1, BLUE));
            } else if (color == GREEN) {
                dp[house][GREEN] = cost[house][GREEN] + Math.min(paint(house - 1, RED), paint(house - 1, BLUE));
            } else {
                dp[house][BLUE] = cost[house][BLUE] + Math.min(paint(house - 1, RED), paint(house - 1, GREEN));
            }
        }
        return dp[house][color];
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
