package binary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 보석_상자 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int child;
    private static int color;
    private static int[] jewel;
    private static int left;
    private static int right;
    private static int person;

    public static void main(String[] args) throws IOException {
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        child = Integer.parseInt(st.nextToken());
        color = Integer.parseInt(st.nextToken());
        person = Integer.MAX_VALUE; // 최종 답안: Math.min을 사용할 것이기 때문에 초깃값은 최대로
        left = 1;
        right = Integer.MIN_VALUE;
        jewel = new int[color];

        for (int i = 0; i < color; i++) {
            jewel[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, jewel[i]); // 가장 많은 갯수를 가진 보석의 수를 계산한다.
        }

        Arrays.sort(jewel); // 이진 탐색의 조건

        // 보석을 주는 경우는 1 ~ 가장 많은 갯수를 가진 보석의 수 범위에 있다.
        while (left <= right) {
            int mid = (left + right) / 2; // 테스트해 볼 나누어주는 수
            int needPerson = getMinimumNeedPerson(mid); // mid씩 배분했을 때 필요한 학생 수

            // 만약 필요한 학생 수가 설정된 학생 수보다 작거나 같다면: 가져가는 사람이 작으므로, 1인 당 취하는 보석 갯수가 더 많다. 즉, 더 줄여야 한다!
            if (child >= needPerson) {
                // 같을 때도 조건에 포함시킨 이유는, "최대한 더 적게" 질투심을 줄이기 위함이다.
                person = Math.min(person, mid);
                right = mid - 1;
            } else {
                // 만약 필요한 학생 수가 설정된 학생 수보다 크다면: 이 경우는 불가능한 케이스이기 때문에 줄여야 한다!
                left = mid + 1;
            }
        }

        bw.write(person + "");
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    // 각 보석마다 value만큼 배분했을 때 필요한 최소 인원을 파악한다.
    // 만약 보석 갯수를 value로 나눴을 때 나머지가 없다면 몫만큼만 필요할 것이다.
    // 만약 보석 갯수를 value로 나눴을 때 나머지가 있다면 몫 + 1명이 더 필요할 것이다.
    // value가 보석 갯수보다 큰 경우는 어떻게 될까? jewel[i] / value + 1에 의해 1로 처리된다.
    private static int getMinimumNeedPerson(int value) {
        int sum = 0;
        for (int i = 0; i < color; i++) {
            if (jewel[i] % value == 0) {
                sum += jewel[i] / value;
            } else {
                sum += jewel[i] / value + 1;
            }
        }
        return sum;
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
