package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 전쟁_전투 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int MAX_Y;
    private static int MAX_X;
    private static int our;
    private static int enemy;
    private static String[][] map;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        init();
        createMap();
        draw();
        logic("W");
        visited = new boolean[MAX_Y][MAX_X];
        logic("B");
        bw.write(our + " " + enemy);
        bw.close();
        br.close();
    }

    private static void draw() throws IOException {
        for (int i = 0; i < MAX_Y; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < MAX_X; j++) {
                map[i][j] = row[j];
            }
        }
    }

    private static void createMap() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        MAX_X = Integer.parseInt(st.nextToken());
        MAX_Y = Integer.parseInt(st.nextToken());
        map = new String[MAX_Y][MAX_X];
        visited = new boolean[MAX_Y][MAX_X];
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static int dfs(String target, int y, int x) {
        visited[y][x] = true;
        int count = 1;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny < 0 || ny >= MAX_Y || nx < 0 || nx >= MAX_X || !map[ny][nx].equals(target) || visited[ny][nx])
                continue;
            count += dfs(target, ny, nx);
        }
        return count;
    }

    private static void logic(String target) {
        for (int i = 0; i < MAX_Y; i++) {
            for (int j = 0; j < MAX_X; j++) {
                if (map[i][j].equals(target) && !visited[i][j]) {
                    int sum = dfs(target, i, j);
                    if (target.equals("W"))
                        our += (sum * sum);
                    else
                        enemy += (sum * sum);
                }
            }
        }
    }
}
