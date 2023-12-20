package 백준.스택;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Boj_10828 {

    /*
    SOLVED: 23.12.20 (수)
    스택 - 실버4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // prepare stack
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // N = 1 <= N <= 10,000
        // 아래 반복문의 전체 시간 복잡도는 O(N)
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String[] inputCommand = reader.readLine().split(" ");
            String inputName = inputCommand[0];

            // push - O(1)
            if (inputCommand.length > 1) {
                int value = Integer.parseInt(inputCommand[1]);
                if (inputName.equals("push")) {
                    stack.addLast(value);
                    continue;
                }
            }

            // size - O(1)
            if (inputName.equals("size")) {
                writer.write(stack.size() + "\n");
                continue;
            }

            // top - O(1)
            if (inputName.equals("top")) {
                if (stack.isEmpty()) {
                    writer.write("-1\n");
                } else {
                    writer.write(stack.peekLast() + "\n");
                }
                continue;
            }

            // pop - O(1)
            if (inputName.equals("pop")) {
                if (stack.isEmpty()) {
                    writer.write("-1\n");
                } else {
                    writer.write(stack.pollLast() + "\n");
                }
                continue;
            }

            // empty - O(1)
            if (inputName.equals("empty")) {
                if (stack.isEmpty()) {
                    writer.write("1\n");
                } else {
                    writer.write("0\n");
                }
            }
        }

        writer.close();
        reader.close();
    }
}
