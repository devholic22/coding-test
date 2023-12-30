package 프로그래머스.lv2;

public class 최댓값과_최솟값 {

    /*
    SOLVED: 23.12.30 (토)
    최댓값과 최솟값 - Lv2
     */
    public String solution(String s) {
        String[] numbers = s.split(" ");
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for (String number : numbers) {
            maxValue = Math.max(Integer.parseInt(number), maxValue);
            minValue = Math.min(Integer.parseInt(number), minValue);
        }

        return minValue + " " + maxValue;
    }
}
