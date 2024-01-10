package 백준.그리디;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1026 {

    /*
    SOLVED: 24.01.10 (수)
    보물 - 실버4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        int[] a = new int[N];
        int[] b = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(tokenizer.nextToken());
        }
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(a); // a만 정렬
        // b는 우선순위 큐 이용함
        PriorityQueue<Integer> queue = new PriorityQueue<>((a1, a2) -> Integer.compare(a2, a1));
        for (int number : b) {
            queue.add(number);
        }
        int result = 0;
        for (int i = 0; i < N; i++) {
            result += (a[i] * queue.poll());
        }
        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
