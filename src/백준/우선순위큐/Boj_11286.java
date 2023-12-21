package 백준.우선순위큐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Boj_11286 {

    // 정렬 조건이 두 개 이상이기 때문에 객체화한다. (직접 PriorityQueue에 정의해도 되겠지만..)
    private static class Number implements Comparable<Number> {

        int number;

        public Number(final int number) {
            this.number = number;
        }

        @Override
        public int compareTo(final Number other) {
            // 기준 첫 번째: 절댓값 (오름차순)
            if (Math.abs(this.number) == Math.abs(other.number)) {
                // 기준 두 번째: 실제 값 (오름차순)
                return this.number - other.number;
            }
            return Math.abs(this.number) - Math.abs(other.number);
        }
    }

    /*
    SOLVED: 23.12.21 (목)
    절댓값 힙 - 실버1
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100,000 -> O(N^2) 미만 설계
        PriorityQueue<Number> priorityQueue = new PriorityQueue<>();

        // O(NlogN)
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(reader.readLine());
            if (number == 0) {
                Number target = priorityQueue.poll(); // O(logN)
                if (target == null) {
                    writer.write("0\n");
                    continue;
                }
                writer.write(target.number + "\n");
                continue;
            }
            priorityQueue.add(new Number(number)); // O(logN)
        }

        writer.close();
        reader.close();
    }
}
