package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 섬의_개수 {

    private static final int DIRECTION = 8;
    private static final int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int LAND = 1;
    private static final int SEA = 0;
    private static final int END = 0;

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] graph;
    private static boolean[][] visited;
    private static int weight;
    private static int height;
    private static int result;

    public static void main(String[] args) throws IOException {
        init();
        while (true) {
            st = new StringTokenizer(br.readLine());
            saveWeightAndHeight();
            if (isEndCommand()) {
                close();
                return;
            }
            initArray();
            initVisited();
            initResult();
            storeGraphInfo();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < weight; x++) {
                    if (!visited[y][x] && isLand(y, x)) {
                        dfs(y, x);
                        result++;
                    }
                }
            }
            System.out.println(result);
        }
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void saveWeightAndHeight() {
        weight = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
    }

    private static void initArray() {
        graph = new int[height][weight];
    }

    private static void initVisited() {
        visited = new boolean[height][weight];
    }

    private static void initResult() {
        result = 0;
    }

    private static boolean isEndCommand() {
        return weight == END && height == END;
    }

    private static void storeGraphInfo() throws IOException {
        for (int y = 0; y < height; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < weight; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void dfs(int y, int x) {
        if (!isValidLocation(y, x) || visited[y][x] && !isLand(y, x)) {
            return;
        }
        visited[y][x] = true;
        for (int direction = 0; direction < DIRECTION; direction++) {
            int ny = y + DY[direction];
            int nx = x + DX[direction];
            if (!isValidLocation(ny, nx) || visited[ny][nx] || !isLand(ny, nx)) {
                continue;
            }
            dfs(ny, nx);
        }
    }

    private static boolean isValidLocation(int y, int x) {
        return y >= 0 && y < height && x >= 0 && x < weight;
    }

    private static boolean isLand(final int y, final int x) {
        return graph[y][x] == LAND;
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
