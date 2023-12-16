package 백준.문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_2744 {

    /*
    SOLVED: 23.12.16 (토)
    대소문자 바꾸기 - 브론즈5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = reader.readLine().split("");
        StringBuilder answer = new StringBuilder("");

        for (String input : inputs) {
            // 만약 대문자와 같다면 소문자를 추가하고, 아니라면 대문자를 추가한다.
            if (input.equals(input.toUpperCase())) {
                answer.append(input.toLowerCase());
                continue;
            }
            answer.append(input.toUpperCase());
        }

        writer.write(answer.toString());
        writer.close();
        reader.close();
    }
}
