package graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 아기_상어_2 {

    private static final int SHARK = 1;
    private static final int SAFE = 0;
    private static final int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int DIRECTION = 8;
    private static final int INITIAL_VALUE = 0;
    private static int MAX_Y;
    private static int MAX_X;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[][] map;

    private static class Position {
        private int x;
        private int y;
        private int count;

        public Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public int getX() {
            return this.x;
        }
        public int getY() {
            return this.y;
        }
        public int getCount() {
            return this.count;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = Integer.MIN_VALUE; // 결괏값: 최댓값 비교 연산이 계속해서 이뤄지므로 초깃값은 MIN_VALUE

        // MAX_Y, MAX_X 값 입력 받고 맵 정보 만들기
        MAX_Y = Integer.parseInt(st.nextToken());
        MAX_X = Integer.parseInt(st.nextToken());
        map = new int[MAX_Y][MAX_X];
        for (int y = 0; y < MAX_Y; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < MAX_X; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // SAFE 지점일 때 BFS를 실행한다. 여기는 이해 완료.
        for (int y = 0; y < MAX_Y; y++) {
            for (int x = 0; x < MAX_X; x++) {
                if (map[y][x] == SAFE) {
                    answer = Math.max(answer, bfs(y, x));
                    // 자신을 이미 방문했더라도, 자기와 붙어 있는 지점들을 실행해봐야 하므로 visited 조건을 걸지 않는다.
                }
            }
        }

        bw.write(answer + "");
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    /*
    내가 어려워했던 원인을 분석해보면, Position을 따로 만들지 않고 문제를 해결하려고 해서 더 헷갈렸기 때문인 것 같다.
    또한, visited를 활용하지 못 하였다. visited를 선언해도, BFS를 호출하는 for문에서 조건을 거는 식으로 진행했을 것이다. (DFS 처럼)
    너무 오랜만에 BFS를 풀어서 그런 것일 수도 있겠지만, 알고리즘도 편식하지 않고 골고루 문제를 풀어야 한다는 것을 깊이 체감했다.
    참고 블로그: https://tussle.tistory.com/793
     */
    private static int bfs(int y, int x) {
        ArrayDeque<Position> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[MAX_Y][MAX_X]; // visited는 이 곳에서 작성해둔다. 전역으로 설정하지 않는다.
        visited[y][x] = true; // 자신의 방문 지점을 true로 한다.
        queue.addLast(new Position(x, y, 0));
        while (!queue.isEmpty()) {
            Position position = queue.pollFirst();
            // 이웃한 지점 탐색
            for (int direction = 0; direction < DIRECTION; direction++) {
                int ny = DY[direction] + position.getY();
                int nx = DX[direction] + position.getX();
                // 이미 방문이 되었으면 처리된 것으로 판단한다.
                if (!validateY(ny) || !validateX(nx) || visited[ny][nx]) {
                    continue;
                }
                // SAFE 지점일 때만 true로 설정 및 현재 포지션의 count + 1로 설정
                if (map[ny][nx] == SAFE) {
                    visited[ny][nx] = true;
                    queue.addLast(new Position(nx, ny, position.getCount() + 1));
                } else { // SHARK 지점이면 현재 값 + 1만큼의 값을 리턴한다.
                    return position.getCount() + 1;
                }
            }
        }
        return -1;
    }

    // Y 좌표 검증
    private static boolean validateY(int y) {
        return y >= INITIAL_VALUE && y < MAX_Y;
    }

    // X 좌표 검증
    private static boolean validateX(int x) {
        return x >= INITIAL_VALUE && x < MAX_X;
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
