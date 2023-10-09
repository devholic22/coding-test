package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class one으로_만들기 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int number;
    private static int[] numbers;
    public static void main(String[] args) throws IOException {
        init();
        number = Integer.parseInt(br.readLine());
        numbers = new int[number + 1];

        numbers[1] = 0;
        if (number >= 2) {
            numbers[2] = 1;
        }
        if (number >= 3) {
            numbers[3] = 1;
        }
        for (int i = 4; i <= number; i++) {
            int test1 = 1 + numbers[i - 1];
            int test2 = 1 + numbers[i - 1];
            int test3 = 1 + numbers[i - 1];
            if (i % 3 == 0) {
                test1 = 1 + numbers[i / 3];
            }
            if (i % 2 == 0) {
                test2 = 1 + numbers[i / 2];
            }
            numbers[i] = Math.min(test1, Math.min(test2, test3));
        }

        bw.write(numbers[number] + "");
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void close() throws IOException {
        br.close();
        bw.close();
    }
}
