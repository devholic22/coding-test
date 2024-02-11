package 프로그래머스.lv2;

public class 카펫 {

    /*
    SOLVED: 24.02.11 (일)
    카펫 - Lv2
     */
    public int[] solution(int brown, int yellow) {
        // (가로 - 2) * (세로 - 2)가 yellow, (가로 * 세로) - yellow가 brown임을 쉽게 알 수 있었음
        int[] answer = new int[2];
        int width = 2000; // 가로 최대
        int height = 2000; // 세로 최대
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if (i < j) {
                    continue;
                }
                if (yellow == (i - 2) * (j - 2) && brown == (i * j - yellow)) {
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }
        return answer;
    }
}
