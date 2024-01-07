package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj_2178 {

    private static class Position {

        private int y;
        private int x;
        private boolean isNotWall;
        private int cost;

        public Position(final int y, final int x, final int value) {
            this.y = y;
            this.x = x;
            this.isNotWall = (value == 1);
            this.cost = 0;
        }
    }

    private static int width;
    private static int height;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static Position[][] map;

    /*
    SOLVED: 24.01.07 (일)
    미로 탐색 - 실버1
    BFS 연습 문제
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        height = Integer.parseInt(tokenizer.nextToken());
        width = Integer.parseInt(tokenizer.nextToken());
        map = new Position[height][width];

        // 정보 저장
        for (int i = 0; i < height; i++) {
            String[] row = reader.readLine().split("");
            for (int j = 0; j < width; j++) {
                map[i][j] = new Position(i, j, Integer.parseInt(row[j]));
            }
        }

        bfs(0, 0);
        writer.write(map[height - 1][width - 1].cost + "");
        writer.close();
        reader.close();
    }

    private static void bfs(int y, int x) {
        ArrayDeque<Position> queue = new ArrayDeque<>();
        map[y][x].cost = 1; // 문제 요구사항을 충족하기 위함
        queue.add(map[y][x]);
        while (!queue.isEmpty()) {
            Position position = queue.pollFirst();
            for (int i = 0; i < 4; i++) {
                int ny = position.y + dy[i];
                int nx = position.x + dx[i];
                // 좌표가 올바르지 않거나 벽이거나 비용이 0 초과 (즉, 이미 만난 곳)면 패스
                if (ny < 0 || ny >= height || nx < 0 || nx >= width || !map[ny][nx].isNotWall || map[ny][nx].cost > 0) {
                    continue;
                }
                map[ny][nx].cost += (position.cost + 1); // 점점 누적되며 비용이 증가해야 한다.
                queue.addLast(map[ny][nx]); // 참조 관계로 설정하기 위해 new Position 말고 기존 객체를 활용
            }
        }
    }
}
