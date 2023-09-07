package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N과_M_1 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static int[] temp;

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        temp = new int[M + 1];

        boolean[] visited = new boolean[N + 1];

        // 1부터 N까지 자연수 중 중복 없이 M개를 고른 수열
        backtracking(1, visited);
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void backtracking(int select, boolean[] visited) throws IOException {
        // 종결 조건
        if (select > M) {
            print();
            return;
        }

        // 1부터 N까지의 수를 고른다
        for (int i = 1; i <= N; i++) {
            // 이미 선택한 숫자는 건너뛰어야 합니다.
            if (visited[i])
                continue;
            visited[i] = true;
            temp[select] = i;
            backtracking(select + 1, visited);
            // 퇴각 작업
            visited[i] = false;
            temp[select] = 0;
        }
    }

    private static void print() throws IOException {
        for (int i = 1; i <= M; i++) {
            bw.write(temp[i] + " ");
        }
        bw.write("\n");
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}