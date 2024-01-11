package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1260 {

    private static BufferedWriter writer; // 출력
    private static ArrayList<Integer>[] graph; // 정렬 때문에 PriorityQueue를 쓸려 했는데, PriorityQueue는 반 쯤만 정렬된 것임을 알아야 한다. (빼기 전까진)
    private static boolean[] visited; // 방문 배열
    private static int N; // 노드
    private static int M; // 브랜치 수
    private static int V; // 시작 지점

    /*
    SOLVED: 24.01.11 (목)
    DFS와 BFS - 실버2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 1,000
        M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 10,000
        V = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            graph[start].add(end);
            graph[end].add(start); // 양방향 및 정렬 필요함
        }

        dfs(V);
        Arrays.fill(visited, false);
        writer.write("\n");
        bfs(V);

        writer.close();
        reader.close();
    }

    // DFS
    private static void dfs(int input) throws IOException {
        visited[input] = true;
        writer.write(input + " ");

        // 이 과정을 조금 더 간편하게 할 수 있을 것 같다.
        ArrayList<Integer> nodes = graph[input];
        int[] sortNodes = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            sortNodes[i] = nodes.get(i);
        }
        Arrays.sort(sortNodes);

        for (int neighbor : sortNodes) {
            if (visited[neighbor]) {
                continue;
            }
            dfs(neighbor);
        }
    }

    // BFS
    private static void bfs(int input) throws IOException {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(input);
        visited[input] = true;
        while (!queue.isEmpty()) {
            int target = queue.pollFirst();
            writer.write(target + " ");

            // 이 과정을 조금 더 간편하게 할 수 있을 것 같다.
            ArrayList<Integer> nodes = graph[target];
            int[] sortNodes = new int[nodes.size()];
            for (int i = 0; i < nodes.size(); i++) {
                sortNodes[i] = nodes.get(i);
            }
            Arrays.sort(sortNodes);

            for (int neighbor : sortNodes) {
                if (visited[neighbor]) {
                    continue;
                }
                visited[neighbor] = true; // 방문 처리를 어디에서 하는지 명확히 알아야 할 것 같다. 다익스트라는 꺼낼 때, BFS는 넣을 때.. 왜 그런지도 알아야 함
                queue.addLast(neighbor);
            }
        }
    }
}
