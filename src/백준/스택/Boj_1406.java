package 백준.스택;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Boj_1406 {


    /*
    SOLVED: 23.12.26 (화)
    에디터 - 실버2
    https://minhamina.tistory.com/17 참고하여 이중 스택을 활용
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayDeque<String> leftStack = new ArrayDeque<>(); // 커서 왼쪽
        ArrayDeque<String> rightStack = new ArrayDeque<>(); // 커서 오른쪽

        String[] answers = reader.readLine().split(""); // N <= 100,000
        // 초기화 - O(N)
        for (String answer : answers) {
            leftStack.addLast(answer);
        }

        // 가능한 명령어 정보
        String LEFT = "L";
        String RIGHT = "D";
        String DELETE = "B";
        String INSERT = "P";

        int M = Integer.parseInt(reader.readLine()); // 1 <= N <= 500,000
        // O(M)
        for (int i = 0; i < M; i++) {
            String[] command = reader.readLine().split(" ");
            if (command[0].equals(INSERT)) {
                leftStack.addLast(command[1]); // O(1) - 왼쪽에 쌓는다.
                continue;
            }
            if (command[0].equals(LEFT) && !leftStack.isEmpty()) {
                rightStack.addLast(leftStack.pollLast()); // O(1) - 왼쪽이 남아있을 경우에만 맨 위의 값을 삭제하여 오른쪽 값에 넣는다.
                continue;
            }
            if (command[0].equals(RIGHT) && !rightStack.isEmpty()) {
                leftStack.addLast(rightStack.pollLast()); // O(1) - 오른쪽이 남아있을 경우에만 맨 위의 값을 삭제하여 왼쪽 값에 넣는다.
                continue;
            }
            if (command[0].equals(DELETE) && !leftStack.isEmpty()) {
                leftStack.pollLast(); // O(1) - 커서 왼쪽에 있는 값을 삭제하는 것으로 설정했기 때문이다. right에 옮기지 않고 그냥 삭제하면 된다.
            }
        }

        // print
        StringBuilder result = new StringBuilder();
        while (!leftStack.isEmpty()) {
            result.append(leftStack.pollFirst());
            // 완전한 스택 구조는 아니다. (FIFO라 큐 형식이긴 함, "ABC"의 경우 A, B, C로 넣어지고 그렇게 출력되어야 하므로 FIFO 처리)
        }
        while (!rightStack.isEmpty()) {
            result.append(rightStack.pollLast());
            // 오른쪽에 있는 값은 ABCEF에서 ABCDEF로 되었을 경우 각각 F, E가 들어갔으므로 이후 합쳐진다면 ABCD"EF"로 출력되어야 하므로, 맨 뒤에서부터 출력한다.
        }
        writer.write(result.toString());

        writer.close();
        reader.close();
    }
}
