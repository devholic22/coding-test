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

public class Boj_5972 {

    private static int N;
    private static int M;
    private static ArrayList<Node>[] graph;
    private static boolean[] visited;
    private static int[] costs;

    private static class Node implements Comparable<Node> {

        private int index;
        private int cost;

        public Node(final int index, final int cost) {
            this.index = index;
            this.cost = cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    /*
    SOLVED: 24.01.03 (수)
    택배 배송 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[N + 1];
        costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            // 단방향이든 양방향이든 문제 없다. 일반적인 다익스트라처럼 하면 된다.
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        dijkstra(1, N);
        writer.write(costs[N] + "");
        writer.close();
        reader.close();
    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        costs[start] = 0;
        while (!queue.isEmpty()) {
            Node target = queue.poll();
            visited[target.index] = true; // 꺼낼 때 visit 처리!
            if (target.index == end) {
                return;
            }
            for (Node node : graph[target.index]) {
                if (visited[node.index]) {
                    continue;
                }
                if (costs[node.index] > target.cost + node.cost) {
                    costs[node.index] = target.cost + node.cost;
                    node.setCost(costs[node.index]); // 메모리 낭비를 줄이기 위해 새로운 Node를 만들기보다는 node에서 직접 cost 변경하도록 설정
                    queue.add(node);
                }
            }
        }
    }
}
