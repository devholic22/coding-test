package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16918_봄버맨 {

    private static int width;
    private static int height;
    private static int N;
    private static String[][] map;
    private static int[][] time;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        height = Integer.parseInt(tokenizer.nextToken());
        width = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        map = new String[height][width];
        time = new int[height][width];

        for (int i = 0; i < height; i++) {
            String[] row = reader.readLine().split("");
            for (int j = 0; j < width; j++) {
                map[i][j] = row[j];
                if (map[i][j].equals("O")) {
                    time[i][j] = 3;
                }
            }
        }

        minus(); // 1초 후 (기본)

        for (int i = 2; i <= N; i++) {
            minus();
            if (i % 2 == 0) {
                input();
            } else {
                bomb();
            }
            // print();
        }
        print();
        reader.close();
    }

    // 기존 폭탄들 유효 시간 감소
    private static void minus() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (time[i][j] > 0 && map[i][j].equals("O")) {
                    time[i][j]--;
                }
            }
        }
    }

    // 새로 폭탄을 설치
    private static void input() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].equals(".")) {
                    map[i][j] = "O";
                    time[i][j] = 3;
                }
            }
        }
    }

    // 유효 시간 지난 폭탄 터짐 처리
    private static void bomb() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].equals("O") && time[i][j] == 0) {
                    map[i][j] = ".";
                    // 폭탄의 상 하 좌 우 처리
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        // time[ny][nx] == 0인 것 까지 처리하면 기존의 원래 진행해야 할 폭탄이 미리 없어지는 효과가 발생해버림
                        if (ny < 0 || ny >= height || nx < 0 || nx >= width || map[ny][nx].equals(".") || time[ny][nx] == 0) {
                            continue;
                        }
                        // 인접한 폭탄은 폭발 없이 파괴 (연쇄 폭발 X)
                        map[ny][nx] = ".";
                        time[ny][nx] = 0;
                    }
                }
            }
        }
    }

    // 상태 출력
    private static void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        /*
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(time[i][j]);
            }
            System.out.println();
        }
        System.out.println("==========");
         */
    }
}
