package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1759 {

    private static int L; // 만들 문자의 길이
    private static int C; // 제공된 문자들의 갯수
    private static char[] words; // 제공된 문자들
    private static char[] temp; // 만들어질 문자
    private static boolean[] used; // 이용 여부

    /*
    SOLVED: 24.02.07 (수)
    암호 만들기 - 골드5
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        L = Integer.parseInt(tokenizer.nextToken()); // 3 <= L <= C <= 15
        C = Integer.parseInt(tokenizer.nextToken());

        words = new char[C];
        temp = new char[L];
        used = new boolean[C];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < C; i++) {
            words[i] = tokenizer.nextToken().charAt(0); // String -> char (사전 순 비교를 할 것이라 char를 사용함)
        }

        Arrays.sort(words); // 문제 조건 1: 사전 순으로 출력한다 = 사전 순으로 정렬해두면 좋다
        Arrays.fill(temp, '0'); // char를 ''로 채우는 것은 컴파일 예외가 발생 = '0'으로 임시 저장
        backtracking(0);

        writer.close(); // 굳이 안 써도 됐을 듯
        reader.close();
    }

    private static void backtracking(int depth) {
        if (depth == L) { // 백트래킹 한계 도달 시 프린트 후 종료
            print();
            return;
        }
        for (int i = 0; i < C; i++) {
            if (used[i] || (depth > 0 && (int) temp[depth - 1] > (int) words[i])) {
                continue;
            }
            temp[depth] = words[i];
            used[i] = true;
            backtracking(depth + 1);
            used[i] = false;
            temp[depth] = '0';
        }
    }

    private static void print() {
        if (!isValid()) { // 유효한 경우에만 출력할 것임
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (char t : temp) {
            if (t == '0') { // 한번이라도 '0'을 만나면 return
                return;
            }
            builder.append(t);
        }
        System.out.println(builder.toString());
    }

    private static boolean isValid() {
        // 문제 조건 2: 모음 1개 이상, 자음 2개 이상이어야 함
        int mCount = 0; // 모음
        int nCount = 0; // 자음
        for (char t : temp) {
            if ("aeiou".contains(String.valueOf(t))) {
                mCount++;
            } else {
                nCount++;
            }
        }
        return mCount >= 1 && nCount >= 2;
    }
}
