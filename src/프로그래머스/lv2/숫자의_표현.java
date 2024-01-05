package 프로그래머스.lv2;

public class 숫자의_표현 {

    /*
    SOLVED: 24.01.06 (토)
    숫자의 표현 - Lv2
    투 포인터로 쉽게 풀 수 있었다.
     */
    public int solution(int n) {
        // 투 포인터로 쉽게 풀 수 있다. 연속한 경우의 합이기 때문
        int start = 1;
        int end = 1;
        int[] numbers = new int[n + 1]; // 숫자
        int[] sums = new int[n + 1]; // 누적 합

        // 숫자 저장
        for (int i = 1; i <= n; i++) {
            numbers[i] = i;
            sums[i] = sums[i - 1] + numbers[i];
        }

        int result = 0;
        while (start <= n && end <= n) {
            int sum = calculate(start, end, sums);
            if (sum < n) { // n보다 작다면 더 고르면 됨
                end++;
            } else if (sum == n) { // n과 같다면 result 증가, 더 고르면 커지므로 start를 증가, 이때 end는 start로 초기화할 필요 없음 (start를 옮김으로써 n보다 작아졌기 때문)
                result++;
                start++;
            } else { // n보다 크다면 시작점을 이동시켜야 함
                start++;
            }
        }

        return result;
    }

    // 누적 합 계산 코드
    private int calculate(int start, int end, int[] sums) {
        return sums[end] - sums[start - 1];
    }
}
