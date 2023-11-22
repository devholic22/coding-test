package mst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소_스패닝_트리 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static boolean[] visited;
    private static HashMap<Integer, ArrayList<Edge>> map;
    private static PriorityQueue<Edge> queue;
    private static int total;

    private static class Edge implements Comparable<Edge> {

        private final int next;
        private final int cost;

        public Edge(final int next, final int cost) {
            this.next = next;
            this.cost = cost;
        }

        public int getNext() {
            return next;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(final Edge other) {
            return cost - other.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int vertex = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        visited = new boolean[vertex + 1];
        map = new HashMap<>();
        queue = new PriorityQueue<>();
        total = 0;
        int start = 0;

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(st.nextToken());
            if (start == 0) {
                start = from;
            }
            int next = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            ArrayList<Edge> fromEdge = map.get(from);
            fromEdge.add(new Edge(next, cost));

            if (!map.containsKey(next)) {
                map.put(next, new ArrayList<>());
            }
            ArrayList<Edge> nextEdge = map.get(next);
            nextEdge.add(new Edge(from, cost));
        }

        prim(start);
        writer.write(total + "");
        close();
    }

    public static void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void prim(final int vertex) {
        queue.add(new Edge(vertex, 0));
        while (!queue.isEmpty()) {
            Edge pollEdge = queue.poll();
            if (visited[pollEdge.getNext()]) {
                continue;
            }
            visited[pollEdge.getNext()] = true;
            total += pollEdge.getCost();
            for (Edge edge : map.get(pollEdge.getNext())) {
                if (!visited[edge.getNext()]) {
                    queue.add(edge);
                }
            }
        }
    }

    public static void close() throws IOException {
        reader.close();
        writer.close();
    }
}
