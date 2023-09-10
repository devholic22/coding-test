package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 연결_요소의_개수 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static ArrayList[] nodes;
    private static boolean[] visited;
    private static int result;
    private static int N;

    public static void main(String[] args) throws IOException {

        init();
        result = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<Integer>();
        }

        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            nodes[start].add(end);
            nodes[end].add(start);
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i])
                continue;
            dfs(i);
            result++;
        }

        bw.write(result + "");
        close();
    }

    public static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void dfs(int node) {
        if (visited[node])
            return;
        visited[node] = true;
        ArrayList<Integer> list = nodes[node];
        for (Integer number : list) {
            dfs(number);
        }
    }

    public static void close() throws IOException {
        bw.close();
        br.close();
    }
}
