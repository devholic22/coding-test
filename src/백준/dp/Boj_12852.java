package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Boj_12852 {

    /*
    SOLVED: 24.01.09 (화)
    1로 만들기 2 - 실버1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,000,000
        int[] numbers = new int[1_000_001]; // N의 크기에 관계없이 (인덱스 오류 막기 위해) 미리 최대 사이즈 설정
        int[] stages = new int[1_000_001]; // N의 크기에 관계없이 (인덱스 오류 막기 위해) 미리 최대 사이즈 설정

        // 초깃값 설정
        numbers[1] = 0; // 걸린 횟수
        numbers[2] = 1; // 걸린 횟수
        numbers[3] = 1; // 걸린 횟수
        stages[1] = 0; // 어디를 따라가야 1로 가기에 최적화된 선택이냐 (1일 경우에는 활용되지 않긴 함)
        stages[2] = 1; // 어디를 따라가야 1로 가기에 최적화된 선택이냐
        stages[3] = 1; // 어디를 따라가야 1로 가기에 최적화된 선택이냐

        for (int i = 4; i <= N; i++) {
            int bestBefore = i - 1; // 최선의 선택은 기본적으로 자신 직전이라고 설정
            numbers[i] = numbers[i - 1] + 1;
            if (i % 3 == 0 && numbers[i] > numbers[i / 3] + 1) {
                bestBefore = i / 3; // bestBefore 갱신
                numbers[i] = numbers[i / 3] + 1; // 일반적인 1로 만들기 문제와 같음
            }
            if (i % 2 == 0 && numbers[i] > numbers[i / 2] + 1) {
                bestBefore = i / 2; // bestBefore 갱신
                numbers[i] = numbers[i / 2] + 1; // 일반적인 1로 만들기 문제와 같음
            }
            stages[i] = bestBefore;
        }

        /*
        로그 출력용
        for (int i = 1; i <= N; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("=====");
        for (int i = 1; i <= N; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        System.out.println("=====");
        for (int i = 1; i <= N; i++) {
            System.out.print(stages[i] + " ");
        }
         */

        int index = N;
        ArrayDeque<Integer> result = new ArrayDeque<>();
        result.addFirst(N); // 가장 먼저에는 입력된 수 N이 나와야 함
        while (index != 1) {
            result.addLast(stages[index]); // ex: N이 10일 경우 stages[10]은 9이며 (다른 값도 가능), 따라서 9를 넣는다. 그리고 index를 9로 설정한다.
            index = stages[index];
        }

        writer.write(numbers[N] + "\n");
        while (!result.isEmpty()) {
            writer.write(result.pollFirst() + " "); // 먼저 쓰인 게 먼저 나와야 함
        }
        writer.close();
        reader.close();
    }
}
