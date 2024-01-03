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

public class Boj_1753 {

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
    최단 경로 - 골드4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int V = Integer.parseInt(tokenizer.nextToken()); // 1 <= V <= 20,000
        int E = Integer.parseInt(tokenizer.nextToken()); // 1 <= E <= 300,000
        int target = Integer.parseInt(reader.readLine());
        visited = new boolean[V + 1];
        costs = new int[V + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        // 방향 그래프
        // O(E)
        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph[start].add(new Node(end, cost));
        }

        // 전체 = 20,000 * 1,290,308 = 25,806,160,000 (시간 초과)
        /*
        for (int i = 1; i <= V; i++) { // O(V): 20,000
            if (i != target && !visited[i] && costs[i] == Integer.MAX_VALUE) {
                dijkstra(target, i); // O(ElogV) = 300,000 * log(20,000) = 1,290,308
            }
        }
        */

        dijkstra(target, V); // 다익스트라 한 번만 해도 된다!

        // O(V)
        for (int i = 1; i <= V; i++) {
            if (i == target) {
                writer.write("0\n");
            } else if (costs[i] == Integer.MAX_VALUE) {
                writer.write("INF\n");
            } else {
                writer.write(costs[i] + "\n");
            }
        }
        writer.close();
        reader.close();
    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node target = queue.poll();
            visited[target.index] = true;
            /*
            택배 배송, 최소 비용 구하기 등과 같이 단순 start - end 간 거리를 구할 때는 이걸 써서 시간 단축을 할 수 있었으나,
            이 문제 같은 경우에는 전부 우선순위 큐에 넣고 최종적으로 정리될 때 까지 기다려야 하므로 이것을 쓰면 틀린다.
            문제의 목적에 따라 사용 여부를 결정해야 한다.
            if (target.index == end) {
                return;
            }
             */
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
