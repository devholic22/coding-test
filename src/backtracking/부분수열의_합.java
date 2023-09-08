package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 부분수열의_합 {
    private static int result = 0;
    private static int target; // 찾고자 하는 합

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N개의 정수
        target = Integer.parseInt(st.nextToken()); // 찾고자 하는 합
        int[] arr = new int[N]; // 가 담겨진 배열
        st = new StringTokenizer(br.readLine());
        // 배열 값 저장
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 1개부터 N개를 선택할 때 각각의 경우가 S가 되는 경우의 수를 합산
        for (int i = 1; i <= N; i++) {
            comb(arr, new boolean[arr.length], 0, arr.length, i);
        }
        bw.write(result + "");
        bw.close();
        br.close();
    }

    // 조합 (n개 중 r개를 뽑는) 코드 : 자바에서 조합을 쓰면 무조건 외워놔야 하는 코드인 것 같습니다.
    // 백트래킹을 활용한 조합입니다. (참고 링크 : https://bcp0109.tistory.com/15)
    private static void comb(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited);
            return;
        }
        for (int i = start; i < n; i++) {
            visited[i] = true;
            comb(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    private static void print(int[] arr, boolean[] visited) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i])
                continue;
            sum += arr[i]; // 전체 합산을 했을 때
        }
        if (sum == target) // 타겟과 같으면 경우의 수를 1 증가시킵니다.
            result++;
    }
}