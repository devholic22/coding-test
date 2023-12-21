package 백준.우선순위큐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Boj_1715 {

    /*
    SOLVED: 23.12.21 (목)
    카드 정렬하기 - 골드4
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 100,000 -> O(N^2) 미만 설계
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(); // 오름차순
        int sum = 0;
        int store = 0;
        if (N == 1) {
            writer.write("0\n"); // 수정: 한 묶음만 있을 시 필요 없기 때문이다.
            writer.close();
            reader.close();
            return;
        }

        // O(NlogN)
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(reader.readLine());
            priorityQueue.add(number); // O(logN)
        }

        // O(NlogN)
        while (!priorityQueue.isEmpty()) {
            int numberOne = priorityQueue.poll();
            if (priorityQueue.size() >= 1) {
                int numberTwo = priorityQueue.poll(); // O(logN)
                store += (numberOne + numberTwo);
                sum = (numberOne + numberTwo);
                priorityQueue.add(sum);
            } else {
                break;
            }
        }

        /*
        // 틀렸던 이유
        2, 2, 3, 3일 때
        (2 + 2) + (3 + 3) + (4 + 6) = 20이 되어야 한다.

        나는
        (2 + 2) + (4 + 3) + (7 + 3) = 21로 했다.

        즉, 두 값을 꺼내면서 더하고, 그리고 동시에 그 두 값의 합을 우선순위 큐에 다시 넣어야 한다.

        // 틀렸던 접근
        1 2 3 4일 경우
        (1 + 2) + (3 + 3) + (6 + 4) = 3 + 6 + 10 = 19
            이때 만약 1, 2로만 되어 있었다면 (1 + 2)만 되어야 한다. count에 1 + 2의 합 (sum)을 또 더하면 안 된다.

        // 틀렸던 접근
        /*
        1 2 3일 경우
        (1 + 2) + (3 + 3) = 3 + 6 = 9
         */
        writer.write(store + "");
        writer.close();
        reader.close();
    }
}
