package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 적록색약 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int MAX_SIZE;
    private static String[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();

        MAX_SIZE = Integer.parseInt(br.readLine());
        int blue = 0;
        int generalRed = 0;
        int generalGreen = 0;
        int patient = 0;
        map = new String[MAX_SIZE][MAX_SIZE];
        visited = new boolean[MAX_SIZE][MAX_SIZE];

        storeColorIntoMap();

        // blue count
        blue += countColor("B");
        // general red count
        generalRed += countColor("R");
        // general green count
        generalGreen += countColor("G");
        // patient red & green count
        patient += countPatient();

        bw.write(blue + generalGreen + generalRed + " " + (blue + patient));
        close();
    }

    private static void storeColorIntoMap() throws IOException {
        for (int i = 0; i < MAX_SIZE; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < MAX_SIZE; j++) {
                map[i][j] = row[j];
            }
        }
    }

    private static int countColor(String keyword) {
        int result = 0;
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                if (visited[i][j] || !map[i][j].equals(keyword))
                    continue;
                result += countColorDfs(i, j, keyword);
            }
        }
        resetVisited();
        return result;
    }

    private static void resetVisited() {
        for (int i = 0; i < MAX_SIZE; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static int countColorDfs(int y, int x, String color) {
        visited[y][x] = true;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny >= MAX_SIZE || ny < 0 || nx >= MAX_SIZE || nx < 0 || visited[ny][nx] || !map[ny][nx].equals(color))
                continue;
            countColorDfs(ny, nx, color);
        }
        return 1;
    }

    private static int countPatient() {
        int result = 0;
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                if (visited[i][j] || map[i][j].equals("B"))
                    continue;
                patientDfs(i, j);
                result++;
            }
        }
        return result;
    }

    private static int patientDfs(int y, int x) {
        visited[y][x] = true;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny >= MAX_SIZE || ny < 0 || nx >= MAX_SIZE || nx < 0 || visited[ny][nx] || map[ny][nx].equals("B"))
                continue;
            patientDfs(ny, nx);
        }
        return 1;
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
