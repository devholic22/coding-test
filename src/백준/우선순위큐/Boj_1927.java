package 백준.우선순위큐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Boj_1927 {

    /*
    SOLVED: 23.12.21 (목)
    최소 힙 - 실버2
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100,000 -> O(NlogN)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // total time-complexity = O(NlogN)
        for (int i = 0; i < N; i++) {
            int inputNumber = Integer.parseInt(reader.readLine());
            // 자연수인 경우 값을 넣음
            if (inputNumber > 0) {
                priorityQueue.add(inputNumber); // O(logN)
                continue;
            }
            // 비어 있을 경우 0 출력
            if (priorityQueue.isEmpty()) { // O(1)
                writer.write("0\n");
                continue;
            }
            // 0일 경우 꺼낸 뒤 값 출력
            int pollNumber = priorityQueue.poll(); // O(logN)
            writer.write(pollNumber + "\n");
        }

        writer.close();
        reader.close();
    }
}
