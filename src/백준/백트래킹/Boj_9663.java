package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_9663 {

    private static int N;
    private static int count;
    private static int[] row;

    /*
    SOLVED: 24.02.15 (목)
    N-Queen - 골드4
    https://st-lab.tistory.com/118 참고
    "행[행 인덱스] = 열 값"으로 2차원 배열을 1차원으로 바꾸는 것을 연습해봐야 할 듯 하다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        row = new int[N]; // row[rowIndex]의 값을 특정 col로 설정한다고 가정
        count = 0;
        backtracking(0);
        writer.write(count + "");
        writer.close();
        reader.close();
    }

    private static void backtracking(int depth) {
        if (depth == N) {
            count++;
            return;
        }
        for (int col = 0; col < N; col++) {
            row[depth] = col;
            if (can(depth)) {
                backtracking(depth + 1);
            }
        }
    }

    private static boolean can(int rowIndex) {
        for (int i = 0; i < rowIndex; i++) {
            if (row[i] == row[rowIndex]) {
                return false;
         // } else if (Math.abs(rowIndex - row[rowIndex]) == Math.abs(i - row[i])) {
            } else if (Math.abs(rowIndex - i) == Math.abs(row[rowIndex] - row[i])) {
                return false;
            }
        }
        return true;
    }

    /*
    시도했던 방법
    사고의 확장을 못했다. dx dy 설정하고 돌리는..
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;

    public class Main {

        private static boolean[][] used;
        private static boolean[][] queen;
        private static boolean[] row;
        private static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        private static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        private static int N;
        private static int result;

        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

            result = 0;
            long divide = 1;
            N = Integer.parseInt(reader.readLine()); // 1 <= N < 15
            used = new boolean[N][N];
            queen = new boolean[N][N];
            row = new boolean[N];

            for (int i = 1; i <= N; i++) {
                divide *= i;
            }
            backtracking(0);
            writer.write((result / divide) + "");
            writer.close();
            reader.close();
        }

        private static void backtracking(int depth) {
            if (depth == N) {
                int count = 0;
                for (int i = 0; i < N; i++) {
                    if (row[i]) {
                        count++;
                    }
                }
                if (count != N) {
                    return;
                }
                result++;
                return;
            }
            for (int i = 0; i < N; i++) {
                if (row[i]) {
                    continue;
                }
                for (int j = 0; j < N; j++) {
                    if (used[i][j] || queen[i][j] || !validate(i, j)) {
                        continue;
                    }
                    row[i] = true;
                    used[i][j] = true;
                    queen[i][j] = true;
                    setQueenEffect(i, j);
                    backtracking(depth + 1);
                    releaseQueenEffect(i, j);
                    queen[i][j] = false;
                    used[i][j] = false;
                    row[i] = false;
                }
            }
        }

        private static boolean validate(int y, int x) {
            for (int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                while (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    if (queen[ny][nx]) {
                        return false;
                    }
                    ny += dy[i];
                    nx += dx[i];
                }
            }
            return true;
        }

        private static void setQueenEffect(int y, int x) {
            for (int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                while (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    used[ny][nx] = true;
                    ny += dy[i];
                    nx += dx[i];
                }
            }
        }

        private static void releaseQueenEffect(int y, int x) {
            for (int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                while (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    if (queen[ny][nx]) {
                        continue;
                    }
                    used[ny][nx] = false;
                    ny += dy[i];
                    nx += dx[i];
                }
            }
        }
    }
     */
}
