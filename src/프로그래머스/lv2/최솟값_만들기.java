package 프로그래머스.lv2;

import java.util.Arrays;

public class 최솟값_만들기 {

    /*
    SOLVED: 23.12.30 (토)
    최솟값 만들기 - Lv2
     */
    public int solution(int []A, int []B)
    {
        int answer = 0;

        // A는 오름차순 정렬, B는 내림차순 정렬한 뒤 하나씩 곱한 뒤 더하면 된다.
        Arrays.sort(A);
        Arrays.sort(B); // 오름차순 정렬을 해도 내림차순 효과를 얻을 수 있다. (아래 for문)

        for (int i = 0; i < A.length; i++) { // A length == B length
            answer += (A[i] * B[A.length - 1 - i]);
        }
        return answer;
    }
}
