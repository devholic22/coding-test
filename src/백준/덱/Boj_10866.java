package 백준.덱;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Boj_10866 {

    /*
    SOLVED: 23.12.24 (일)
    덱 - 실버4
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 10,000 -> O(N^2) 이하
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        // O(N)
        for (int i = 0; i < N; i++) {
            String[] commands = reader.readLine().split(" ");
            if (commands.length == 2 && commands[0].equals("push_front")) {
                deque.addFirst(Integer.parseInt(commands[1])); // O(1)
                continue;
            }

            if (commands.length == 2 && commands[0].equals("push_back")) {
                deque.addLast(Integer.parseInt(commands[1])); // O(1)
                continue;
            }

            if (commands[0].equals("pop_front")) {
                if (deque.isEmpty()) { // O(1)
                    writer.write("-1\n");
                    continue;
                }
                writer.write(deque.pollFirst() + "\n"); // O(1)
                continue;
            }

            if (commands[0].equals("pop_back")) {
                if (deque.isEmpty()) { // O(1)
                    writer.write("-1\n");
                    continue;
                }
                writer.write(deque.pollLast() + "\n"); // O(1)
                continue;
            }

            if (commands[0].equals("size")) {
                writer.write(deque.size() + "\n"); // O(1)
                continue;
            }

            if (commands[0].equals("empty")) {
                if (deque.isEmpty()) { // O(1)
                    writer.write("1\n");
                    continue;
                }
                writer.write("0\n");
                continue;
            }

            if (commands[0].equals("front")) {
                if (deque.isEmpty()) { // O(1)
                    writer.write("-1\n");
                    continue;
                }
                writer.write(deque.peekFirst() + "\n"); // O(1)
                continue;
            }
            if (deque.isEmpty()) {
                writer.write("-1\n");
                continue;
            }
            writer.write(deque.peekLast() + "\n"); // O(1)
        }
        writer.close();
        reader.close();
    }
}
