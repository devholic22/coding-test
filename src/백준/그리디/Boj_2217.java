package 백준.그리디;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj_2217 {

    /*
    SOLVED: 24.01.13 (토)
    로프 - 실버4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100,000
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(ropes); // 정렬 (오름차순 정렬되어 있기 때문에 for문을 반대로 진행)
        int result = 0; // 결과
        // 생각보다 간단한 것이었는데 지레 자신감이 없었어서 못 풀고 있었다.
        // 선택할 수 있는 최대치는 결국 현재 최솟값이 커버할 수 있어야 한다.
        // 그렇기에 최솟값 * 로프의 갯수로 하면 된다.
        for (int i = N - 1; i >= 0; i--) {
            int select = N - i;
            result = Math.max(result, ropes[i] * select);
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
