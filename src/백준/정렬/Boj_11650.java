package 백준.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11650 {

    /*
    SOLVED: 23.12.19 (화)
    좌표 정렬하기 - 실버5
     */
    private static class Position {

        int x;
        int y;

        public Position(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 100,000 -> O(NlogN)
        Position[] positions = new Position[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            positions[i] = new Position(x, y);
        }

        Arrays.sort(positions, (p1, p2) -> {
            if (p1.x == p2.x) {
                return Integer.compare(p1.y, p2.y); // -1: p1 < p2, 0: p1 == p2, 1: p1 > p2
            }
            return Integer.compare(p1.x, p2.x); // x 기준으로 오름차순 정렬
        });

        // Object 배열 정렬 시에는 Tim Sort가 활용된다.
        // Tim Sort는 Merge Sort + Insertion Sort 구조이기 때문에, Stable sort이다.
        // Stable sort란, 이전에 했던 정렬이 유지됨을 뜻한다.

        // 전체적으로 x에 대해 오름차순 정렬을 할 것이므로, x가 같은 케이스를 제외하고 전체적으로는 x 기준 오름차순 정렬
        // x가 같을 경우 y에 대해 오름차순 정렬을 하는데, 이때 기존에 했던 정렬이 흐트러지지 않는다.
        for (Position position : positions) {
            writer.write(position.x + " " + position.y + "\n");
        }

        writer.close();
        reader.close();
    }
}
