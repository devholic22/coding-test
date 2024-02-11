package 프로그래머스.lv2;

import java.util.HashMap;

public class 영어_끝말잇기 {

    /*
    SOLVED: 24.02.11 (일)
    영어 끝말잇기 - Lv2
     */
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;
        int[] person = new int[n + 1];
        HashMap<String, Boolean> used = new HashMap<>();
        for (String word : words) {
            used.put(word, false);
        }
        int index = 0;
        while (index < words.length) {
            for (int j = 1; j <= n; j++) {
                person[j]++;
                if (index >= words.length) {
                    break;
                }
                if (used.get(words[index]) == false) {
                    used.put(words[index], true);
                } else {
                    // 조건 1: 한 번만 쓸 수 있음
                    answer[0] = j;
                    answer[1] = person[j];
                    return answer;
                }
                // 조건 2: 끝나는 단어로 시작해야 함
                if (index > 0 && words[index - 1].charAt(words[index - 1].length() - 1) != words[index].charAt(0)) {
                    answer[0] = j;
                    answer[1] = person[j];
                    return answer;
                }
                index++;
            }
        }
        return answer;
    }
}
