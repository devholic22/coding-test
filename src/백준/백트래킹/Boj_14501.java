package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14501 {

    private static int day;
    private static Consult[] consults;
    private static boolean[] used;
    private static int result;

    // 상담 정보
    private static class Consult {
        private int day;
        private int costDay;
        private int money;

        public Consult(final int day, final int costDay, final int money) {
            this.day = day;
            this.costDay = costDay;
            this.money = money;
        }
    }

    /*
    SOLVED: 24.01.29 (월)
    퇴사 - 실버3
    식에서 실수할 만한 요소가 좀 있었던 것 같다.
    그리고 새벽에 풀어서 그런지 혼란이 좀 있었다..
    DP로는 냅색 문제와 유사한 것 같은데 그래서 그럴까 하다가 백트래킹 카테고리 있길래 백트래킹이 더 낫겠다 생각함 (원래는 안 보고도 바로 생각해냈어야 했는데..)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        day = Integer.parseInt(reader.readLine());
        consults = new Consult[day + 1];
        for (int i = 1; i <= day; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int costDay = Integer.parseInt(tokenizer.nextToken());
            int money = Integer.parseInt(tokenizer.nextToken());
            consults[i] = new Consult(i, costDay, money);
        }
        result = 0;
        used = new boolean[day + 1];

        // 실수로 used[1] = true를 그대로 뒀었음 (실패 원인)
        for (int i = 1; i <= day; i++) {
            used[i] = true; // 시작 날짜 true
            backtracking(i, consults[i].costDay); // 처음에는 backtracking(0, 0)으로 하려 함..
            Arrays.fill(used, false);
        }

        writer.write(result + "");
        writer.close();
        reader.close();
    }

    private static void backtracking(int d, int consume) {
        // 처음에는 백트래킹의 합을 의미한 temp에 consults[i].money를 넣고 빼는 식으로 했는데 그런 것 보다 used로만 값을 계산하는 게 깔끔했다.
        if (d + consume > day) { // ex: day가 7이고, d가 6이고 consume이 2일 경우 더 고를 수 있는 게 없음
            int sum = 0;
            for (int i = 1; i <= day; i++) {
                if (used[i]) {
                    sum += consults[i].money;
                }
            }
            if (d + consume > day + 1) { // ex: day가 7이고, d가 6이고 consume이 2라면 이때는 당일 + 다음 날짜 2일이 7일에 되므로 괜찮음. 하지만 그 이상일 경우에는 안됨
                sum -= consults[d].money;
            }
            result = Math.max(result, sum);
            return;
        }

        for (int i = 1; i <= day; i++) {
            if (d + consume > consults[i].day) { // ex: 1일차가 3일이 걸린다면 2, 3일차는 스킵한다.
                continue;
            }
            used[i] = true;
            backtracking(consults[i].day, consults[i].costDay);
            used[i] = false;
        }
    }
}
