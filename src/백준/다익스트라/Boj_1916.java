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

public class Boj_1916 {

    private static ArrayList<Node>[] nodes;
    private static boolean[] visited;
    private static int[] costs;

    private static class Node implements Comparable<Node> {
        private int number;
        private int cost;

        public Node(final int number, final int cost) {
            this.number = number;
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
    최소비용 구하기 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000
        int M = Integer.parseInt(reader.readLine()); // 1 <= M <= 100,000

        nodes = new ArrayList[N + 1]; // 그래프 정보 저장
        // Arrays.fill(nodes, new ArrayList<>()); 주의: 이렇게 하면 전부 다 같은 ArrayList를 공유한다.

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE); // INF로 설정

        visited = new boolean[N + 1]; // 방문 여부

        // 그래프 정보 저장
        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            nodes[start].add(new Node(end, cost));
        }

        // start, end 저장
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(tokenizer.nextToken());
        int end = Integer.parseInt(tokenizer.nextToken());
        costs[start] = 0; // 출발지 비용 0

        dijkstra(start, end);
        writer.write(costs[end] + "");

        writer.close();
        reader.close();
    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        visited[start] = true; // 시작 지점은 자신의 비용이 0이고 방문 완료되었다.
        while (!queue.isEmpty()) {
            Node next = queue.poll();
            visited[next.number] = true; // 꺼낼 때 방문 처리한다.
            if (next.number == end) { // 메모리 초과 원인 1: end를 방문 완료 했음에도 종료시키지 않았었다.
                return;
            }
            for (Node node : nodes[next.number]) {
                if (visited[node.number]) {
                    continue;
                }
                // 메모리 초과 원인 2: 항상 노드를 넣었다.
                if (costs[node.number] > node.cost + next.cost) {
                    costs[node.number] = node.cost + next.cost;
                    node.setCost(costs[node.number]); // 메모리 초과 원인 3: setter를 쓰지 않고 새로운 노드를 만들어 큐에 넣었다.
                    queue.add(node);
                }
            }
        }
    }
}
