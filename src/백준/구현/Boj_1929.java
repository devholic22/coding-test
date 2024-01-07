package 백준.구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1929 {

    /*
    SOLVED: 24.01.07 (일)
    소수 구하기 - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        // 1 <= start <= end <= 1,000,000
        int start = Integer.parseInt(tokenizer.nextToken());
        int end = Integer.parseInt(tokenizer.nextToken());

        for (int i = start; i <= end; i++) {
            // 반례: 1일 경우 넘겨야 함
            if (i == 1) {
                continue;
            }
            if (isPrime(i)) {
                writer.write(i + "\n");
            }
        }
        writer.close();
        reader.close();
    }

    // 어떤 수가 소수인지 파악하기 위해서는 2 ~ 수의 제곱근 까지만 돌려도 충분하다.
    private static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
