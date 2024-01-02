package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_14889 {

    private static int N;
    private static int result;
    private static int[][] map;
    private static boolean[] visited;

    /*
    SOLVED: 24.01.02 (화)
    스타트와 링크 - 실버1
    https://st-lab.tistory.com/122 참고
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        map = new int[N][N]; // 값 저장
        visited = new boolean[N];
        result = Integer.MAX_VALUE; // 최솟값을 찾을 것이므로 최댓값 지정

        // 값 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        backtracking(0, 0);
        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static void backtracking(int index, int depth) {
        // 절반 선택했으면 종료
        if (depth == N / 2) {
            calculateDifference();
            return;
        }
        for (int i = index; i < N; i++) { // 이미 지나간 0 ~ index - 1 까지는 무시해야 함. 남아 있는 것들에 대해서만 진행
            if (!visited[i]) {
                visited[i] = true;
                backtracking(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void calculateDifference() {
        int teamScore = 0; // 팀 점수
        int linkScore = 0; // 링크 점수

        // N이 4일 경우, 겹치지 않기 위해 {0, 1, 2}번째 인덱스와 {1, 2, 3}번째 인덱스가 교류되도록 함
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // 모두 방문했으면 팀 점수로 간주
                if (visited[i] && visited[j]) {
                    teamScore += map[i][j];
                    teamScore += map[j][i];
                // 모두 방문하지 않았으면 링크 점수로 간주
                } else if (!visited[i] && !visited[j]) {
                    linkScore += map[i][j];
                    linkScore += map[j][i];
                }
            }
        }

        int value = Math.abs(teamScore - linkScore); // 절댓값 기준으로 저장
        if (value == 0) { // 0 찍었으면 출력 후 끝 하면 됨
            System.out.println(value);
            System.exit(0);
        }

        result = Math.min(result, value); // result 최솟값 갱신
    }
}
