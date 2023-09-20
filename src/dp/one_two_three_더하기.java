package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class one_two_three_더하기 {

    private static int N = 11;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        init();
        int T = Integer.parseInt(br.readLine());
        result = new int[N];
        result[1] = 1;
        result[2] = 2;
        result[3] = 4;
        for (int i = 0; i < T; i++) {
            int number = Integer.parseInt(br.readLine());
            bw.write(dp(number) + "\n");
        }
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static int dp(int n) {
        for (int i = 4; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2] + result[i - 3];
        }
        return result[n];
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
