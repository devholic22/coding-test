package 백준.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_15489 {

    /*
    SOLVED: 24.01.13 (토)
    파스칼 삼각형 - 실버4
    AO 알고리즘 스터디 문제
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());
        int W = Integer.parseInt(tokenizer.nextToken());

        int[][] numbers = new int[30][30]; // 2 <= R + W <= 30, 2 <= C + W <= 30, 1 <= W <= 29, C <= R
        // 초깃값 저장
        numbers[1][1] = 1;
        numbers[2][1] = 1;
        numbers[2][2] = 1;

        // 트리 만들기
        for (int i = 3; i <= 29; i++) {
            for (int j = 1; j <= 29; j++) {
                if (j == 1 || j == 29) {
                    numbers[i][j] = 1;
                    continue;
                }
                numbers[i][j] = numbers[i - 1][j - 1] + numbers[i - 1][j];
            }
        }

        /*
        로그 출력용
        for (int i = 1; i <= 29; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(numbers[i][j] + " ");
            }
            System.out.println();
        }
         */

        // 선택된 영역만큼 더하기
        int result = 0;
        int select = 1;
        for (int i = R; i < R + W; i++) {
            for (int j = C; j < C + select; j++) {
                // System.out.println("numbers[i][j] = " + numbers[i][j]); 로그 출력용
                result += numbers[i][j];
            }
            select++;
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }
}
