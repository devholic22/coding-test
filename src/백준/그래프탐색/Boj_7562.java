package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj_7562 {

    /*
    SOLVED: 24.02.05 (월)
    나이트의 이동 - 실버1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            int size = Integer.parseInt(reader.readLine()); // 4 <= size <= 300
            int[][] map = new int[size][size]; // 체스판
            int[][] cost = new int[size][size]; // 비용
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            // 나이트 위치
            int y = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(reader.readLine());
            // 목표 위치
            int targetY = Integer.parseInt(tokenizer.nextToken());
            int targetX = Integer.parseInt(tokenizer.nextToken());
            map[targetY][targetX] = 1; // 목표 지점 체크
            bfs(map, cost, y, x); // 최소 이동이므로 BFS
            writer.write(cost[targetY][targetX] + "\n");
        }
        writer.close();
        reader.close();
    }

    private static void bfs(int[][] map, int[][] cost, int y, int x) {
        // 나이트 이동 정보
        int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};

        // x, y 좌표 모두 큐 이용
        ArrayDeque<Integer> xQueue = new ArrayDeque<>();
        ArrayDeque<Integer> yQueue = new ArrayDeque<>();
        yQueue.addLast(y);
        xQueue.addLast(x);

        // BFS
        while (!yQueue.isEmpty() && !xQueue.isEmpty()) {
            int currY = yQueue.pollFirst();
            int currX = xQueue.pollFirst();
            if (map[currY][currX] == 1) {
                break;
            }
            for (int i = 0; i < 8; i++) {
                int ny = currY + dy[i];
                int nx = currX + dx[i];
                // 범위 계산, 비용 이미 있으면 스킵 (방문 처리)
                if (ny < 0 || nx < 0 || ny >= map.length || nx >= map.length || cost[ny][nx] != 0) {
                    continue;
                }
                cost[ny][nx] = cost[currY][currX] + 1;
                yQueue.addLast(ny);
                xQueue.addLast(nx);
            }
        }
    }
}
