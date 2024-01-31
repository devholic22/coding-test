package 백준.그리디;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_9655 {

    /*
    SOLVED: 24.01.31 (수)
    돌 게임 - 실버5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000
        int[] stones = new int[1000 + 1];
        for (int i = 1; i <= 1000; i++) {
            if (i == 1 || i == 3) {
                continue;
            }
            stones[i] = stones[i - 1] + 1; // 기본적으로는 1을 고를 수 있으므로 자신 전 단계 + 1을 한다.
            if (i % 3 == 0) { // 3으로 나눠 떨어지는 경우는 3을 선택할 수 있기 때문에 비교를 다시 한다.
                stones[i] = Math.min(stones[i], stones[i - 3] + 1);
            }
        }
        // 주고받는 걸 계산했을 때, 짝수로 떨어지면 처음 호출한 사람 (상근)으로 되돌아온다.
        if (stones[N] % 2 == 0) {
            writer.write("SK");
        } else {
            // 홀수로 떨어지면 창영이다.
            writer.write("CY");
        }
        writer.close();
        reader.close();
    }
}
