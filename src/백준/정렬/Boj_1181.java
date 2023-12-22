package 백준.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;

public class Boj_1181 {

    private static class Word implements Comparable<Word> {

        private String value;

        public Word(final String value) {
            this.value = value;
        }

        @Override
        public int compareTo(final Word other) {
            // 정렬 기준 1: 길이 오름차순
            // 정렬 기준 2: 사전 순
            if (this.value.length() == other.value.length()) {
                return this.value.compareTo(other.value); // 문자열의 경우 String.compareTo를 해야 함 (대소문자 무시: compareToIgnorecase())
            }
            return Integer.compare(this.value.length(), other.value.length());
        }
    }
    /*
    SOLVED: 23.12.22 (금)
    단어 정렬 - 실버 5
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 20,000 -> O(N^2) 미만 설계
        HashSet<String> uniqueWords = new HashSet<>(); // 중복 방지
        // O(N)
        for (int i = 0; i < N; i++) {
            uniqueWords.add(reader.readLine()); // O(1)
        }
        Word[] words = new Word[uniqueWords.size()];
        int index = 0;

        // O(N)
        for (String word : uniqueWords) {
            words[index] = new Word(word);
            index++;
        }

        Arrays.sort(words); // O(NlogN)

        // O(N)
        for (Word word : words) {
            writer.write(word.value + "\n");
        }

        writer.close();
        reader.close();
    }
}
