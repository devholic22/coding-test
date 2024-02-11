package 프로그래머스.lv2;

public class 점프와_순간_이동 {

    /*
    SOLVED: 24.02.11 (일)
    점프와 순간 이동 - Lv2
     */
    public int solution(int n) {

        int answer = 0;
        // n이 10억까지 올 수 있기 때문에 배열은 안 된다.
        while (n > 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n--;
                answer++;
            }
        }
        return answer + 1; // 처음에 1을 더해야 하므로
    }
}
