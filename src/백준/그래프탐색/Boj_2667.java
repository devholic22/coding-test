package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Boj_2667 {

    private static int[][] graph;
    private static boolean[][] visited;
    private static int N;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    private static PriorityQueue<Integer> result; // 오름차순 출력 바로 하기 위함

    /*
    SOLVED: 24.01.11 (목)
    단지번호붙이기 - 실버1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        result = new PriorityQueue<>();

        N = Integer.parseInt(reader.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] row = reader.readLine().split("");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(row[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        writer.write(result.size() + "\n");
        while (!result.isEmpty()) {
            writer.write(result.poll() + "\n");
        }
        writer.close();
        reader.close();
    }

    private static void bfs(int y, int x) {
        int count = 1; // 한 번 수행 시 몇 개 구해졌는지 파악하기 위함
        ArrayDeque<Integer> xQueue = new ArrayDeque<>(); // x 전용
        ArrayDeque<Integer> yQueue = new ArrayDeque<>(); // y 전용

        visited[y][x] = true;
        xQueue.addLast(x);
        yQueue.addLast(y);
        while (!xQueue.isEmpty() && !yQueue.isEmpty()) {
            int nowY = yQueue.pollFirst();
            int nowX = xQueue.pollFirst();
            for (int i = 0; i < 4; i++) {
                int ny = nowY + dy[i];
                int nx = nowX + dx[i];
                // 유효성 검증
                if (ny >= N || ny < 0 || nx >= N || nx < 0 || graph[ny][nx] == 0 || visited[ny][nx]) {
                    continue;
                }
                visited[ny][nx] = true;
                count++; // select +1
                yQueue.addLast(ny);
                xQueue.addLast(nx);
            }
        }
        result.add(count); // 결과에 count 저장 (우선순위 큐라 바로 출력 오름차순으로 가능)
    }
}
