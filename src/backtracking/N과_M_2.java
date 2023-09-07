package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N과_M_2 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static int[] numbers;
    private static int[] temp;
    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boolean[] used = new boolean[N + 1];
        numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = i;
        }
        temp = new int[M + 1];

        backtracking(1, used);

        close();
    }

    private static void init() {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void backtracking(int depth, boolean[] used) throws IOException {
        if (depth == M + 1) {
            print();
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (used[i] || temp[depth - 1] > numbers[i])
                continue;
            used[i] = true;
            temp[depth] = numbers[i];
            backtracking(depth + 1, used);
            temp[depth] = 0;
            used[i] = false;
        }
    }

    private static void print() throws IOException {
        for (int i = 1; i <= M; i++) {
            bw.write(temp[i] + " ");
        }
        bw.write("\n");
    }

    private static void close() throws IOException {

        br.close();
        bw.close();
    }
}
