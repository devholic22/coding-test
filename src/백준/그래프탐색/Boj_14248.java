package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_14248 {

    private static boolean[] visited;
    private static Stone[] stones;
    private static class Stone {

        private int index;
        private int value;

        public Stone(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
    }

    /*
    SOLVED: 24.01.07 (일)
    점프 점프 - 실버2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        stones = new Stone[N + 1];
        visited = new boolean[N + 1];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        // 돌 정보 저장
        for (int i = 1; i <= N; i++) {
            stones[i] = new Stone(i, Integer.parseInt(tokenizer.nextToken()));
        }

        int position = Integer.parseInt(reader.readLine());
        logic(position);
        int count = 0;
        // 건너게 된 징검다리만 계산 후 출력
        for (boolean isVisit : visited) {
            if (isVisit) {
                count++;
            }
        }

        writer.write(count + "");
        writer.close();
        reader.close();
    }

    private static void logic(int index) {
        // 범위가 잘못되었거나 이미 방문한 징검다리면 X
        if (index <= 0 || index >= stones.length || visited[index]) {
            return;
        }
        visited[index] = true;

        // 생각을 잘못했던 점: 이동할 때 목표 지점 사이에 있는 징검다리들을 건널 수 있는 줄 알았다.
        // 그러나 시작점에서 목표 지점으로 가는 것 하나밖에 없어야 한다.
        /*
        if (index + stones[index].value < stones.length) {
            for (int i = index; i < index + stones[index].value; i++) {
                if (i >= stones.length) {
                    continue;
                }
                visited[i] = true;
            }
        }
        if (index - stones[index].value > 0) {
            for (int i = index; i > index - stones[index].value; i--) {
                if (i <= 0) {
                    continue;
                }
                visited[i] = true;
            }
        }
         */
        logic(index + stones[index].value);
        logic(index - stones[index].value);
    }
}
