package 백준.그리디;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_28136 {

    /*
    SOLVED: 24.01.13 (토)
    원, 탁! - 실버5
    AO 알고리즘 스터디 문제
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 3 <= N <= 1,000,000
        int[] numbers = new int[N + 1];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        numbers[N] = numbers[0];

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (numbers[i - 1] >= numbers[i]) { // 틀렸던 이유: 오름차순 정렬일 시 같을 때에도 나눠야 했다는 것을 몰랐다.
                count++;
            }
        }

        writer.write(count + "");
        writer.close();
        reader.close();
    }
}
