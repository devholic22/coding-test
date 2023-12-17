package 백준.문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Boj_1919 {

    /*
    SOLVED: 23.12.17 (일)
    애너그램 만들기 - 브론즈2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int result = 0;
        // aabbcc
        // xxyybb일 때
        // bb만 공통이다.
        // 즉, 공통인 것만 추출하면 된다.
        HashMap<String, Integer> answerOne = new HashMap<>();
        HashMap<String, Integer> answerTwo = new HashMap<>();
        String[] answerOneTokens = reader.readLine().split("");
        String[] answerTwoTokens = reader.readLine().split("");

        // 첫 번째 입력값에 대한 HashMap을 저장해둔다.
        for (String answer : answerOneTokens) {
            if (answerOne.containsKey(answer)) {
                answerOne.put(answer, answerOne.get(answer) + 1);
                continue;
            }
            answerOne.put(answer, 1);
        }

        // 두 번째 입력값에 대한 HashMap을 저장해둔다.
        for (String answer : answerTwoTokens) {
            if (answerTwo.containsKey(answer)) {
                answerTwo.put(answer, answerTwo.get(answer) + 1);
                continue;
            }
            answerTwo.put(answer, 1);
        }

        // 아예 키로 등록되지 않은 문자열이었다면 그 갯수만큼 더한다.
        for (String answer : answerOne.keySet()) {
            if (!answerTwo.containsKey(answer)) {
                result += answerOne.get(answer);
            }
        }

        // 아예 키로 등록되지 않은 문자열이었다면 그 갯수만큼 더한다.
        for (String answer : answerTwo.keySet()) {
            if (!answerOne.containsKey(answer)) {
                result += answerTwo.get(answer);
                continue;
            }
            // 등록된 키면 그 차의 절댓값만큼 더한다.
            result += Math.abs(answerOne.get(answer) - answerTwo.get(answer));
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
