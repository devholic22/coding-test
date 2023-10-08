package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.List;
import java.util.StringTokenizer;

public class 숨바꼭질 {

    private static class Node {

        private int index;
        private int cost;

        public Node(final int index, final int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static boolean[] visited;
    private static int start;
    private static int target;

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        saveInput(st);
        initVisited();
        bfs(start);
        close();
    }

    private static void initVisited() {
        visited = new boolean[100001];
    }

    private static void saveInput(final StringTokenizer st) {
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void bfs(int start) throws IOException {
        visited[start] = true;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            if (isWantNode(node)) {
                bw.write(node.cost + "\n");
            }

            Node prevNode = new Node(node.index - 1, node.cost + 1);
            Node nextNode = new Node(node.index + 1, node.cost + 1);
            Node doubleNode = new Node(node.index * 2, node.cost + 1);
            List<Node> nodes = List.of(prevNode, nextNode, doubleNode);

            for (Node test : nodes) {
                if (isNotValidNodeLocation(test) || visited[test.index]) {
                    continue;
                }
                queue.addLast(test);
                visited[test.index] = true;
            }
        }
    }

    private static boolean isNotValidNodeLocation(final Node test) {
        return test.index < 0 || test.index >= 100001;
    }

    private static boolean isWantNode(final Node node) {
        return node.index == target;
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
