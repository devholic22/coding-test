package 백준.트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_11725 {

    private static int[] parents;
    private static boolean[] used;
    private static ArrayList<Integer>[] graph;

    /*
    SOLVED: 24.01.28 (일)
    트리의 부모 찾기 - 실버2
    그냥 그래프 문제로 접근하면 된다. 먼저 호출된 노드가 부모 역할을 하기 때문이다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 2 <= N <= 100,000
        parents = new int[N + 1];
        used = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        dfs(1);
        for (int i = 2; i <= N; i++) {
            writer.write(parents[i] + "\n");
        }
        writer.close();
        reader.close();
    }

    private static void dfs(final int index) {
        used[index] = true;
        for (int node : graph[index]) {
            if (used[node]) {
                continue;
            }
            used[node] = true;
            parents[node] = index;
            dfs(node);
        }
    }
}
