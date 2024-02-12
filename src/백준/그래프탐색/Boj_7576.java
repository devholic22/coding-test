package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj_7576 {

    private static final int EMPTY = -1;

    private static int width;
    private static int height;
    private static int[][] map;
    private static ArrayDeque<Integer> xQueue;
    private static ArrayDeque<Integer> yQueue;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static boolean[][] visited;

    /*
    SOLVED: 24.02.12 (월)
    토마토 - 골드5
    단계적으로 여러 곳에서 같이 진행시킬 때 어떻게 수행할 수 있을지 고민할 수 있었던 문제
    핵심 원리: 시작점들을 큐에 넣어두고 순차적으로 BFS 수행하면 됨
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        width = Integer.parseInt(tokenizer.nextToken());
        height = Integer.parseInt(tokenizer.nextToken());
        xQueue = new ArrayDeque<>();
        yQueue = new ArrayDeque<>();
        map = new int[height][width];
        visited = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 1) {
                    yQueue.addLast(i);
                    xQueue.addLast(j);
                    visited[i][j] = true;
                }
            }
        }
        bfs();
        /*
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
         */
        int result = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 0) {
                    writer.write("-1");
                    writer.close();
                    reader.close();
                    return;
                }
                result = Math.max(result, map[i][j]);
            }
        }
        writer.write((result - 1) + "");
        writer.close();
        reader.close();
    }

    private static void bfs() {
        while (!xQueue.isEmpty() && !yQueue.isEmpty()) {
            int currX = xQueue.pollFirst();
            int currY = yQueue.pollFirst();
            for (int i = 0; i < 4; i++) {
                int ny = currY + dy[i];
                int nx = currX + dx[i];
                if (ny < 0 || ny >= height || nx < 0 || nx >= width || map[ny][nx] == EMPTY || visited[ny][nx]) {
                    continue;
                }
                map[ny][nx] = map[currY][currX] + 1;
                xQueue.addLast(nx);
                yQueue.addLast(ny);
                visited[ny][nx] = true;
            }
        }
    }
}
