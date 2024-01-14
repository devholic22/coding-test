package 백준.그리디;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11399 {

    /*
    SOLVED: 24.01.14 (일)
    ATM - 실버4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000
        int[] persons = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            persons[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(persons);

        // 가장 적게 걸리는 것 부터 선택하면 된다.
        int sum = 0;
        int result = 0;
        for (int person : persons) {
            sum += person;
            result += sum;
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
