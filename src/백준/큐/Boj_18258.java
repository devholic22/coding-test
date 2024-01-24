package 백준.큐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj_18258 {

    /*
    SOLVED: 24.01.24 (수)
    큐2 - 실버4
    무난한 큐 문제
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 2,000,000
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            if (tokenizer.countTokens() == 2) { // push
                tokenizer.nextToken();
                int number = Integer.parseInt(tokenizer.nextToken());
                queue.addLast(number);
                continue;
            }
            String command = tokenizer.nextToken();
            if (command.equals("pop")) {
                writer.write((queue.isEmpty() ? -1 : queue.pollFirst()) + "\n");
                continue;
            }
            if (command.equals("size")) {
                writer.write(queue.size() + "\n");
                continue;
            }
            if (command.equals("empty")) {
                writer.write((queue.isEmpty() ? 1 : 0) + "\n");
                continue;
            }
            if (command.equals("front")) {
                writer.write((queue.isEmpty() ? -1 : queue.peekFirst()) + "\n");
                continue;
            }
            writer.write((queue.isEmpty() ? -1 : queue.peekLast()) + "\n");
        }
        writer.close();
        reader.close();
    }
}
