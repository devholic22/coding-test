package 백준.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11651 {

    private static class Position implements Comparable<Position> {

        int x;
        int y;

        public Position(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(final Position other) {
            // 1차 정렬 조건: y좌표 오름차순
            // 2차 정렬 조건: x좌표 오름차순
            if (this.y == other.y) {
                return Integer.compare(this.x, other.x);
            }
            return Integer.compare(this.y, other.y);
        }
    }

    /*
    SOLVED: 23.12.22 (금)
    좌표 정렬하기 2 - 실버5
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100,000 -> O(N^2) 미만 설계
        Position[] positions = new Position[N];

        // O(N)
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            positions[i] = new Position(x, y);
        }

        // O(NlogN)
        Arrays.sort(positions);

        // O(N)
        for (Position position : positions) {
            writer.write(position.x + " " + position.y + "\n");
        }

        writer.close();
        reader.close();
    }
}
