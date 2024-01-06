package 백준.다익스트라;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_11779 {

    private static boolean[] visited;
    private static int[] costs;
    private static ArrayList<Bus>[] buses;
    private static ArrayList<Integer>[] paths;

    private static class Bus implements Comparable<Bus> {

        private int city;
        private int cost;
        private List<Integer> path;

        public Bus(final int city, final int cost) {
            this.city = city;
            this.cost = cost;
            this.path = new ArrayList<>();
            path.add(city);
        }

        public void addPrevPath(Bus other) {
            this.path.addAll(other.path);
        }

        @Override
        public int compareTo(Bus other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    /*
    SOLVED: 24.01.06 (토)
    최소비용 구하기 2 - 골드3
    다익스트라에서 시간을 더 줄일 수 있는 방법 두 가지를 알게 되어 좋았다.
    https://www.acmicpc.net/board/view/130728 참고
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000
        int M = Integer.parseInt(reader.readLine()); // 1 <= M <= 100,000
        visited = new boolean[N + 1];
        costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        buses = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            buses[i] = new ArrayList<>();
        }

        paths = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            paths[i] = new ArrayList<>();
            paths[i].add(i);
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            buses[start].add(new Bus(end, cost));
        }

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(tokenizer.nextToken());
        int end = Integer.parseInt(tokenizer.nextToken());

        dijkstra(start);
        // System.out.println(Arrays.toString(costs));
        // System.out.println(Arrays.toString(paths));
        writer.write(costs[end] + "\n");
        writer.write(paths[end].size() + "\n");
        // StringBuilder builder = new StringBuilder();
        for (int i = paths[end].size() - 1; i >= 0; i--) {
            writer.write(paths[end].get(i) + " ");
        }
        writer.close();
        reader.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Bus> queue = new PriorityQueue<>();
        queue.add(new Bus(start, 0));
        costs[start] = 0;
        while (!queue.isEmpty()) {
            Bus target = queue.poll();

            if (visited[target.city]) {
                continue;
            }

            // 그간 다익스트라 문제를 풀면서 작성하지 않았던 부분
            // 위의 뜻 (visited[target.city])과 같은 의미
            // https://www.acmicpc.net/board/view/130728
            // if (costs[target.city] < target.cost) {
            //    continue;
            // }

            visited[target.city] = true;
            for (Bus bus : buses[target.city]) {
                if (visited[bus.city]) {
                    continue;
                }
                if (costs[bus.city] > bus.cost + target.cost) {
                    // System.out.println("=====");
                    // System.out.println("갱신 발생: 기존 = " + target.city + " -> " + bus.city + " = " + costs[bus.city]);
                    // System.out.println("이후 = " + target.city + " -> " + bus.city + " = " + (bus.cost + target.cost));
                    costs[bus.city] = bus.cost + target.cost;
                    bus.cost = costs[bus.city];
                    // bus.addPrevPath(target);
                    // System.out.println(bus.city + " 경로 (이전) = " + paths[bus.city]);
                    paths[bus.city] = new ArrayList<>();
                    paths[bus.city].add(bus.city);
                    paths[bus.city].addAll(paths[target.city]);
                    // System.out.println(bus.city + " 경로 (이후) = " + paths[bus.city]);
                    // System.out.println(bus.path);
                    queue.add(bus);
                    // System.out.println("=====");
                }
            }
        }
    }
}
