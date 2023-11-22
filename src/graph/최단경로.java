package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static int[] result;
    private static boolean[] visited;
    private static HashMap<Integer, ArrayList<Node>> map;

    private static class Node implements Comparable<Node> {

        private final int next;
        private final int cost;

        public Node(final int next, final int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(final Node other) {
            return cost - other.cost;
        }

        public int getNext() {
            return next;
        }

        public int getCost() {
            return cost;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int vertex = Integer.parseInt(st.nextToken());
        result = new int[vertex + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        visited = new boolean[vertex + 1];
        int edges = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(reader.readLine());
        map = new HashMap<>();

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            if (!map.containsKey(end)) {
                map.put(end, new ArrayList<>());
            }
            ArrayList<Node> fromEdges = map.get(from);
            ArrayList<Node> endEdges = map.get(end);
            fromEdges.add(new Node(end, cost));
            // endEdges.add(new Node(from, cost));
        }
        dijkstra(start);
        for (int i = 1; i <= vertex; i++) {
            if (result[i] == Integer.MAX_VALUE) {
                writer.write("INF\n");
                continue;
            }
            writer.write(result[i] + "\n");
        }
        close();
    }

    public static void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void dijkstra(final int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        result[start] = 0;
        while (!queue.isEmpty()) {
            Node pollNode = queue.poll();
            if (visited[pollNode.next]) {
                continue;
            }
            visited[pollNode.next] = true;
            for (Node node : map.get(pollNode.next)) {
                if (result[node.next] > node.getCost() + pollNode.getCost()) {
                    result[node.next] = node.getCost() + pollNode.getCost();
                    queue.add(new Node(node.next, result[node.next]));
                }
            }
        }
    }

    public static void close() throws IOException {
        reader.close();
        writer.close();
    }
}
