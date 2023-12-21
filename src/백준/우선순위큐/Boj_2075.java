package 백준.우선순위큐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_2075 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()); // 1 <= N <= 1,500 -> O(N^2) 이상까지 가능, O(N^2logN)도 가능
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x, y) -> Integer.compare(y, x)); // 내림차순

        // O(N^2logN)
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreElements()) { // O(N)
                priorityQueue.add(Integer.parseInt(tokenizer.nextToken())); // O(logN)
            }
        }

        // O(NlogN)
        for (int i = 0; i < N - 1; i++) {
            priorityQueue.poll();
        }

        writer.write(priorityQueue.poll() + ""); // O(logN)

        // 만약 배열을 사용한 뒤 Arrays.sort한 후 값을 가져오도록 했으면 O(N^2)으로 되어 시간 복잡도가 더 낮았을 것 같다.
            // 배열 값 저장: O(N^2)
            // 배열 정렬: O(NlogN)
            // 출력: O(1)
            // 최종 시간 복잡도: O(N^2)
        // 이렇게 구하려는 최소값이 고정된 경우에는 우선순위 큐 보다는 배열을 활용하는 게 좋을 것 같다.
        writer.close();
        reader.close();
    }
}
