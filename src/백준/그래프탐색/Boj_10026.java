package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Boj_10026 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static final String RED = "R";
    private static final String GREEN = "G";
    private static final String BLUE = "B";

    private static int N;

    /*
    SOLVED: 23.12.28 (목)
    적록색약 - 골드5
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100
        String[][] section = new String[N][N];
        boolean[][] visited = new boolean[N][N];
        HashMap<String, Integer> history = new HashMap<>();
        history.put(RED, 0);
        history.put(GREEN, 0);
        history.put(BLUE, 0);
        int nonPatientCount = 0;
        int patientCount = 0;

        // init
        for (int i = 0; i < N; i++) {
            String[] row = reader.readLine().split("");
            for (int j = 0; j < N; j++) {
                section[i][j] = row[j];
            }
        }

        // 적록색약이 아닌 사람: 빨강, 파랑, 초록
        // 적록색약인 사람: 빨강-초록, 파랑
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                pickColor(section[i][j], i, j, section, visited);
                history.put(section[i][j], history.get(section[i][j]) + 1);
            }
        }
        for (String key : history.keySet()) {
            nonPatientCount += history.get(key);
        }

        // patient
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || section[i][j].equals(BLUE)) {
                    continue;
                }
                patient(i, j, section, visited);
                patientCount++;
            }
        }
        patientCount += history.get(BLUE);

        writer.write(nonPatientCount + " " + patientCount);
        writer.close();
        reader.close();
    }

    private static void pickColor(String target, int y, int x, String[][] section, boolean[][] visited) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || !section[ny][nx].equals(target)) {
                continue;
            }
            pickColor(target, ny, nx, section, visited);
        }
    }

    private static void patient(int y, int x, String[][] section, boolean[][] visited) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || section[y][x].equals(BLUE)) {
                continue;
            }
            patient(ny, nx, section, visited);
        }
    }
}
