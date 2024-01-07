package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj_11123 {

    private static int height;
    private static int width;
    private static String[][] map;
    private static boolean[][] visited;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    /*
    SOLVED: 24.01.07 (일)
    양 한마리... 양 두마리... - 실버2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine()); // 1 <= T <= 100
        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            // 0 < height, width <= 100
            height = Integer.parseInt(tokenizer.nextToken());
            width = Integer.parseInt(tokenizer.nextToken());
            map = new String[height][width];
            int count = 0;
            // 맵 정보 저장
            for (int j = 0; j < height; j++) {
                String[] row = reader.readLine().split("");
                for (int k = 0; k < width; k++) {
                    map[j][k] = row[k];
                }
            }
            // BFS 수행
            visited = new boolean[height][width];
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < width; k++) {
                    if (!visited[j][k] && map[j][k].equals("#")) {
                        bfs(j, k);
                        count++;
                    }
                }
            }
            writer.write(count + "\n");
        }

        writer.close();
        reader.close();
    }

    // 큐를 두 개로 만들어 관리
    private static void bfs(int y, int x) {
        ArrayDeque<Integer> yQueue = new ArrayDeque<>();
        ArrayDeque<Integer> xQueue = new ArrayDeque<>();
        yQueue.addLast(y);
        xQueue.addLast(x);

        while (!yQueue.isEmpty() && !xQueue.isEmpty()) {
            int targetY = yQueue.pollFirst();
            int targetX = xQueue.pollFirst();
            for (int i = 0; i < 4; i++) {
                int ny = targetY + dy[i];
                int nx = targetX + dx[i];
                if (ny < 0 || ny >= height || nx < 0 || nx >= width || visited[ny][nx] || map[ny][nx].equals(".")) {
                    continue;
                }
                visited[ny][nx] = true;
                yQueue.addLast(ny);
                xQueue.addLast(nx);
            }
        }
    }
}
