package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class one_two_three_더하기_2 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int K;

    private static String[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new String[11][];

        dp[1] = new String[]{"", "1"};
        dp[2] = new String[]{"", "1+1", "2"};
        dp[3] = new String[]{"", "1+1+1", "2+1", "1+2", "3"};

        Arrays.sort(dp[1]);
        Arrays.sort(dp[2]);
        Arrays.sort(dp[3]);

        for (int i = 4; i <= N; i++) {
            ArrayList<String> addOneArray = addOne(dp[i - 1]);
            ArrayList<String> addTwoArray = addTwo(dp[i - 2]);
            ArrayList<String> addThreeArray = addThree(dp[i - 3]);

            addOneArray.addAll(addTwoArray);
            addOneArray.addAll(addThreeArray);

            int size = addOneArray.size();
            dp[i] = new String[size + 1];
            dp[i][0] = "";
            for (int j = 1; j <= size; j++) {
                dp[i][j] = addOneArray.get(j - 1);
            }
            Arrays.sort(dp[i]);
        }

        if (K > dp[N].length - 1) {
            bw.write("-1");
        } else {
            bw.write(dp[N][K]);
        }
        close(); // 1 -> 2 -> 4 -> 7 -> 13 -> 24 -> 44 -> 81 -> 149 -> 274
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static ArrayList<String> addOne(String[] arr) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : arr) {
            if (s.isEmpty())
                continue;
            result.add(s + "+1");
        }
        return result;
    }
    private static ArrayList<String> addTwo(String[] arr) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : arr) {
            if (s.isEmpty())
                continue;
            result.add(s + "+2");
        }
        return result;
    }
    private static ArrayList<String> addThree(String[] arr) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : arr) {
            if (s.isEmpty())
                continue;
            result.add(s + "+3");
        }
        return result;
    }
    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
