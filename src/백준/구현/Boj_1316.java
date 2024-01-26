package 백준.구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Boj_1316 {

    /*
    SOLVED: 24.01.27 (금)
    그룹 단어 체커 - 실버5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100
        int result = 0;
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split("");
            ArrayDeque<String> stack = new ArrayDeque<>();
            if (logic(input, stack)) {
                result++;
            }
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static boolean logic(final String[] input, final ArrayDeque<String> stack) {
        HashMap<String, Boolean> used = new HashMap<>();

        for (String s : input) {
            if (!used.containsKey(s)) {
                used.put(s, false);
            }
            if (stack.isEmpty()) {
                stack.addLast(s);
                used.put(s, true);
                continue;
            }
            if (used.get(s) && !stack.peekLast().equals(s)) {
                return false;
            }
            stack.addLast(s);
            used.put(s, true);
        }
        return true;
    }
}
