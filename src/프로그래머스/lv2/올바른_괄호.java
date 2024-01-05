package 프로그래머스.lv2;

import java.util.ArrayDeque;

public class 올바른_괄호 {

    /*
    SOLVED: 24.01.06 (토)
    올바른 괄호 - Lv2
    백준이었으면 통과되었을 문제가 시간 초과가 계속 떴다.
    살짝 억지인 것 같긴 한데, 그래도 덕분에 String을 String[]으로 만드는 것 보다 char[]로 하는게 더 빠른 것임을 알았다.
     */
    boolean solution(String s) {

        // 1차 검증: 갯수가 짝수가 아니면 애초에 틀린 경우
        if (s.length() % 2 != 0) {
            return false;
        }

        ArrayDeque<Character> stack = new ArrayDeque<>();
        // 2차 검증: 처음이 닫는 괄호거나 끝이 여는 괄호라면 틀린 경우
        char[] tokens = s.toCharArray();
        if (tokens[0] == ')' || tokens[tokens.length - 1] == '(') {
            return false;
        }

        // 3차 검증: 닫는 괄호, 여는 괄호의 개수가 다르다면 틀린 경우
        int openCount = 0;
        int closeCount = 0;
        for (char token : tokens) {
            if (token == '(') {
                openCount++;
                continue;
            }
            closeCount++;
        }

        if (openCount != closeCount) {
            return false;
        }

        // 4차 검증: 최종적으로 스택 이용하여 판단
        for (char token : tokens) {
            if (token == '(') {
                stack.addLast(token);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (stack.peekLast() == '(') {
                stack.pollLast();
            }
        }
        return stack.isEmpty();
    }
}
