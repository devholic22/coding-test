package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1926 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static int height;
    private static int width;
    private static boolean[][] visited;
    private static String[][] map;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static final String COLORED = "1";

    /*
    SOLVED: 23.12.28 (목)
    그림 - 실버1
     */
    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        height = Integer.parseInt(tokenizer.nextToken()); // 1 <= height <= 500
        width = Integer.parseInt(tokenizer.nextToken()); // 1 <= width <= 500
        visited = new boolean[height][width];
        map = new String[height][width];
        int count = 0; // 개수 0 초기화
        int maxValue = 0; // 넓이 0 초기화 (문제 요구사항)

        // 값 저장
        for (int i = 0; i < height; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = tokenizer.nextToken();
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visited[i][j] || !map[i][j].equals(COLORED)) { // 만났거나 색칠이 아니라면 패스한다.
                    continue;
                }
                maxValue = Math.max(maxValue, collectImage(i, j)); // 최대 넓이 값을 갱신한다.
                count++; // 그림의 개수는 바깥에서의 함수 호출 횟수와 같다.
            }
        }

        writer.write(count + "\n" + maxValue);
        writer.close();
        reader.close();
    }

    private static int collectImage(int y, int x) {
        // 호출이 되었다는 것은 이동 가능한 좌표였다는 뜻 - 자신의 사이즈를 1로 하고, 방문 처리 진행
        int size = 1;
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            // 갈 수 있을 때만 DFS
            if (ny < 0 || ny >= height || nx < 0 || nx >= width || visited[ny][nx] || !map[ny][nx].equals(COLORED)) {
                continue;
            }
            size += collectImage(ny, nx);
        }
        return size; // 최종적인 넓이 반환
    }
}
