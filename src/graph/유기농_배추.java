package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 유기농_배추 {

    private static int T;
    private static int[][] map;
    private static boolean[][] visited;
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        init();
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int MAX_X = Integer.parseInt(st.nextToken());
            final int MAX_Y = Integer.parseInt(st.nextToken());
            map = new int[MAX_Y][MAX_X];
            visited = new boolean[MAX_Y][MAX_X];
            final int SEED = Integer.parseInt(st.nextToken());
            storeSeed(SEED);
            logic(MAX_Y, MAX_X);
        }
        end();
    }

    private static void logic(int MAX_Y, int MAX_X) throws IOException {
        int sum = 0;

        for (int j = 0; j < MAX_Y; j++) {
            for (int k = 0; k < MAX_X; k++) {
                if (visited[j][k] || map[j][k] == 0)
                    continue;
                sum += dfs(j, MAX_Y, k, MAX_X);
            }
        }

        bw.write(sum + "\n");
    }

    private static void storeSeed(int SEED) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < SEED; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[y][x] = 1;
        }
    }

    private static int dfs(int y, int MAX_Y, int x, int MAX_X) {
        if (y < 0 || y >= MAX_Y || x < 0 || x >= MAX_X || visited[y][x] || map[y][x] == 0)
            return 0;

        visited[y][x] = true;

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            dfs(y + dy[i], MAX_Y, x + dx[i], MAX_X);
        }

        return 1;
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void end() throws IOException {
        bw.close();
        br.close();
    }
}
