package 백준.문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_13223 {

    /*
    SOLVED: 23.12.17 (일)
    소금 폭탄 - 브론즈3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 상수 처리
        String SPLITTER = ":";
        int hourIndex = 0;
        int minuteIndex = 1;
        int secondIndex = 2;
        // 분, 시간 미리 썼는지
        boolean usedMinute = false;
        boolean usedHour = false;

        // 같으면 24:00:00
        String goalInput = reader.readLine();
        String nowInput = reader.readLine();
        if (goalInput.equals(nowInput)) {
            System.out.println("24:00:00");
            return;
        }

        String[] goal = goalInput.split(SPLITTER);
        String[] now = nowInput.split(SPLITTER);

        // second
        int second = Integer.parseInt(now[secondIndex]) - Integer.parseInt(goal[secondIndex]);
        if (second < 0) {
            second = 60 + second;
            usedMinute = true;
        }

        // minute
        int minute = Integer.parseInt(now[minuteIndex]) - Integer.parseInt(goal[minuteIndex]);
        if (usedMinute) {
            minute -= 1;
        }
        if (minute < 0) {
            minute = 60 + minute;
            usedHour = true;
        }

        // hour
        int hour = Integer.parseInt(now[hourIndex]) - Integer.parseInt(goal[hourIndex]);
        if (usedHour) {
            hour--;
        }
        if (hour < 0) {
            hour = 24 + hour;
        }

        // result
        writer.write(String.format("%02d", hour) + SPLITTER + String.format("%02d", minute) + SPLITTER + String.format("%02d", second));
        writer.close();
        reader.close();
    }
}
