package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10971_외판원_순회_2 {

    private static int N;
    private static int result;
    private static int temp;
    private static int[][] cost;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        result = Integer.MAX_VALUE;
        cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            // temp = 0;
            visited = new boolean[N];
            visited[i] = true;
            backtracking(i, i, 0);
        }

        System.out.println(result);
        reader.close();
    }

    private static void backtracking(int city, int start, int value) {
        if (value > result) {
            return;
        }
        if (isAllVisited()) {
            if (cost[city][start] == 0 || value == 0) {
                return;
            }
            value += cost[city][start];
            result = Math.min(result, value);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i] || cost[city][i] == 0) {
                continue;
            }
            visited[i] = true;
            // temp += cost[city][i];
            backtracking(i, start, value + cost[city][i]);
            visited[i] = false;
            // temp -= cost[city][i];
        }
    }

    private static boolean isAllVisited() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
