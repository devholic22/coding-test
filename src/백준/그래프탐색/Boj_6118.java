package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_6118 {

    private static ArrayList<Integer>[] graph;
    private static int[] costs;
    private static boolean[] visited;

    /*
    SOLVED: 24.01.19 (금)
    숨바꼭질 - 실버1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 2 <= N <= 20,000
        int M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 50,000

        graph = new ArrayList[N + 1]; // 굳이 Node 클래스 만들 필요 없음
        costs = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        bfs(); // 1로 고정되어 있기 때문에 굳이 인자 전달하지 않았음

        int costResultValue = 0; // 헛간까지의 거리
        int resultNumber = 0; // 숨어야 하는 헛간 번호
        int resultCount = 0; // 헛간과 같은 거리를 갖는 헛간 개수

        for (int cost : costs) {
            costResultValue = Math.max(cost, costResultValue); // 가장 멀리 떨어진 지점
        }

        for (int i = 2; i <= N; i++) {
            if (costs[i] == costResultValue) {
                resultCount++; // 같을 경우 헛간 개수 증가
                if (resultNumber == 0) {
                    resultNumber = i; // i가 오름차순이므로 가장 먼저 발견한 것만 해주면 됨
                }
            }
        }

        writer.write(resultNumber + " " + costResultValue + " " + resultCount); // 문제 정의에 맞게 출력
        writer.close();
        reader.close();
    }

    private static void bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int target = queue.pollFirst();
            for (int node : graph[target]) {
                if (visited[node]) {
                    continue;
                }
                visited[node] = true; // BFS와 다익스트라의 visit 처리 위치 주의...
                costs[node] += (costs[target] + 1);
                queue.addLast(node);
            }
        }
    }
}
