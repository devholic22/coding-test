package 백준.그래프.다익스트라;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1238_파티 {

    private static int N;
    private static int M;
    private static int X;
    private static int result;
    private static ArrayList<Road>[] startsGraph;
    private static ArrayList<Road>[] returnGraph;
    private static boolean[] visited;
    private static int[] starts; // 시작점에서 X로 가는 (출발) 최소 비용
    private static int[] returns; // X에서 시작점으로 돌아오는 (복귀) 최소 비용

    private static class Road implements Comparable<Road> {

        private int end;
        private int cost;

        public Road(final int end, final int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    /*
    Solved: 24.02.20 (화)
    파티 - 골3
    - ❌ 메모리 초과 해결을 하지 못했다.
        - 기존에 시도했던 방법: 1 ~ N까지 각각 다익스트라를 호출했다. 또한, 결과 배열을 2차원으로 만들었다. (N x N, 즉 1000 x 1000)
            - 문제점: 큐를 만들고, new Road가 계속 호출됨으로써 불필요한 메모리를 사용하였다. 때문에 메모리 초과가 일어났다.
        - 해결 방법으로 1️⃣ 플로이드-워셜을 쓰거나, 2️⃣ 방향을 전환하여 (방향 있는 다익스트라의 경우) 다익스트라를 두 번 돌리면 된다.
        - 단, 플로이드-워셜은 시간 복잡도를 고려해야 한다.
    - ❌ 다익스트라의 전반적인 로직을 설계하지 못했다. 복습이 필요하다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 1000
        M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 10000
        X = Integer.parseInt(tokenizer.nextToken()); // 1 <= X <= N
        result = 0;
        startsGraph = new ArrayList[N + 1];
        returnGraph = new ArrayList[N + 1];
        starts = new int[N + 1];
        returns = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            startsGraph[i] = new ArrayList<>();
            returnGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            startsGraph[end].add(new Road(start, cost)); // 방향 전환하여 다익스트라를 두 번만 돌리게 한다.
            returnGraph[start].add(new Road(end, cost));
        }

        visited = new boolean[N + 1];
        Arrays.fill(starts, Integer.MAX_VALUE);
        Arrays.fill(returns, Integer.MAX_VALUE);
        starts[X] = 0;
        returns[X] = 0;
        dijkstra(X, starts, startsGraph);
        Arrays.fill(visited, false);
        dijkstra(X, returns, returnGraph);

        /*
        System.out.println(Arrays.toString(starts));
        System.out.println(Arrays.toString(returns));
         */

        /*
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            times[i] = 0;
            dijkstra(i);

            for (int j = 1; j <= N; j++) {
                temp[i][j] = Math.min(temp[i][j], times[j]);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result = Math.max(result, temp[i][j] + temp[j][i]);
            }
        }
        */

        for (int i = 1; i <= N; i++) {
            result = Math.max(result, starts[i] + returns[i]);
        }
        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static void dijkstra(int start, int[] arr, ArrayList<Road>[] graph) {
        PriorityQueue<Road> queue = new PriorityQueue<>(); // 우선순위 큐를 써야 한다.
        queue.add(new Road(start, 0));
        while (!queue.isEmpty()) {
            Road road = queue.poll();
            visited[road.end] = true; // 다익스트라는 꺼낼 때 방문 처리를 해야 한다. 우선순위 큐에 의해 가장 가까운 것이 뽑아졌을 시점이기 때문이다.
            for (Road other : graph[road.end]) {
                if (visited[other.end]) {
                    continue;
                }

                // 여기가 핵심
                if (arr[other.end] > road.cost + other.cost) {
                    arr[other.end] = road.cost + other.cost;
                }

                queue.add(new Road(other.end, arr[other.end])); // queue.add(other)가 아니다. queue.add(new Road(other.end, arr[other.end]))로 해야 한다. (갱신)
            }
        }
    }
}
