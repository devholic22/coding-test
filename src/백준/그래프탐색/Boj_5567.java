package 백준.그래프탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_5567 {

    private static ArrayList<Integer>[] graph;
    private static int[] costs;
    private static boolean[] visited;

    /*
    SOLVED: 24.01.22 (월)
    결혼식 - 실버2
    무난했던 문제
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 2 <= n <= 500
        costs = new int[N + 1];
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(reader.readLine()); // 1 <= m <= 10000
        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        bfs();
        int result = 0;
        for (int i = 2; i <= N; i++) {
            // 친구: 1, 친구의 친구: 2
            if (visited[i] && costs[i] <= 2) {
                result++;
            }
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static void bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1); // 1 고정
        visited[1] = true;
        while (!queue.isEmpty()) {
            int target = queue.pollFirst();
            for (int other : graph[target]) {
                if (visited[other]) {
                    continue;
                }
                visited[other] = true;
                costs[other] = costs[target] + 1;
                queue.addLast(other);
            }
        }
    }
}
