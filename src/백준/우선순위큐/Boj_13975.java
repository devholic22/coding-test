package 백준.우선순위큐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_13975 {

    /*
    SOLVED: 23.12.21 (목)
    파일 합치기 3 - 골드 4
     */
    public static void main(String[] args) throws IOException {

        // 카드 정렬하기와 완전히 같다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine()); // T는 특별히 범위가 정해지지 않았음

        // O(TKlogK)
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(reader.readLine()); // 3 <= K <= 1,000,000 -> O(K^2) 미만 설계
            PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            long sum = 0;
            // O(TlogK)
            for (int j = 0; j < K; j++) {
                priorityQueue.add((long) Integer.parseInt(tokenizer.nextToken()));
            }
            // O(KlogK)
            while (!priorityQueue.isEmpty()) {
                long numberOne = priorityQueue.poll(); // O(logK)
                if (!priorityQueue.isEmpty()) {
                    long numberTwo = priorityQueue.poll(); // O(logK)
                    sum += (numberOne + numberTwo);
                    priorityQueue.add(numberOne + numberTwo);
                } else {
                    // sum += numberOne;
                    break;
                }
            }
            writer.write(sum + "\n");
        }
        writer.close();
        reader.close();
    }

    /*
    40 30 30 50 -> (30 + 30) + (40 + 50) + (60 + 90) = 300
    이미 (30 + 30)을 꺼내는 과정에서 60을 더했고, (40 + 50)을 꺼내는 과정에서 90을 꺼냈으므로 모든 수의 합을 저장 완료했다.
    따라서 마지막 시점에 sum += numberOne 을 하지 않는 것이 맞다.
     */
    /*
    유일하게 틀렸던 이유: 자료형 계산을 고려하지 못했다. 당장 K가 1,000,000 (백만)이고 파일의 크기가 전부 10,000 (일만)이라면
    int의 범위를 훨씬 넘는다. 이런 점도 고려하자.
     */
}
