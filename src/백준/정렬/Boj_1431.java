package 백준.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj_1431 {

    private static class Guitar implements Comparable<Guitar> {

        private String value;
        private int score;

        public Guitar(final String value) {
            this.value = value;
            this.score = calculateScore(value);
        }

        // 숫자 합 계산
        private int calculateScore(String value) {
            int result = 0;
            String[] tokens = value.split("");
            for (String token : tokens) {
                if ("0123456789".contains(token)) {
                    result += Integer.parseInt(token);
                }
            }
            return result;
        }

        @Override
        public int compareTo(Guitar other) {
            // 1차 정렬: 길이 짧은 것
            if (value.length() == other.value.length()) {
                // 2차 정렬: 숫자 합 작은 것
                if (score == other.score) {
                    // 3차 정렬: 사전 순
                    return value.compareTo(other.value); // 정보: 문자열 정렬은 해당 문자열.compareTo(다른 문자열) 이다.
                }
                return Integer.compare(score, other.score);
            }
            return Integer.compare(value.length(), other.value.length());
        }
    }

    /*
    SOLVED: 24.01.05 (금)
    시리얼 번호 - 실버3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 50
        Guitar[] guitars = new Guitar[N];

        // O(N)
        for (int i = 0; i < N; i++) {
            guitars[i] = new Guitar(reader.readLine());
        }

        // O(NlogN)
        Arrays.sort(guitars);

        // O(NlogN)
        for (Guitar guitar : guitars) {
            writer.write(guitar.value + "\n");
        }

        writer.close();
        reader.close();
    }
}
