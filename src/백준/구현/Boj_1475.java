package 백준.구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Boj_1475 {

    /*
    SOLVED: 23.12.25 (월)
    방 번호 - 실버5
    https://www.acmicpc.net/board/view/114794 글을 참고하였다.
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = 1; // 임시 result

        String[] numbers = reader.readLine().split(""); // 1 <= N <= 1,000,000 -> O(N)
        HashMap<Integer, Integer> used = new HashMap<>(); // 일반적인 번호 (0 ~ 9 중 6, 9 제외) 카운트

        boolean[] duplicate = new boolean[2]; // 6, 9
        int SIX = 0;
        int NINE = 1;

        // 처음 numbers 사용량 초기화
        for (int i = 0; i < 10; i++) {
            used.put(i, 0);
        }

        // 입력값을 전체적으로 돈다
        for (int i = 0; i < numbers.length; i++) { // O(N)
            // 만약 숫자가 일반적인 숫자라면 맵에 넣기만 하면 된다
            if (!"69".contains(numbers[i])) {
                used.put(Integer.parseInt(numbers[i]), used.get(Integer.parseInt(numbers[i])) + 1);
                continue;
            }
            // 6일 경우
            if (numbers[i].equals("6")) {
                if (!duplicate[SIX]) {
                    duplicate[SIX] = true; // 6을 쓸 수 있을 경우 true
                }
                else if (duplicate[SIX] && !duplicate[NINE]) { // 6은 이미 썼지만, 9를 쓸 수 있을 경우 9를 이용
                    duplicate[NINE] = true;
                } else { // 그 외의 경우에는 새롭게 초기화 시켜야 함 -> 카운트 증가
                    Arrays.fill(duplicate, false);
                    duplicate[SIX] = true; // 새롭게 초기화 시킨 뒤 6을 이용하도록 설정
                    count++;
                }
            } else { // 9일 경우
                if (!duplicate[NINE]) {
                    duplicate[NINE] = true; // 9를 쓸 수 있을 경우 true
                }
                else if (!duplicate[SIX] && duplicate[NINE]) { // 9는 이미 썼지만, 6을 쓸 수 있을 경우 6을 이용
                    duplicate[SIX] = true;
                } else {
                    Arrays.fill(duplicate, false);
                    duplicate[NINE] = true; // 새롭게 초기화 시킨 뒤 9를 이용하도록 설정
                    count++;
                }
            }
        }

        // 일반적인 수 중에서 중복된 수의 최대 갯수 계산
        int temp = 0;
        for (int key : used.keySet()) {
            temp = Math.max(temp, used.get(key));
        }

        // 필요한 최대의 수를 출력해야 한다.
        // 예시로 6을 쓰는 데 3개, 8을 쓰는 데 5개가 걸렸다면 어쨌든 6, 9를 제외한 수는 한 번에 하나만 담을 수 있기 때문이다.
        writer.write(Math.max(count, temp) + "");
        writer.close();
        reader.close();
    }
}
