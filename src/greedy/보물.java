package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 보물 {

    private static class Node implements Comparable<Node> {
        private int index;
        private int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node another) {
            if (this.value > another.value)
                return -1;
            if (this.value < another.value)
                return 1;
            return 0;
        }
    }

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static Node[] A;
    private static int[] B;
    public static void main(String[] args) throws IOException {
        init();
        int sum = 0;
        N = Integer.parseInt(br.readLine());
        A = new Node[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = new Node(i, Integer.parseInt(st.nextToken()));
        }
        B = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        Arrays.sort(B); // 문제의 의도와 다르긴 하다.
        for (int i = 0; i < N; i++) {
            sum += (A[i].value * B[i]);
        }

        bw.write(sum + "");
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
