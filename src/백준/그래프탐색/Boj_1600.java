package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj_1600 {

    private static int K;
    private static int width;
    private static int height;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int[] kY = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static int[] kX = {1, 2, 2, 1, -1, -2, -2, -1};
    private static boolean[][][] visited;
    private static int[][] map;

    private static class Position {
        private int y;
        private int x;
        private int knightCount;
        private int moveCount;

        public Position(final int y, final int x, final int knightCount, final int moveCount) {
            this.y = y;
            this.x = x;
            this.knightCount = knightCount;
            this.moveCount = moveCount;
        }
    }

    /*
    SOLVED: 24.02.13 (화)
    말이 되고픈 원숭이 - 골드3
    3차원 배열을 이용해야 하는 문제
    https://velog.io/@yoonuk/%EB%B0%B1%EC%A4%80-1600-%EB%A7%90%EC%9D%B4-%EB%90%98%EA%B3%A0%ED%94%88-%EC%9B%90%EC%88%AD%EC%9D%B4Java%EC%9E%90%EB%B0%94 참고
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        width = Integer.parseInt(tokenizer.nextToken());
        height = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[height][width][K + 1];
        map = new int[height][width];

        for (int i = 0; i < height; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        int answer = bfs(0, 0);
        writer.write(answer + "");
        /*
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < K + 1; k++) {
                    System.out.print(visited[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println("=====");
        }
         */
        writer.close();
        reader.close();
    }

    private static int bfs(int y, int x) {
        ArrayDeque<Position> queue = new ArrayDeque<>();
        queue.addLast(new Position(y, x, 0, 0));
        visited[y][x][0] = true;
        while (!queue.isEmpty()) {
            Position p = queue.pollFirst();
            int currX = p.x;
            int currY = p.y;
            if (currX == width - 1 && currY == height - 1) {
                return p.moveCount;
            }
            for (int i = 0; i < 4; i++) {
                int ny = currY + dy[i];
                int nx = currX + dx[i];

                if (nx < 0 || ny < 0 || nx >= width || ny >= height || visited[ny][nx][p.knightCount] || map[ny][nx] == 1) {
                    continue;
                }
                visited[ny][nx][p.knightCount] = true;
                queue.addLast(new Position(ny, nx, p.knightCount, p.moveCount + 1));
            }
            if (p.knightCount < K) {
                for (int i = 0; i < 8; i++) {
                    int ny = currY + kY[i];
                    int nx = currX + kX[i];

                    if (nx < 0 || ny < 0 || nx >= width || ny >= height || visited[ny][nx][p.knightCount + 1] || map[ny][nx] == 1) {
                        continue;
                    }
                    visited[ny][nx][p.knightCount + 1] = true;
                    queue.addLast(new Position(ny, nx, p.knightCount + 1, p.moveCount + 1));
                }
            }
        }
        return -1;
    }
}
