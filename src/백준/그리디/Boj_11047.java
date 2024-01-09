package 백준.그리디;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11047 {

    /*
    SOLVED: 24.01.09 (화)
    동전0 - 실버4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int count = 0; // 답

        int[] coins = new int[N];
        // 저장
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(coins); // 정렬 진행 (최대한 큰 동전부터 이용하도록)

        for (int i = coins.length - 1; i >= 0; i--) {
            // coins[i]를 이용할 수 있을 때만 진행
            if (K >= coins[i]) {
                count += K / coins[i]; // 몫 만큼 count 증가
                K %= coins[i]; // K는 coins[i]로 나눈 나머지로 갱신
            }
        }

        writer.write(count + "");
        writer.close();
        reader.close();
    }
}
