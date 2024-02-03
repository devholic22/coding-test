package 백준.구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1590 {

    // 문제에서 요구하는 버스 형식: 시작 시간 | 시간 단위 | 버스 갯수
    private static class Bus {

        long startTime;
        long duration;
        int remain;

        public Bus(final long startTime, final long duration, final int remain) {
            this.startTime = startTime;
            this.duration = duration;
            this.remain = remain;
        }

        public void updateTime() {
            if (remain <= 1) { // 1로 하는 이유: 시작할 때 이미 하나를 소모할 것이기 때문
                return;
            } else {
                this.startTime += this.duration;
                this.remain--;
            }
        }
    }

    /*
    SOLVED: 24.02.03 (토)
    캠프가는 영식 - 실버4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int busCount = Integer.parseInt(tokenizer.nextToken()); // <= 50
        int visitTime = Integer.parseInt(tokenizer.nextToken()); // 1,000,000
        int maxTry = 0; // <= 100
        long result = Integer.MAX_VALUE;
        Bus[] buses = new Bus[busCount];

        // 버스 저장
        for (int i = 0; i < busCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int busStart = Integer.parseInt(tokenizer.nextToken());
            int busDuration = Integer.parseInt(tokenizer.nextToken());
            int busRemain = Integer.parseInt(tokenizer.nextToken());
            maxTry = Math.max(maxTry, busRemain); // 최대 시도 횟수 저장 위함
            Bus newBus = new Bus(busStart, busDuration, busRemain);
            buses[i] = newBus;
        }

        // 각 버스마다 시간 갱신
        for (int i = 0; i < maxTry; i++) {
            for (Bus bus : buses) {
                // System.out.println("bus.startTime = " + bus.startTime + ", remain = " + bus.remain);
                if (bus.startTime >= visitTime) {
                    // System.out.println("result = " + result);
                    result = Math.min(result, bus.startTime); // 최종 선정 시간은 최대한 빨라야 함
                }
                bus.updateTime(); // 버스 시간 갱신
            }
            // System.out.println();
        }

        // result < visitTime이면 성립 불가능하고, result가 갱신이 되지 않거나 Int 연산에서의 오류가 발생하면 음수로 처리될 것이므로 그런 경우에 대해 -1이 나오도록
        if (result < 0 || result == Integer.MAX_VALUE || result < visitTime) {
            writer.write("-1");
        } else {
            // 그 이외의 경우에는 구해진 result - visitTime = 기다려야 할 시간 출력
            writer.write((result - visitTime) + "");
        }

        writer.close();
        reader.close();
    }
}
