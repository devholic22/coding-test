package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_13301 {

    /*
    SOLVED: 24.01.13 (토)
    타일 장식물 - 실버5
    AO 알고리즘 스터디 문제
     */
    public static void main(String[] args) throws IOException {
        // 길이: 1 - 1 - 2 - 3 - 5 - 8 -> width[n] = width[n - 1] + width[n - 2]
        // 둘레: 1 - 1 - 10 - 16 - 26 - 42

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 80
        long[] widths = new long[81];
        long[] totalWidths = new long[81];

        widths[1] = 1;
        widths[2] = 1;
        widths[3] = 2;
        totalWidths[1] = 4; // 처음 틀렸던 이유: totalWidths[1], totalWidths[2]를 1로 설정했었다.
        totalWidths[2] = 6;
        totalWidths[3] = 10;

        for (int i = 4; i <= N; i++) {
            widths[i] = widths[i - 1] + widths[i - 2];
            totalWidths[i] = totalWidths[i - 1] + (widths[i] * 2);
        }

        writer.write(totalWidths[N] + "");
        writer.close();
        reader.close();
    }
}
