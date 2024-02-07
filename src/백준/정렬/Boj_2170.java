package 백준.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2170 {

    private static class Line implements Comparable<Line> {
        private final int start;
        private final int end;

        public Line(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(final Line other) {
            if (start == other.start) {
                return Integer.compare(end, other.end);
            }
            return Integer.compare(start, other.start);
        }
    }

    /*
    SOLVED: 24.02.06 (화)
    선 긋기 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int lineCount = Integer.parseInt(reader.readLine());
        Line[] lines = new Line[lineCount];
        for (int i = 0; i < lineCount; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            lines[i] = new Line(x, y);
        }
        Arrays.sort(lines);

        long size = 0;
        int start = lines[0].start;
        int end = lines[0].end;
        // System.out.println(Arrays.toString(lines));
        for (int i = 1; i < lineCount; i++) {
            if (start < lines[i].start && end > lines[i].start && end > lines[i].end) {
                continue;
            }
            if (start <= lines[i].start && end >= lines[i].start && end < lines[i].end) {
                end = lines[i].end;
            } else if (end < lines[i].start) {
                // end와 start 간의 거리 계산 후 저장
                if (start <= 0 && end >= 0) {
                    size += Math.abs(start) + end;
                } else {
                    size += Math.abs(end) - Math.abs(start);
                }
                start = lines[i].start;
                end = lines[i].end;
            }
        }

        if (start <= 0 && end >= 0) {
            size += Math.abs(start) + end;
        } else {
            size += Math.abs(end) - Math.abs(start);
        }
        writer.write(size + "");
        writer.close();
        reader.close();
    }
}
