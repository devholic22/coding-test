package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1654 {

    private static int[] wires;
    private static int N;

    /*
    SOLVED: 24.02.01 (목)
    랜선 자르기 - 실버2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int K = Integer.parseInt(tokenizer.nextToken()); // 1 <= K <= 10,000
        N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 1,000,000

        wires = new int[K];
        long min = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            wires[i] = Integer.parseInt(reader.readLine());
            min = Math.max(min, wires[i]);
        }

        long start = 1;
        long end = min;

        // 핵심 로직: 왜 이렇게 해야만 하는지는 더 봐야 할 것 같다..
        while (start < end) {
            long mid = (start + end + 1) / 2;
            if (can(mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        writer.write(start + "");
        writer.close();
        reader.close();
    }

    // parametric search 기본 함수
    private static boolean can(final long mid) {
        long count = 0;
        for (int wire : wires) {
            count += (wire / mid);
        }
        return count >= N;
    }
}
