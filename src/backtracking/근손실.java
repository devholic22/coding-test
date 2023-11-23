package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 근손실 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static int[] routine;
    private static int[] temp;
    private static int N;
    private static int K;
    private static int answer;
    private static boolean[] used;
    public static void main(String[] args) throws IOException {
        init();
        answer = 0;
        // 코드를 작성하세요
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        routine = new int[N];
        temp = new int[N];
        used = new boolean[N];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            routine[i] = Integer.parseInt(st.nextToken());
        }
        backtracking(0);
        writer.write(answer + "");
        close();
    }

    private static void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void close() throws IOException {
        reader.close();
        writer.close();
    }

    private static void backtracking(int depth) {
        if (depth == N) {
            calculate();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            temp[depth] = routine[i];
            backtracking(depth + 1);
            used[i] = false;
            temp[depth] = 0;
        }
    }

    private static void calculate() {
        int weight = 500;
        for (int i = 0; i < N; i++) {
            if (weight + temp[i] - K < 500) {
                return;
            }
            weight += (temp[i] - K);
        }
        if (weight >= 500) {
            answer++;
        }
    }
}
