package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 바이러스 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static ArrayList<ArrayList<Integer>> graph; // 인접 리스트 방식으로 풀어보기
    private static int computers;
    private static boolean[] visited;
    private static int nodes;
    public static void main(String[] args) throws IOException {
        init();
        computers = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        graph.add(new ArrayList<>()); // 인덱스 활용 시 1번 ~ N번 이용할 수 있도록
        visited = new boolean[computers + 1];
        for (int i = 1; i <= computers; i++) {
            graph.add(new ArrayList<>());
        }
        nodes = Integer.parseInt(br.readLine());
        for (int i = 0; i < nodes; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // 무방향 그래프 - 서로 연결
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        bw.write(bfs(1) + "");
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    // 익숙하지 않은 BFS 방식으로 해 보자
    private static int bfs(int target) {
        int result = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(target);
        visited[target] = true;
        while (!queue.isEmpty()) {
            int number = queue.pollFirst();
            for (int node : graph.get(number)) {
                if (visited[node]) continue;
                visited[node] = true;
                queue.addLast(node);
                result++;
            }
        }
        return result;
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
