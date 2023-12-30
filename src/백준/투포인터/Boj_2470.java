package 백준.투포인터;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2470 {

    private static int N;
    private static Number[] numbers;
    private static class Number implements Comparable<Number> {

        private int value;

        public Number(final int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Number other) {
            return Integer.compare(Math.abs(other.value), Math.abs(value));
            // Integer.compare(x, y)일 시 x < y라면 -1 (기본), x == y이면 0, x > y이면 1
            // Integer.compare(y, x)일 시 y < x라면 -1 (즉, 이것을 따르고자 y -> x 식으로 표현됨)
            // 절댓값 기준으로 내림차순 정렬한다.
        }
    }

    /*
    SOLVED: 23.12.30 (토)
    두 용액 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine()); // 2 <= N <= 100,000
        numbers = new Number[N];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = new Number(Integer.parseInt(tokenizer.nextToken()));
        }
        Arrays.sort(numbers); // O(NlogN)

        int start = 0;
        int end = 1; // end를 start와 같게 하면 안 된다.
        int result = Integer.MAX_VALUE;
        int[] values = new int[2];
        while (start < N && end < N) { // O(N)
            int distance = distance(start, end); // O(1)
            if (distance < result) { // 갱신
                result = distance;
                values[0] = numbers[start].value;
                values[1] = numbers[end].value;
            }
            start++; // start와 end는 해당 단계에서 할 수 있는 최적의 값을 구했음. 혹여 최적의 값이 뒤에 있어도, 그것은 다음 start & end에서 할 수 있음
            end++;
            /*
            예시: [-99 -98 97 4 -3 -1]일 때, -99 + -98을 한 것 보다 -99 + 97이 더 나은 선택이지만, 이것은 start가 -98, end가 97로 옮겨졌을 때 처리 가능함.
             */
        }

        Arrays.sort(values); // 출력 조건 맞추기 위함
        writer.write(values[0] + " " + values[1]);
        writer.close();
        reader.close();
    }

    // 두 수의 합을 절댓값으로 변환
    private static int distance(int start, int end) {
        return Math.abs(numbers[start].value + numbers[end].value);
    }
}
