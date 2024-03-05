package 백준.그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj_17220_마약수사대 {

    private static int N; // 1 <= N <= 26
    private static int M; // 1 <= M <= 600
    private static ArrayDeque<String> queue = new ArrayDeque<>();
    private static HashMap<String, Boolean> arrested = new HashMap<>();
    private static HashMap<String, ArrayList<String>> group = new HashMap<>();
    private static HashMap<String, ArrayList<String>> parents = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String start = tokenizer.nextToken();
            String end = tokenizer.nextToken();

            if (!group.containsKey(start)) {
                group.put(start, new ArrayList<>());
            }
            if (!group.containsKey(end)) {
                group.put(end, new ArrayList<>());
            }
            if (!parents.containsKey(start)) {
                parents.put(start, new ArrayList<>());
            }
            if (!parents.containsKey(end)) {
                parents.put(end, new ArrayList<>());
            }

            group.get(start).add(end); // 단방향
            parents.get(end).add(start); // 단방향

            arrested.put(start, false);
            arrested.put(end, false);
        }
        /*
        for (String key : group.keySet()) {
            System.out.println("key = " + key + ", group.get(key) = " + group.get(key));
        }
         */
        tokenizer = new StringTokenizer(reader.readLine());
        int arrestedCount = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < arrestedCount; i++) {
            queue.addLast(tokenizer.nextToken());
        }

        while (!queue.isEmpty()) {
            String person = queue.pollFirst();
            dfs(person);
        }

        int total = 0;
        for (String person : group.keySet()) {
            if (arrested.get(person) || isSource(person)) {
                continue;
            }
            total++;
        }

        writer.write(total + "");
        writer.close();
        reader.close();
    }

    private static void dfs(final String person) {
        arrested.put(person, true);
        ArrayList<String> neighbors = group.get(person); // person을 부모로 하는 자식 neighbor들
        for (String neighbor : neighbors) {
            ArrayList<String> neighborParents = parents.get(neighbor); // neighbor를 자식으로 하는 부모 parents들
            if (isUsableDrug(neighborParents)) {
                continue;
            }
            dfs(neighbor);
        }
    }

    private static boolean isUsableDrug(final ArrayList<String> parents) {
        for (String parent : parents) {
            if (!arrested.get(parent)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSource(final String target) {
        return parents.get(target).isEmpty();
    }
}
