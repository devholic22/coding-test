package 백준.구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_13414 {

    private static class Number implements Comparable<Number> {

        private String value;
        private int line;

        public Number(final String value, final int line) {
            this.value = value;
            this.line = line;
        }

        @Override
        public int compareTo(Number other) {
            return Integer.compare(line, other.line);
        }
    }

    /*
    SOLVED: 24.01.15 (월)
    수강신청 - 실버3
    알게 된 점 1: 0으로 시작하는 수를 int로 출력할 경우 0이 나오지 않을 수 있다. String 사용 권장
    알게 된 점 2: 이 문제처럼 직접 삭제하고 다시 넣는 게 부담되는 대신 우선순위만 변경하면 되는 경우에는 HashMap과 PriorityQueue의 조합을 활용하면 좋다.
        // HashMap의 특성: 동일한 입력이 다시 들어올 경우 값이 갱신된다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int K = Integer.parseInt(tokenizer.nextToken()); // 1 <= K <= 100,000
        int L = Integer.parseInt(tokenizer.nextToken()); // 1 <= L <= 500,000

        HashMap<String, Integer> numbers = new HashMap<>();

        for (int i = 0; i < L; i++) {
            String number = reader.readLine();
            numbers.put(number, i); // HashMap의 특징을 활용하면 중복 검사를 할 필요가 없다. 자동으로 갱신해준다.
        }

        PriorityQueue<Number> queue = new PriorityQueue<>();

        for (String number : numbers.keySet()) {
            queue.add(new Number(number, numbers.get(number)));
        }

        int size = Math.min(K, queue.size()); // NullPointerException을 방지하기 위함이다.
        for (int i = 0; i < size; i++) {
            writer.write(queue.poll().value + "\n");
        }

        writer.close();
        reader.close();
    }
}
