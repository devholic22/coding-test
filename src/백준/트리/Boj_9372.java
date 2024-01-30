package 백준.트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_9372 {

    /*
    SOLVED: 24.01.30 (화)
    상근이의 여행 - 실버4
    지금까지 풀었던 백준 문제 중 가장 쉬웠다.
    알고 보니 각각 동일한 가중치를 가진 상황에서는 N - 1로 구할 수 있다고 한다. (최소 스패닝 트리)
    최소 스패닝 트리에 대해 더 공부해야겠다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int nation = Integer.parseInt(tokenizer.nextToken());
            int airplane = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < airplane; j++) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            writer.write(nation - 1 + "\n");
        }
        writer.close();
        reader.close();
    }
}
