package 백준.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_11660 {

    private static int[][] sums;

    /*
    SOLVED: 24.01.18 (목)
    구간 합 구하기 5 - 실버1
    누적 합 문제이면서 DP
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 1024
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 100,000

        int[][] numbers = new int[N + 1][N + 1];
        sums = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= N; j++) {
                numbers[i][j] = Integer.parseInt(tokenizer.nextToken());
                sums[i][j] = numbers[i][j] + sums[i][j - 1];
            }
        }

        // System.out.println(Arrays.deepToString(sums));
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            // 문제에서 정의한 x, y와 내가 정의한 x, y가 달랐다. 좌표 문제일 경우 이런 점을 주의하자.
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            writer.write(calculate(y1, x1, y2, x2) + "\n");
        }

        writer.close();
        reader.close();
    }

    private static int calculate(final int y1, final int x1, final int y2, final int x2) {
        int sum = 0;
        for (int i = y1; i <= y2; i++) {
            // System.out.println(sums[i][x2] + " - " + sums[i][x1 - 1] + " = " + (sums[i][x2] - sums[i][x1 - 1]));
            // 줄마다 계산한다.
            // 무조건 x의 범위는 x1 ~ x2이다. 즉, (2, 2, 3, 4)로 각각 (y1, x1, y2, x2)가 들어왔다면 (2, 2) + (2, 3) + (2, 4) + (3, 2)로 되어야지, (3, 2) 말고 (3, 1)이 더해지면 안 된다.
            sum += (sums[i][x2] - sums[i][x1 - 1]);
        }
        return sum;
    }
}
