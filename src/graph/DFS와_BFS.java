package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DFS와_BFS {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static int K;
    private static ArrayList<Integer>[] nodes;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 정점의 갯수 N (1 ~ N)
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        // 간선의 갯수 M
        M = Integer.parseInt(st.nextToken());
        // 목적지
        K = Integer.parseInt(st.nextToken());

        // start, end
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            nodes[start].add(end);
            nodes[end].add(start);
        }

        // 처음: DFS
        dfs(K);
        bw.write("\n");
        Arrays.fill(visited, false);

        // 이후: BFS
        bfs();

        bw.close();
        br.close();
    }
    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }
    private static void dfs(int start) throws IOException {
        bw.write(start + " ");
        visited[start] = true;
        int[] neighbors = nodes[start].stream().mapToInt(i -> i).toArray();
        Arrays.sort(neighbors);
        for (int neighbor : neighbors) {
            if (visited[neighbor])
                continue;
            dfs(neighbor);
        }
    }
    private static void bfs() throws IOException {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(K);
        while (!queue.isEmpty()) {
            int target = queue.pollFirst();
            if (visited[target])
                continue;
            visited[target] = true;
            bw.write(target + " ");
            int[] neighbors = nodes[target].stream().mapToInt(i -> i).toArray();
            Arrays.sort(neighbors);
            for (int neighbor : neighbors) {
                queue.addLast(neighbor);
            }
        }
    }
}
