package 백준.투포인터;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_20922 {

    /*
    SOLVED: 24.01.16 (화)
    겹치는 건 싫어 - 실버1
    https://www.acmicpc.net/board/view/74687을 참고하였다.
    이 문제 덕분에 슬라이딩 윈도우가 정확히 어떤 개념인지 알게 되었다.
    사실상 슬라이딩 윈도우의 기본 유형 문제..
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 1 <= N <= 200,000
        int K = Integer.parseInt(tokenizer.nextToken()); // 1 <= K <= 100

        int[] numbers = new int[N]; // 총 N개의 입력이 들어온다. ex: [3 2 5 5 6 4 4 5 7]
        tokenizer = new StringTokenizer(reader.readLine());
        // 값 저장
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        // 시작 지점
        int start = 0;
        int end = 0;

        int[] saved = new int[100_000 + 1]; // 각 숫자는 1 ~ 100,000까지 가능하다. 따라서 100000 + 1개의 배열을 만든다.
        int result = 0; // 결과

        while (start < N && end < N) {
            saved[numbers[end]]++; // ex: 처음에는 saved[numbers[0]]++ = saved[첫 번째 입력값]++가 될 것이다.
            if (saved[numbers[end]] > K) { // 만약 제한 수 K보다 해당 saved[numbers[end]]가 커졌다면 이동 및 갱신이 필요하다.
                result = Math.max(result, end - start); // 시작점이 0이기 때문에, end - start로 해야 함. 추가로 -1을 빼면 안 됨
                while (saved[numbers[end]] > K) { // saved[numbers[end]]가 K보다 작게 될 때 까지 한 칸씩 옮겨갈거임
                    /*
                    예시
                    21 1
                    ["1 2 3 4 5 6 6" 7 8 9 10 11 13 15 63 34 34 33 33 22 1]인 경우
                    end는 6, start는 0인 상황에서 멈출 것이다.
                    result를 6으로 갱신하고
                    맨 처음 " 부분을 차례로 오른쪽으로 옮겨간다고 상상하자. (= start++, end는 고정)
                    그러면 1, 2, 3..을 사용한 횟수를 하나씩 줄여야 할 것이다. (= saved[numbers[start]]--)
                    그러다보면 start가 5가 되었을 때에도 6의 사용 횟수가 감소하여 반복문이 멈출 것이다.
                    그리고 멈춘 이후 end가 증가되기 때문에 6이 추가로 사용되는 일은 없다.
                     */
                    saved[numbers[start]]--;
                    start++;
                }
            }
            end++; // end는 계속 증가한다.
        }

        // end가 N에 도달하면, 위의 while 문에서는 갱신되지 않는다. 때문에 한 번 더 처리해줘야 한다.
        if (end == N) {
            result = Math.max(result, end - start);
        }
        // System.out.println(Arrays.toString(saved));
        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
