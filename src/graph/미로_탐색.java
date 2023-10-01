package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 미로_탐색 {

    private static final int YES = 1;
    private static final int NO = 0;
    private static final int DIRECTION = 4;

    private static class Position {
        private final int y;
        private final int x;
        private int cost;

        public Position(final int y, final int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getCost() {
            return cost;
        }
    }

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int MAX_Y;
    private static int MAX_X;
    private static int[][] map;
    private static int[][] answer;
    private static boolean[][] visited;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();

        StringTokenizer st = new StringTokenizer(br.readLine());

        MAX_Y = Integer.parseInt(st.nextToken());
        MAX_X = Integer.parseInt(st.nextToken());
        map = new int[MAX_Y][MAX_X];
        answer = new int[MAX_Y][MAX_X];
        visited = new boolean[MAX_Y][MAX_X];

        for (int i = 0; i < MAX_Y; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < MAX_X; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }

        bfs(0, 0);

        bw.write((answer[MAX_Y - 1][MAX_X - 1] + 1) + "");
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void bfs(int y, int x) {
        ArrayDeque<Position> queue = new ArrayDeque<>();
        queue.addLast(new Position(y, x, 0));
        visited[y][x] = true;
        answer[y][x] = 0;
        while (!queue.isEmpty()) {
            Position position = queue.pollFirst();
            for (int direction = 0; direction < DIRECTION; direction++) {
                int ny = dy[direction] + position.getY();
                int nx = dx[direction] + position.getX();
                if (isNotHavePositionError(ny, nx) && isCanMove(ny, nx) && !visited[ny][nx]) {
                    queue.addLast(new Position(ny, nx, position.getCost() + 1));
                    visited[ny][nx] = true;
                    answer[ny][nx] = position.getCost() + 1;
                }
            }
        }
    }

    private static boolean isNotHavePositionError(int y, int x) {
        return y >= 0 && y < MAX_Y  && x >= 0 && x < MAX_X;
    }

    private static boolean isCanMove(int y, int x) {
        return map[y][x] == YES;
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
