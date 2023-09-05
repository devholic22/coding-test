package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 빙산 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[][] map;
    private static int MAX_Y;
    private static int MAX_X;

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        MAX_Y = Integer.parseInt(st.nextToken());
        MAX_X = Integer.parseInt(st.nextToken());
        int year = 1; // 최종 결괏값
        map = new int[MAX_Y][MAX_X];
        for (int i = 0; i < MAX_Y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAX_X; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ice = 1;
        boolean allMelt = false;
        while (ice == 1) {
            boolean[][] visited = new boolean[MAX_Y][MAX_X];
            for (int i = 0; i < MAX_Y; i++) {
                for (int j = 0; j < MAX_X; j++) {
                    if (map[i][j] != 0 && !visited[i][j])
                        melt(i, j, visited);
                }
            }
            ice = countIce();
            if (ice > 1)
                break;
            if (isAllMelt()) {
                allMelt = true;
                break;
            }
            year++;
        }
        if (!allMelt)
            bw.write(year + "");
        else {
            bw.write("0");
        }
        close();
    }

    // 초기화
    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    // 전부 다 녹았는지 파악 함수
    private static boolean isAllMelt() {
        int sum = 0;
        for (int i = 0; i < MAX_Y; i++) {
            for (int j = 0; j < MAX_X; j++) {
                sum += map[i][j];
                if (sum > 0)
                    return false;
            }
        }
        return true;
    }

    // 덩어리 갯수 파악 함수
    private static int countIce() {
        int count = 0;
        boolean[][] visited = new boolean[MAX_Y][MAX_X];
        for (int i = 0; i < MAX_Y; i++) {
            for (int j = 0; j < MAX_X; j++) {
                if (visited[i][j] || map[i][j] == 0)
                    continue;
                count += count(i, j, visited);
            }
        }
        return count;
    }

    // 덩어리 갯수 파악 함수
    private static int count(int y, int x, boolean[][] visited) {
        visited[y][x] = true;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny >= MAX_Y || ny < 0 || nx >= MAX_X || nx < 0 || visited[ny][nx] || map[ny][nx] == 0)
                continue;
            count(ny, nx, visited);
        }
        return 1;
    }

    // 녹이는 DFS 함수
    private static void melt(int y, int x, boolean[][] visited) {
        visited[y][x] = true;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        int blank = 0;
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny >= MAX_Y || ny < 0 || nx >= MAX_X || nx < 0)
                continue;
            if (map[ny][nx] == 0)
                blank++;
        }
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny >= MAX_Y || ny < 0 || nx >= MAX_X || nx < 0 || map[ny][nx] == 0 || visited[ny][nx])
                continue;
            melt(ny, nx, visited);
        }
        map[y][x] -= blank;
        if (map[y][x] < 0)
            map[y][x] = 0;
    }

    // 종료
    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
