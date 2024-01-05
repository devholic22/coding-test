package 프로그래머스.lv2;

public class 피보나치_수 {

    /*
    SOLVED: 24.01.06 (토)
    피보나치 수 - Lv2
     */
    public int solution(int n) {
        int[] fibo = new int[n + 1];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 모든 피보나치 함수에 대해서 1234567으로 나눈 나머지가 저장되도록 해야 한다.
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1234567;
        }
        return fibo[n] % 1234567;
    }
}
