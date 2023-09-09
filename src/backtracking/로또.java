package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 로또 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        init();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            if (size == 0)
                break;
            int[] numbers = new int[size + 1];
            boolean[] visited = new boolean[size + 1];
            int[] temp = new int[6 + 1];
            for (int i = 1; i <= size; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            backtracking(1, visited, numbers, temp);
            bw.write("\n");
        }
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void backtracking(int depth, boolean[] visited, int[] numbers, int[] temp) throws IOException {
        if (depth == 7) {
            print(temp);
            return;
        }
        for (int i = 1; i < numbers.length; i++) {
            if (visited[i] || temp[depth - 1] > numbers[i])
                continue;
            visited[i] = true;
            temp[depth] = numbers[i];
            backtracking(depth + 1, visited, numbers, temp);
            temp[depth] = 0;
            visited[i] = false;
        }
    }

    private static void print(int[] numbers) throws IOException {
        for (int i = 1; i < 7; i++) {
            bw.write(numbers[i] + " ");
        }
        bw.write("\n");
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
