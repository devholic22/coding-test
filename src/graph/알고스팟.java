package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 알고스팟 {

    private static class Node {
        private int y;
        private int x;
        private int cost;
        public Node(final int y, final int x, final int cost) {
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

        public void setCost(final int cost) {
            this.cost = cost;
        }
    }

    private static final int DIRECTION = 4;

    private static final int WALL = 1;
    private static final int ROAD = 0;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int MAX_Y;
    private static int MAX_X;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] answer;

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        MAX_X = Integer.parseInt(st.nextToken());
        MAX_Y = Integer.parseInt(st.nextToken());
        map = new int[MAX_Y][MAX_X];
        answer = new int[MAX_Y][MAX_X];
        visited = new boolean[MAX_Y][MAX_X];
        for (int i = 0; i < MAX_Y; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < MAX_X; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }
        for (int i = 0; i < MAX_Y; i++) {
            Arrays.fill(answer[i], 123456789);
        }
        answer[0][0] = map[0][0];
        bfs(0, 0);
        bw.write(answer[MAX_Y - 1][MAX_X - 1] + "");
        close();
    }

    public static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void bfs(int y, int x) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(new Node(y, x, 0));
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            for (int direction = 0; direction < DIRECTION; direction++) {
                int ny = node.getY() + dy[direction];
                int nx = node.getX() + dx[direction];
                if (ny < 0 || ny >= MAX_Y || nx < 0 || nx >= MAX_X) {
                    continue;
                }
                int cost = map[ny][nx] + node.getCost();
                if (cost < answer[ny][nx]) {
                    answer[ny][nx] = Math.min(answer[ny][nx], cost);
                    queue.addLast(new Node(ny, nx, cost));
                }
            }
        }
    }

    public static void close() throws IOException {
        bw.close();
        br.close();
    }
}
