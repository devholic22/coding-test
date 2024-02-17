package 백준.그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_5427_불 {

    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int[][] fireMap;

    /*
    Solved: 24.02.17 (토)
    불 - 골드4
    - ✅ 기본 구조를 떠올렸다. (예전에 풀은 4179 불!과 완전 같은 문제이기에)
    - ❌ 반례를 해결하지 못해 참고하였다.
    - 알게 된 점
        - "동시에" 여러 곳에서 진행되어야 한다면 (ex: 불이 1초 간격으로 여러 곳에서 퍼질 경우 등) 큐에 좌표를 다 담아두고 BFS를 진행한다.
        - "시간 (비용) 비교"를 한다면 각각 따로 BFS를 수행한 뒤 값 비교를 한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int width = Integer.parseInt(tokenizer.nextToken());
            int height = Integer.parseInt(tokenizer.nextToken());
            String[][] map = new String[height][width];
            fireMap = new int[height][width];
            int[][] personMap = new int[height][width];
            for (int j = 0; j < height; j++) {
                Arrays.fill(fireMap[j], -1);
                Arrays.fill(personMap[j], -1);
            }
            ArrayDeque<Integer> fx = new ArrayDeque<>();
            ArrayDeque<Integer> fy = new ArrayDeque<>();
            ArrayDeque<Integer> px = new ArrayDeque<>();
            ArrayDeque<Integer> py = new ArrayDeque<>();
            for (int h = 0; h < height; h++) {
                String[] row = reader.readLine().split("");
                for (int w = 0; w < width; w++) {
                    map[h][w] = row[w];
                    if (map[h][w].equals("*")) {
                        fx.addLast(w);
                        fy.addLast(h);
                        fireMap[h][w] = 0;
                    } else if (map[h][w].equals("@")) {
                        px.addLast(w);
                        py.addLast(h);
                        personMap[h][w] = 0;
                    }
                }
            }

            fire(width, height, map, fx, fy);

            /*
            System.out.println("fire");
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < width; k++) {
                    System.out.print(fireMap[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
            */

            int result = move(width, height, map, personMap, px, py);
            if (result == -1) {
                writer.write("IMPOSSIBLE\n");
            } else {
                writer.write(result + "\n");
            }
            /*
            System.out.println("person");
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < width; k++) {
                    System.out.print(personMap[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
             */
        }
        writer.close();
        reader.close();
    }

    private static void fire(int width, int height, String[][] map, ArrayDeque<Integer> fx, ArrayDeque<Integer> fy) {
        while (!fx.isEmpty() && !fy.isEmpty()) {
            int x = fx.pollFirst();
            int y = fy.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= width || ny < 0 || ny >= height || fireMap[ny][nx] >= 0 || map[ny][nx].equals("#")) {
                    continue;
                }
                fireMap[ny][nx] = fireMap[y][x] + 1;
                fx.addLast(nx);
                fy.addLast(ny);
            }
        }
    }

    private static int move(int width, int height, String[][] map, int[][] personMap, ArrayDeque<Integer> px, ArrayDeque<Integer> py) {
        while (!px.isEmpty() && !py.isEmpty()) {
            int x = px.pollFirst();
            int y = py.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if ((nx < 0 || nx >= width || ny < 0 || ny >= height)) {
                    if (personMap[y][x] < fireMap[y][x] || fireMap[y][x] == -1) {
                        return personMap[y][x] + 1;
                    }
                    continue;
                }
                if (personMap[ny][nx] >= 0 || map[ny][nx].equals("#") || map[ny][nx].equals("*")) {
                    continue;
                }
                personMap[ny][nx] = personMap[y][x] + 1;
                px.addLast(nx);
                py.addLast(ny);
            }
        }
        return -1;
    }
}
