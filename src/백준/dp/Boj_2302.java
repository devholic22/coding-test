package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Boj_2302 {

    /*
    SOLVED: 24.01.22 (월)
    극장 좌석 - 실버1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 40
        int M = Integer.parseInt(reader.readLine()); // 0 <= M <= N

        int[] possibles = new int[41]; // 어떤 경우에도 구할 수 있기 위해 미리 사이즈 최대로 설정
        // 점화식은 어렵지 않게 구할 수 있었음 (핵심이긴 함)
        for (int i = 1; i <= 40; i++) {
            if (i <= 2) {
                possibles[i] = i;
                continue;
            }
            possibles[i] = possibles[i - 1] + possibles[i - 2];
        }

        // System.out.println(Arrays.toString(possibles));
        // VIP 정보는 ArrayDeque로 관리한다.
        // 시작 인덱스 ~ VIP 인덱스 전까지의 영역 중 발생할 수 있는 갯수를 곱해나가는 방식이다.
        // 예: 1 2 3 [4] 5 6 [7] 8 9라면,
            // (1, 2, 3) 가능 경우의 수 = possibles[4 - 1] = 3
            // (5, 6) 가능 경우의 수 = possibles[7 - 5] = 2
            // (8, 9) 가능 경우의 수 = possibles[10 - 8] = 2
            // 따라서 1 x 3 x 2 x 2 = 12
        ArrayDeque<Integer> vip = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            int number = Integer.parseInt(reader.readLine());
            vip.addLast(number);
        }

        int result = 1; // 최대 갯수가 int 최대 범위를 넘지 않으므로 int로 만들어두고, 가짓수를 곱해나갈 것이기 때문에 1로 초기화
        int start = 1; // 시작 인덱스
        while (!vip.isEmpty()) {
            int index = vip.pollFirst(); // 가장 처음 넣었던 인덱스부터 꺼냄
            if (index - start == 0) { // index와 start를 뺐을 때 0인 경우는 처음 index가 1인 경우 등인 경우임. 이럴 때는 start를 1 증가
                start++;
                continue;
            }
            result *= possibles[index - start]; // result에 경우의 수 곱함
            start = index + 1; // 영역 확인 끝났으므로 index + 1로 이동함
        }

        // start가 N보다 작다는 것은 마지막 index가 N보다 작다는 뜻임
        // 예시의 마지막 경우 생각하면 됨
        // 따라서 마지막 경우도 곱해줌 (index가 N + 1이라고 생각하면 됨)
        if (start < N) {
            result *= possibles[(N + 1) - start];
        }
        writer.write(result + "");

        writer.close();
        reader.close();
    }
}
