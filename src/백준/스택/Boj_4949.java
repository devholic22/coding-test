package 백준.스택;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Boj_4949 {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static ArrayDeque<String> bracket;

    /*
    SOLVED: 23.12.28 (목)
    균형잡힌 세상 - 실버4
     */
    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] inputs = reader.readLine().split("");
            // 온점으로만 있으면 입력 종료됨
            if (inputs.length == 1 && inputs[0].equals(".")) {
                break;
            }
            bracket = new ArrayDeque<>(); // 괄호 스택
            INNER : for (String input : inputs) {
                if ("[()]".contains(input)) { // 괄호일 시에만 작동하도록 설정
                    // "["를 담은 상태에서 ")"를 만나거나, "("를 담은 상태에서 "]"를 만났다면 바로 for문 나머지 종료 (for문 앞에 이름 붙일 수 있음)
                    boolean innerBreakOne = !bracket.isEmpty() && bracket.peekLast().equals("[") && input.equals(")");
                    boolean innerBreakTwo = !bracket.isEmpty() && bracket.peekLast().equals("(") && input.equals("]");
                    if (innerBreakOne || innerBreakTwo) {
                        break INNER; // while문이 아니라 for문만 break되도록 할 수 있음
                    }

                    // 제대로 짝을 만나고 있다면 삭제되도록 해 줌
                    if (!bracket.isEmpty() && bracket.peekLast().equals("[") && input.equals("]")) {
                        bracket.pollLast();
                        continue;
                    }
                    if (!bracket.isEmpty() && bracket.peekLast().equals("(") && input.equals(")")) {
                        bracket.pollLast();
                        continue;
                    }

                    // 짝을 못 만나거나 나중에 만나면 일단은 쌓이게 됨
                    bracket.addLast(input);
                }
            }

            // 스택 사이즈가 남아있으면 no, 비어있으면 yes
            if (bracket.isEmpty()) {
                writer.write("yes\n");
            } else {
                writer.write("no\n");
            }
        }
        
        writer.close();
        reader.close();
    }
}
