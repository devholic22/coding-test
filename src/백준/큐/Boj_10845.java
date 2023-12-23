package 백준.큐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Boj_10845 {

    /*
    SOLVED: 23.12.23 (토)
    큐 - 실버4
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 10,000 -> O(N^2) 이하

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        // O(N)
        for (int i = 0; i < N; i++) {
            String[] commands = reader.readLine().split(" ");
            if (commands.length == 2) {
                // 무조건 push
                queue.addLast(Integer.parseInt(commands[1])); // O(1)
                continue;
            }
            if (commands[0].equals("pop")) {
                if (queue.isEmpty()) { // O(1)
                    writer.write("-1\n");
                    continue;
                }
                writer.write(queue.pollFirst() + "\n"); // O(1)
                continue;
            }
            if (commands[0].equals("size")) {
                writer.write(queue.size() + "\n"); // O(1)
                continue;
            }
            if (commands[0].equals("empty")) {
                if (queue.isEmpty()) { // O(1)
                    writer.write("1\n");
                    continue;
                }
                writer.write("0\n");
                continue;
            }
            if (commands[0].equals("front")) {
                if (queue.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                writer.write(queue.peekFirst() + "\n"); // O(1)
                continue;
            }
            if (commands[0].equals("back")) {
                if (queue.isEmpty()) {
                    writer.write("-1\n");
                    continue;
                }
                writer.write(queue.peekLast() + "\n"); // O(1)
            }
        }

        writer.close();
        reader.close();
    }
}
