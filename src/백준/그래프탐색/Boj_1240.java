package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_1240 {

    /*
    SOLVED: 23.12.30 (토)
    노드 사이의 거리 - 골드5
    https://9hyuk9.tistory.com/61 참고
     */
    private static class Node {

        int index;
        int cost;

        public Node(final int index, final int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    private static ArrayList<Node>[] graph;
    private static boolean[] visited;
    private static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 정보 저장
        for (int i = 0; i < N - 1; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        // 출력
        for (int i = 0; i < M; i++) {
            visited = new boolean[N + 1];
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            dfs(start, end, 0);
            writer.write(result + "\n");
        }

        writer.close();
        reader.close();
    }

    private static void dfs(int index, int goal, int distance) {
        if (index == goal) {
            result = distance;
            return;
        }
        visited[index] = true;
        for (Node node : graph[index]) {
            if (!visited[node.index]) {
                dfs(node.index, goal, distance + node.cost);
            }
        }
    }

    /* 틀렸던 로직
    private static int bfs(int index, int goal) {
        int cost = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(index);
        while (!queue.isEmpty()) {
            int target = queue.pollFirst();
            if (visited[target]) {
                continue;
            }
            visited[target] = true;
            for (Node node : graph[target]) {
                if (visited[node.index]) {
                    continue;
                }
                queue.addLast(node.index);
                cost += node.cost;
                if (node.index == goal) {
                    return cost;
                }
            }
        }
        return 0;
    }
     */
}
