package 백준.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1931 {

    private static class Time implements Comparable<Time> {

        private int start;
        private int end;

        public Time(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time other) {
            // 반례: end가 같을 경우 start가 작은 것 부터 실행해야 함
            /*
            2 4 (선택 가능)
            3 4
            4 4 (선택 가능)
             */
            if (this.end == other.end) {
                return Integer.compare(this.start, other.start);
            }
            return Integer.compare(this.end, other.end);
        }
    }

    /*
    SOLVED: 24.01.07 (일)
    회의실 배정 - 실버1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        Time[] times = new Time[N];
        // 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            times[i] = new Time(start, end);
        }

        // 기준에 맞추어 정렬
        Arrays.sort(times);

        int result = 0;
        long endTime = 0;
        for (int i = 0; i < N; i++) {
            if (i > 0 && times[i].start < endTime) {
                continue;
            }
            // System.out.println(times[i].start + " " + times[i].end);
            endTime = times[i].end;
            result++;
        }
        writer.write(result + "");

        writer.close();
        reader.close();
    }
}
