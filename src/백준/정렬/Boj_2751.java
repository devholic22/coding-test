package 백준.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj_2751 {

    /*
    SOLVED: 23.12.19 (화)
    수 정렬하기2 - 실버5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: 1 <= N <= 1,000,000 (즉, O(NlogN 까지만 가능))
        int N = Integer.parseInt(reader.readLine());
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(numbers); // 오름차순 정렬

        for (int number : numbers) {
            writer.write(number + "\n");
        }

        writer.close();
        reader.close();
    }
}
