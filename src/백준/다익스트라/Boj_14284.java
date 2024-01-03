package 백준.다익스트라;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_14284 {

    private static ArrayList<Node>[] graph;
    private static int[] costs;
    private static boolean[] visited;

    private static class Node implements Comparable<Node> {

        private int index;
        private int cost;

        public Node(final int index, final int cost) {
            this.index = index;
            this.cost = cost;
        }

        public void setCost(final int cost) {
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    /*
    SOLVED: 24.01.03 (수)
    간선 이어가기 2 - 골드5
    택배 배송 문제와 완전히 같다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 2 <= N <= 5,000
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 100,000
        graph = new ArrayList[N + 1];
        costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());

            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        tokenizer = new StringTokenizer(reader.readLine());
        int target = Integer.parseInt(tokenizer.nextToken());
        int goal = Integer.parseInt(tokenizer.nextToken());

        dijkstra(target);
        writer.write(costs[goal] + "");
        writer.close();
        reader.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        costs[start] = 0;
        while (!queue.isEmpty()) {
            Node target = queue.poll();
            visited[target.index] = true;
            for (Node node : graph[target.index]) {
                if (visited[node.index]) {
                    continue;
                }
                if (costs[node.index] > node.cost + target.cost) {
                    costs[node.index] = node.cost + target.cost;
                    node.setCost(costs[node.index]);
                    queue.add(node);
                }
            }
        }
    }
}
