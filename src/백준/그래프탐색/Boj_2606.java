package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_2606 {

    private static ArrayList<Integer>[] graph; // 비용 관련된 게 없으므로 Integer로 해도 충분
    private static boolean[] used;

    /*
    SOLVED: 24.01.17 (수)
    바이러스 - 실버3
    기본적인 그래프 문제!
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100
        used = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int pairs = Integer.parseInt(reader.readLine());
        for (int i = 0; i < pairs; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        dfs(1); // 문제 요구상 1에서 시작하므로

        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (used[i]) { // 사용이 되었다 = 바이러스 걸렸다
                count++;
            }
        }

        writer.write(count + "");
        writer.close();
        reader.close();
    }

    private static void dfs(final int start) {
        used[start] = true;
        for (int index : graph[start]) {
            if (used[index]) {
                continue;
            }
            dfs(index);
        }
    }
}
