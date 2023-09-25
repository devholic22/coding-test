package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class one_two_three_더하기_4 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        init();
        int T = Integer.parseInt(br.readLine());

        numbers = new int[10001];
        for (int i = 0; i <= 3; i++) {
            numbers[i] = i;
        }

        for (int i = 0; i < T; i++) {
            int number = Integer.parseInt(br.readLine());
            bw.write(logic(number) + "\n");
        }
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static int logic(int number) {
        if (number <= 3)
            return numbers[number];
        for (int i = 4; i <= number; i++) {
            numbers[i] = (numbers[i - 1] + numbers[i - 2]) - numbers[i - 3];
            if ((i - 3) % 3 == 0) {
                numbers[i] += 1;
            }
        }
        return numbers[number];
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
