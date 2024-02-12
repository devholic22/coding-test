package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_4179 {

    private static int row;
    private static int column;
    private static ArrayDeque<Integer> fireX;
    private static ArrayDeque<Integer> fireY;
    private static ArrayDeque<Integer> walkX;
    private static ArrayDeque<Integer> walkY;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static String[][] map;
    private static int[][] fireTimes;
    private static int[][] walkTimes;
    private static BufferedWriter writer;

    /*
    SOLVED: 24.02.12 (월)
    불! - 골드4
    틀렸던 문제
    핵심 원리: 먼저 불의 이동을 기록하고, 사람의 시간이 불보다 클 경우 스킵한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        row = Integer.parseInt(tokenizer.nextToken());
        column = Integer.parseInt(tokenizer.nextToken());

        fireX = new ArrayDeque<>();
        fireY = new ArrayDeque<>();
        walkX = new ArrayDeque<>();
        walkY = new ArrayDeque<>();

        map = new String[row][column];
        fireTimes = new int[row][column];
        walkTimes = new int[row][column];

        for (int i = 0; i < row; i++) {
            Arrays.fill(walkTimes[i], -1);
            Arrays.fill(fireTimes[i], -1);
        }

        for (int i = 0; i < row; i++) {
            String[] row = reader.readLine().split("");
            for (int j = 0; j < column; j++) {
                map[i][j] = row[j];
                if (map[i][j].equals("F")) {
                    fireY.addLast(i);
                    fireX.addLast(j);
                    fireTimes[i][j] = 0;
                } else if (map[i][j].equals("J")) {
                    walkY.addLast(i);
                    walkX.addLast(j);
                    walkTimes[i][j] = 0;
                }
            }
        }

        fire();

        /*
        System.out.println("FIRE");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(fireTimes[i][j] + " ");
            }
            System.out.println();
        }
        */
        if (!walk()) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        /*
        System.out.println("WALK");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(walkTimes[i][j] + " ");
            }
            System.out.println();
        }
         */
        writer.close();
        reader.close();
    }

    private static void fire() {
        while (!fireX.isEmpty() && !fireY.isEmpty()) {
            int currY = fireY.pollFirst();
            int currX = fireX.pollFirst();
            for (int i = 0; i < 4; i++) {
                int ny = currY + dy[i];
                int nx = currX + dx[i];
                if (ny < 0 || ny >= row || nx < 0 || nx >= column || map[ny][nx].equals("#") || fireTimes[ny][nx] >= 0) {
                    continue;
                }
                fireY.addLast(ny);
                fireX.addLast(nx);
                fireTimes[ny][nx] = fireTimes[currY][currX] + 1;
            }
        }
    }

    private static boolean walk() throws IOException {
        while (!walkY.isEmpty() && !walkX.isEmpty()) {
            int currY = walkY.pollFirst();
            int currX = walkX.pollFirst();

            for (int i = 0; i < 4; i++) {
                int ny = currY + dy[i];
                int nx = currX + dx[i];
                if (ny < 0 || ny >= row || nx < 0 || nx >= column) {
                    writer.write((walkTimes[currY][currX] + 1) + "");
                    return true;
                }
                if (map[ny][nx].equals("#") || walkTimes[ny][nx] >= 0) {
                    continue;
                }
                if (fireTimes[ny][nx] != -1 && fireTimes[ny][nx] <= walkTimes[currY][currX] + 1) {
                    continue;
                }
                walkTimes[ny][nx] = walkTimes[currY][currX] + 1;
                walkY.addLast(ny);
                walkX.addLast(nx);
            }
        }
        return false;
    }
}
