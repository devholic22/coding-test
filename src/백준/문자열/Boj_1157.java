package 백준.문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Boj_1157 {

    /*
    SOLVED: 23.12.18 (월)
    단어 공부 - 브론즈1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String message = reader.readLine();
        String[] tokens = message.toLowerCase().split(""); // 메시지 토큰화
        HashMap<String, Integer> words = new HashMap<>(); // 맵 생성
        int max = 0;
        int count = 0; // max개인 게 몇 개인지

        // 정보 저장
        for (String token : tokens) {
            if (words.containsKey(token)) {
                words.put(token, words.get(token) + 1);
                continue;
            }
            words.put(token, 1);
        }

        // max 계산
        for (String word : words.keySet()) {
            if (words.get(word) > max) {
                max = Math.max(words.get(word), max);
            }
        }

        // count 계산
        for (String word : words.keySet()) {
            if (words.get(word) == max) {
                count++;
                if (count > 1) {
                    writer.write("?");
                    break;
                }
            }
        }

        // 두 개 이상인 경우 종료
        if (count > 1) {
            writer.close();
            reader.close();
            return;
        }

        // max개인 것 출력
        for (String word : words.keySet()) {
            if (words.get(word) == max) {
                writer.write(word.toUpperCase());
                break;
            }
        }

        writer.close();
        reader.close();
    }
}
