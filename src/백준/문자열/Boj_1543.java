package 백준.문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1543 {

    /*
    SOLVED: 23.12.18 (월)
    문서 검색 - 실버5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = reader.readLine(); // 입력값
        String findText = reader.readLine(); // 찾고자 하는 값
        int count = 0;
        while (input.contains(findText)) { // 포함할 때 계속 반복
            int index = input.indexOf(findText);
            StringBuilder builder = new StringBuilder();
            String before = input.substring(0, index);
            String changed = ".".repeat(findText.length()); // 완전히 제외시키면 결합되는 과정에서 의도치 않게 findText가 만들어질 수 있음
            String after = input.substring(index + findText.length()); // 하나만 있을 경우 자동으로 끝까지임
            input = builder.append(before).append(changed).append(after).toString();
            count++;
        }
        writer.write(count + "");
        writer.close();
        reader.close();
    }
}
