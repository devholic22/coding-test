package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 연산자_끼워넣기 {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLE = "*";
    private static final String DIVIDE = "/";

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int[] numbers;
    private static String[] operators;
    private static boolean[] used;
    private static String[] temp;
    private static int maxValue;
    private static int minValue;

    public static void main(String[] args) throws IOException {

        init();
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        operators = new String[N - 1];
        used = new boolean[N - 1];
        temp = new String[N - 1];
        String[] defaultOperators = {PLUS, MINUS, MULTIPLE, DIVIDE};

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int eachCount = Integer.parseInt(st.nextToken());
            for (int j = 0; j < eachCount; j++) {
                operators[count] = defaultOperators[i];
                count++;
            }
        }
        backtracking(0);

        bw.write(maxValue + "\n" + minValue);
        close();
    }

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        maxValue = Integer.MIN_VALUE;
        minValue = Integer.MAX_VALUE;
    }

    private static void backtracking(int depth) {
        if (depth == N - 1) {
            calculate();
            return;
        }
        for (int i = 0; i < N - 1; i++) {
            if (used[i])
                continue;
            used[i] = true;
            temp[depth] = operators[i];
            backtracking(depth + 1);
            temp[depth] = "";
            used[i] = false;
        }
    }

    private static void calculate() {
        int tempValue = numbers[0];
        for (int i = 0; i < N - 1; i++) {
            if (temp[i].equals(PLUS)) {
                tempValue += numbers[i + 1];
            } else if (temp[i].equals(MINUS)) {
                tempValue -= numbers[i + 1];
            } else if (temp[i].equals(MULTIPLE)) {
                tempValue *= numbers[i + 1];
            } else {
                tempValue /= numbers[i + 1];
            }
        }
        minValue = Math.min(minValue, tempValue);
        maxValue = Math.max(maxValue, tempValue);
    }

    private static void close() throws IOException {
        bw.close();
        br.close();
    }
}
