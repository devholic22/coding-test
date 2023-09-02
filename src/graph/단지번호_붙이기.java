package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class 단지번호_붙이기 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();

        int SIZE = Integer.parseInt(br.readLine());
        visited = new boolean[SIZE][SIZE];
        map = new int[SIZE][SIZE];
        drawMap(SIZE);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 1 && !visited[i][j])
                    list.add(dfs(i, j, SIZE));
            }
        }

        int[] result = new int[list.size()];
        storeResult(result, list);
        printResult(result);

        close();
    }

    private static void storeResult(int[] result, ArrayList<Integer> list) {
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        Arrays.sort(result);
    }

    private static void printResult(int[] result) throws IOException {
        bw.write(result.length + "\n");
        for (int number : result) {
            bw.write(number + "\n");
        }
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }

    private static void drawMap(int SIZE) throws IOException {
        for (int i = 0; i < SIZE; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static int dfs(int y, int x, int SIZE) {
        int count = 1;
        visited[y][x] = true;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny < 0 || ny >= SIZE || nx < 0 || nx >= SIZE || visited[ny][nx] || map[ny][nx] == 0)
                continue;
            visited[ny][nx] = true;
            count += dfs(ny, nx, SIZE);
        }
        return count;
    }
}
