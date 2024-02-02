package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_2805 {

    private static int[] trees;
    private static int M;

    /*
    SOLVED: 24.02.01 (목)
    나무 자르기 - 실버2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 1,000,000
        M = Integer.parseInt(tokenizer.nextToken()); // 1 <= M <= 2,000,000,000

        trees = new int[N];
        long max = Integer.MIN_VALUE;
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(tokenizer.nextToken());
            max = Math.max(max, trees[i]);
        }

        long start = 0;
        long end = max;

        // 핵심 로직
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
        long sum = 0;
        for (int tree : trees) {
            sum += Math.max(tree - mid, 0);
        }
        return sum >= M;
    }
}
